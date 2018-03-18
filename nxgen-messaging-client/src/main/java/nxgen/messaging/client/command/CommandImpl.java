package nxgen.messaging.client.command;

import nxgen.messaging.client.MessageProducer;
import nxgen.messaging.client.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

class CommandImpl implements Command
{
    private CommandSpecification specification;
    private Event initEvent;
    private MessageProducer<Event> eventProducer;
    private CommandManager commandManager;
    private List<Event> eventList = new ArrayList<>();
    private CompletableFuture<List<Event>> futureEvents = new CompletableFuture<>();

    CommandImpl(CommandSpecification specification,
                Event initEvent,
                MessageProducer<Event> eventProducer,
                CommandManager commandManager)
    {
        this.specification = specification;
        this.initEvent = initEvent;
        this.eventProducer = eventProducer;
        this.commandManager = commandManager;
    }

    @Override
    public CommandSpecification getSpecification()
    {
        return specification;
    }

    @Override
    public Event getInitEvent()
    {
        return initEvent;
    }

    @Override
    public CompletionStage<List<Event>> execute()
    {
        try {
            commandManager.registerCommand(this);
            eventProducer.produce(initEvent)
                    .get(specification.timeout(), specification.timeUnit());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            futureEvents.completeExceptionally(e);
        }
        return futureEvents;
    }

    public CompletableFuture<List<Event>> getFutureEvents()
    {
        return futureEvents;
    }

    public void completeIfPossible(Event event)
    {
        eventList.add(event);
        if (specification.shouldStop(event)) {
            futureEvents.complete(eventList);
        }
    }

    public boolean isDone()
    {
        return futureEvents.isDone();
    }

    public boolean timeoutIfNecessary()
    {
        boolean isExpired = System.currentTimeMillis() - initEvent.getDateCreated().getTime()
                >= specification.timeUnit().toMillis(specification.timeout());
        if (isExpired) {
            futureEvents.completeExceptionally(new TimeoutException());
        }
        return isExpired;
    }
}
