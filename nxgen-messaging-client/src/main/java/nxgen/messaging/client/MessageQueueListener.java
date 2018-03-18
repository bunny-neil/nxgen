package nxgen.messaging.client;

public interface MessageQueueListener<M extends Message>
{
    void start(MessageConsumer<M> consumer);
}
