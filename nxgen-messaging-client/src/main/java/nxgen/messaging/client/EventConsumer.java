package nxgen.messaging.client;

public interface EventConsumer
{
    default void beforePoll() {}

    void consume(Event event);

    default void afterPoll() {}
}
