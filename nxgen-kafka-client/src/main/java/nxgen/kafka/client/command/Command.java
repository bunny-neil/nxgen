package nxgen.kafka.client.command;

import nxgen.kafka.client.event.Event;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface Command
{
    CommandSpecification getSpecification();

    Event getInitEvent();

    CompletionStage<List<Event>> execute();
}
