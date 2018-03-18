package nxgen.kafka.client;

import nxgen.kafka.client.config.BrokerProperties;
import nxgen.kafka.client.config.TopicProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DefaultMessageQueueListener<M extends Message> implements MessageQueueListener<M>, Closeable
{
    private String groupId;
    private BrokerProperties brokerProperties;
    private TopicProperties topicProperties;
    private MessageConsumer<M> messageConsumer;
    private ExecutorService executorService;
    private KafkaConsumer<String, M> kafkaConsumer;

    DefaultMessageQueueListener(String groupId, BrokerProperties brokerProperties, TopicProperties topicProperties)
    {
        this.groupId = groupId;
        this.brokerProperties = brokerProperties;
        this.topicProperties = topicProperties;
    }

    @Override
    public void start(MessageConsumer<M> consumer)
    {
        messageConsumer = consumer;
        executorService = Executors.newSingleThreadExecutor(r -> {
            Thread newThread = new Thread(r);
            newThread.setName(topicProperties.getTopicName() + "-queue-listener-thread");
            return newThread;
        });
        Properties properties = new Properties();
        properties.putAll(brokerProperties.toPropertiesMap());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, topicProperties.getKeyDeserializerClassName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, topicProperties.getValueDeserializerClassName());
        kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singleton(topicProperties.getTopicName()));
        executorService.execute(this::poll);
    }

    private void poll()
    {
        boolean running = true;
        while (running) {
            try {
                messageConsumer.beforeConsume();
                ConsumerRecords<String, M> consumerRecords = kafkaConsumer.poll(200L);
                if (! consumerRecords.isEmpty()) {
                    Iterator<ConsumerRecord<String, M>> recordIterator
                            = consumerRecords.records(topicProperties.getTopicName()).iterator();
                    if (recordIterator.hasNext()) {
                        messageConsumer.consume(recordIterator.next().value());
                        kafkaConsumer.commitSync();
                    }
                }
                messageConsumer.afterPoll();
            }
            catch (Throwable throwable) {
                running = false;
            }
            if (running) {
                running = ! Thread.currentThread().isInterrupted();
            }
        }
    }

    @Override
    public void close() throws IOException
    {
        executorService.shutdown();
        if (kafkaConsumer != null) {
            kafkaConsumer.close();
        }
    }
}
