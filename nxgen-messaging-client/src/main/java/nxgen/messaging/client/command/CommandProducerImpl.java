package nxgen.messaging.client.command;

import nxgen.messaging.client.MessagingException;
import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.EventConsumer;
import nxgen.messaging.client.event.EventProducer;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

class CommandProducerImpl implements CommandProducer, EventConsumer
{
    private EventProducer eventProducer;

    public CommandProducerImpl(EventProducer eventProducer)
    {
        this.eventProducer = eventProducer;
    }

    @Override
    public CompletionStage<List<Event>> produceCommand(Event initEvent, CommandSpecification specification)
    {
        try {
            eventProducer.produceEvent(initEvent)
                         .get(specification.timeout(), specification.timeUnit());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new MessagingException(e);
        }
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
