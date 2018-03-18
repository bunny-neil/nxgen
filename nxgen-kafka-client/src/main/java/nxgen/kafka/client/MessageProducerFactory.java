package nxgen.kafka.client;

import nxgen.kafka.client.config.BrokerProperties;
import nxgen.kafka.client.config.TopicProperties;

public class MessageProducerFactory
{
    private BrokerProperties brokerProperties;

    public MessageProducerFactory(BrokerProperties brokerProperties)
    {
        this.brokerProperties = brokerProperties;
    }

    public <M extends Message> MessageProducer<M> createMessageProducer(TopicProperties topicProperties)
    {
        return new DefaultMessageProducer<>(brokerProperties, topicProperties);
    }
}
