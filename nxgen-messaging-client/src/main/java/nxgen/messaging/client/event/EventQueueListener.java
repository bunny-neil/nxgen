package nxgen.messaging.client.event;

public interface EventQueueListener
{
    void init();

    void receive();
}
