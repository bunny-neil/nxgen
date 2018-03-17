package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface CommandProducer
{
    CompletionStage<List<Event>> produceCommand(Command command, CommandSpecification specification);
}
