package nxgen.kafka.client;

import nxgen.kafka.client.config.BrokerProperties;
import nxgen.kafka.client.config.TopicProperties;

public class MessageQueueListenerFactory
{
    private BrokerProperties brokerProperties;

    public MessageQueueListenerFactory(BrokerProperties brokerProperties)
    {
        this.brokerProperties = brokerProperties;
    }

    public <M extends Message> MessageQueueListener<M> createMessageQueueListener(String groupId,
                                                                                  TopicProperties topicProperties)
    {
        return new DefaultMessageQueueListener<>(groupId, brokerProperties, topicProperties);
    }
}
