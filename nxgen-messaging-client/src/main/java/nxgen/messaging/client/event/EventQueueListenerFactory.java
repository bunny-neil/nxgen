package nxgen.messaging.client.event;

import nxgen.messaging.client.config.BrokerProperties;

public class EventQueueListenerFactory
{
    public EventQueueListener createEventQueueListener(String topicName, String groupId, BrokerProperties brokerProperties)
    {
        return new EventQueueListenerImpl(topicName, groupId, brokerProperties);
    }
}
