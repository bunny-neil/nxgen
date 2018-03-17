package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;

import java.util.concurrent.TimeUnit;

public interface CommandSpecification
{
    long timeout();

    TimeUnit timeUnit();

    boolean shouldStop(Event event);
}
