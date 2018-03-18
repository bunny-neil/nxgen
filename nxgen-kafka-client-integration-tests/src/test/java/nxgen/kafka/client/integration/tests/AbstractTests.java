package nxgen.kafka.client.integration.tests;

import nxgen.kafka.client.MessageProducerFactory;
import nxgen.kafka.client.config.BrokerProperties;
import nxgen.kafka.client.event.Event;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public abstract class AbstractTests
{
    @Autowired
    protected BrokerProperties brokerProperties;

    @Autowired
    protected MessageProducerFactory messageProducerFactory;

    protected Event createEvent()
    {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setDateCreated(new Date());
        event.setPayload("test".getBytes());
        event.setSender("sender".getBytes());
        event.setType("Some_Type");
        return event;
    }
}
