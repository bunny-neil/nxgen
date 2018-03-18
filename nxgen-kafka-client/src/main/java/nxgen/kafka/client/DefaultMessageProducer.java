package nxgen.kafka.client;

import nxgen.kafka.client.config.BrokerProperties;
import nxgen.kafka.client.config.TopicProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

class DefaultMessageProducer<M extends Message> implements MessageProducer<M>, Closeable
{
    private static final long MAX_TIMEOUT_IN_MILLI = 5000L;
    private BrokerProperties brokerProperties;
    private TopicProperties topicProperties;
    private KafkaProducer<String, M> kafkaProducer;

    DefaultMessageProducer(BrokerProperties brokerProperties, TopicProperties topicProperties)
    {
        this.brokerProperties = brokerProperties;
        this.topicProperties = topicProperties;
        this.init();
    }

    @Override
    public Future<RecordMetadata> produce(M message)
    {
        return kafkaProducer.send(createProducerRecord(message));
    }

    @Override
    public void flush()
    {
        if (kafkaProducer != null) {
            kafkaProducer.flush();
        }
    }

    @Override
    public void close() throws IOException
    {
        if (kafkaProducer != null) {
            kafkaProducer.close();
        }
    }

    private void init()
    {
        Properties properties = new Properties();
        properties.putAll(brokerProperties.toPropertiesMap());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "" + MAX_TIMEOUT_IN_MILLI);
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "" + MAX_TIMEOUT_IN_MILLI);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, topicProperties.getKeySerializerClassName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, topicProperties.getValueSerializerClassName());
        kafkaProducer = new KafkaProducer<>(properties);
    }

    private ProducerRecord<String, M> createProducerRecord(M message)
    {
        return new ProducerRecord<>(topicProperties.getTopicName(), message.getId(), message);
    }
}
