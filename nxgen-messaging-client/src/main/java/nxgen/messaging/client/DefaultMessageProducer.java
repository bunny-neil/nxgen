package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;
import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.serdes.EventSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

class DefaultMessageProducer<M extends Message> implements MessageProducer<M>, Closeable
{
    private static final long MAX_TIMEOUT_IN_MILLI = 5000L;
    private String topicName;
    private BrokerProperties brokerProperties;
    private KafkaProducer<String, Event> kafkaProducer;

    DefaultMessageProducer(String topicName, BrokerProperties brokerProperties)
    {
        this.topicName = topicName;
        this.brokerProperties = brokerProperties;
        this.init();
    }

    @Override
    public Future<RecordMetadata> produce(Event event)
    {
        return kafkaProducer.send(createProducerRecord(event));
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
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(properties);
    }

    private ProducerRecord<String, Event> createProducerRecord(Event event)
    {
        return new ProducerRecord<>(topicName, event.getId(), event);
    }
}
