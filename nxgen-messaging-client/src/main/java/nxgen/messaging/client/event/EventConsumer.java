package nxgen.messaging.client.event;

public interface EventConsumer
{
    default void beforePoll() {}

    void consume(Event event);

    default void afterPoll() {}
}
