package nxgen.kafka.client.integration.tests;

import nxgen.kafka.client.MessageProducer;
import nxgen.kafka.client.config.TopicProperties;
import nxgen.kafka.client.event.Event;
import nxgen.kafka.client.event.serdes.EventSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

@EnableAutoConfiguration
public class EventProducerTests extends AbstractTests
{
    private MessageProducer<Event> underTest;
    private TopicProperties topicProperties;

    @Before
    public void setup()
    {
        topicProperties = new TopicProperties();
        topicProperties.setTopicName(UUID.randomUUID().toString());
        topicProperties.setKeySerializerClassName(StringSerializer.class.getName());
        topicProperties.setValueSerializerClassName(EventSerializer.class.getName());
        underTest = messageProducerFactory.createMessageProducer(topicProperties);
    }

    @Test
    public void testProduce() throws ExecutionException, InterruptedException
    {
        assertNotNull(underTest.produce(createEvent()).get());
    }
}
