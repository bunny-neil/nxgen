package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface CommandExecutor
{
    CompletionStage<List<Event>> execute(Event initEvent, CommandSpecification specification);
}
