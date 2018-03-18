package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;
import nxgen.messaging.client.event.Event;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public abstract class AbstractTests
{
    @Autowired
    protected BrokerProperties brokerProperties;
    protected MessageProducerFactory messageProducerFactory;

    private AdminClient adminClient;
    private List<NewTopic> topics = new ArrayList<>();

    @Before
    public void setup()
    {
        if (adminClient == null) {
            Properties properties = new Properties();
            properties.putAll(brokerProperties.toPropertiesMap());
            properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "" + 5000L);
            adminClient = AdminClient.create(properties);
            messageProducerFactory = new MessageProducerFactory(brokerProperties);
        }
    }

    protected void createTopic(String name, int partitions)
    {
        NewTopic topic = new NewTopic(name, partitions, (short)1);
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "60000");
        topic.configs(configs);
        try {
            adminClient.createTopics(Collections.singleton(topic)).all().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        topics.add(topic);
    }

    protected Event createEvent()
    {
        Event newEvent = new Event();
        newEvent.setId(UUID.randomUUID().toString());
        newEvent.setDateCreated(new Date());
        newEvent.setPayload("abc".getBytes());
        newEvent.setSender("sender".getBytes());
        newEvent.setType("some_type");
        return newEvent;
    }
}
