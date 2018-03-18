package nxgen.messaging.client;

import nxgen.messaging.client.event.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@EnableAutoConfiguration
public class EventProducerTests extends AbstractTests
{
    private MessageProducer<Event> underTest;
    private String topicName;

    @Before
    public void setup()
    {
        if (underTest == null) {
            super.setup();
            topicName = UUID.randomUUID().toString();
            createTopic(topicName, 1);
            underTest = messageProducerFactory.createMessageProducer(topicName);
        }
    }

    @Test
    public void testProduceEvent() throws ExecutionException, InterruptedException
    {
        Assert.assertNotNull(underTest.produce(createEvent()).get());
    }
}
