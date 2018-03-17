package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;
import nxgen.messaging.client.serdes.EventSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

public class EventProducer implements Closeable
{
    private static final long MAX_TIMEOUT_IN_MILLI = 5000L;
    private String topicName;
    private BrokerProperties brokerProperties;
    private KafkaProducer<String, Event> kafkaProducer;

    public EventProducer(String topicName, BrokerProperties brokerProperties)
    {
        this.topicName = topicName;
        this.brokerProperties = brokerProperties;
        this.init();
    }

    public Future<RecordMetadata> produceEvent(Event event)
    {
        return kafkaProducer.send(createProducer(event));
    }

    @Override
    public void close() throws IOException
    {
        if (kafkaProducer != null) {
            kafkaProducer.close();
        }
    }

    public void flush()
    {
        kafkaProducer.flush();
    }

    private void init()
    {
        Properties properties = new Properties();
        properties.putAll(brokerProperties.toPropertiesMap());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "" + MAX_TIMEOUT_IN_MILLI);
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "" + MAX_TIMEOUT_IN_MILLI);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getSimpleName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class.getSimpleName());
        kafkaProducer = new KafkaProducer<>(properties);
    }

    private ProducerRecord<String, Event> createProducer(Event event)
    {
        return new ProducerRecord<>(topicName, event.getId(), event);
    }
}