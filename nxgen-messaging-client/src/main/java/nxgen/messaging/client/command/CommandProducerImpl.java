package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.EventConsumer;
import nxgen.messaging.client.event.EventProducer;
import nxgen.messaging.client.event.EventQueueListener;

import java.util.List;
import java.util.concurrent.CompletionStage;

class CommandProducerImpl implements CommandProducer, EventConsumer
{
    private EventProducer eventProducer;
    private EventQueueListener eventQueueListener;

    @Override
    public CompletionStage<List<Event>> produceCommand(Command command, CommandSpecification specification)
    {
        return null;
    }

    @Override
    public void consume(Event event)
    {
    }

    @Override
    public void afterPoll()
    {
    }
}
