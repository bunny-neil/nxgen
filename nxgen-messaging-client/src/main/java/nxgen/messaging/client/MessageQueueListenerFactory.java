package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;

public class MessageQueueListenerFactory
{
    private BrokerProperties brokerProperties;

    public MessageQueueListenerFactory(BrokerProperties brokerProperties)
    {
        this.brokerProperties = brokerProperties;
    }

    public <M extends Message> MessageQueueListener<M> createMessageQueueListener(String topicName, String groupId)
    {
        return new DefaultMessageQueueListener<>(topicName, groupId, brokerProperties);
    }
}
