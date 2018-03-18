package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;

public class MessageProducerFactory
{
    private BrokerProperties brokerProperties;

    public MessageProducerFactory(BrokerProperties brokerProperties)
    {
        this.brokerProperties = brokerProperties;
    }

    public <M extends Message> MessageProducer<M> createMessageProducer(String topicName)
    {
        return new DefaultMessageProducer<>(topicName, brokerProperties);
    }
}
