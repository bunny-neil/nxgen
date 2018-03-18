package nxgen.messaging.client;

public interface MessageConsumer<M extends Message>
{
    default void beforeConsume() {}

    void consume(M message);

    default void afterPoll() {}
}
