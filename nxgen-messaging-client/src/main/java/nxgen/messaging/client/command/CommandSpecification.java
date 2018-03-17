package nxgen.messaging.client.command;

import java.util.concurrent.TimeUnit;

public interface CommandSpecification
{
    long timeout();

    TimeUnit timeUnit();
}
