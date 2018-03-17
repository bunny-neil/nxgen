package nxgen.messaging.client.event;

import nxgen.messaging.client.config.BrokerProperties;

public class EventProducerFactory
{
    public EventProducer createEventProducer(String topicName, BrokerProperties brokerProperties)
    {
        return new EventProducerImpl(topicName, brokerProperties);
    }
}
