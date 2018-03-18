package nxgen.kafka.client.command;

import nxgen.kafka.client.event.Event;

import java.util.concurrent.TimeUnit;

public interface CommandSpecification
{
    long timeout();

    TimeUnit timeUnit();

    boolean shouldStop(Event event);
}
