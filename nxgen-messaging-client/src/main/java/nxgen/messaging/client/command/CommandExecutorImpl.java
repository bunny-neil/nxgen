package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.EventConsumer;
import nxgen.messaging.client.event.EventProducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class CommandExecutorImpl implements CommandExecutor, EventConsumer
{
    private EventProducer eventProducer;
    private Map<String, CommandFuture> idsAndFutures = new HashMap<>();

    public CommandExecutorImpl(EventProducer eventProducer)
    {
        this.eventProducer = eventProducer;
    }

    @Override
    public CompletionStage<List<Event>> execute(Event initEvent, CommandSpecification specification)
    {
        CommandFuture future = new CommandFuture(initEvent, specification);
        idsAndFutures.put(initEvent.getId(), future);
        try {
            eventProducer.produceEvent(initEvent)
                         .get(specification.timeout(), specification.timeUnit());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.eventsFuture.completeExceptionally(e);
        }
        return future.eventsFuture;
    }

    @Override
    public void consume(Event event)
    {
        CommandFuture future = idsAndFutures.get(event.getId());
        if (future != null) {
            future.completeIfPossible(event);
        }
    }

    @Override
    public void afterPoll()
    {
        removeCompletedAndExpiredCommands();
    }

    private void removeCompletedAndExpiredCommands()
    {
        Predicate<CommandFuture> doneCond = CommandFuture::isDone;
        Predicate<CommandFuture> expiredCond = CommandFuture::timeoutIfNecessary;

        List<String> doneIds = idsAndFutures.values().stream()
                .filter(doneCond.or(expiredCond))
                .map(future -> future.initEvent.getId())
                .collect(Collectors.toList());
        for (String id : doneIds) {
            idsAndFutures.remove(id);
        }
    }

    private class CommandFuture
    {
        private Event initEvent;
        private CommandSpecification specification;
        private List<Event> events = new ArrayList<>();
        private CompletableFuture<List<Event>> eventsFuture = new CompletableFuture<>();

        CommandFuture(Event initEvent, CommandSpecification specification)
        {
            this.initEvent = initEvent;
            this.specification = specification;
        }

        void completeIfPossible(Event event)
        {
            events.add(event);
            if (specification.shouldStop(event)) {
                eventsFuture.complete(events);
            }
        }

        boolean isDone()
        {
            return eventsFuture.isDone();
        }

        boolean timeoutIfNecessary()
        {
            boolean isExpired = System.currentTimeMillis() - initEvent.getDateCreated().getTime()
                    >= specification.timeUnit().toMillis(specification.timeout());
            if (isExpired) {
                eventsFuture.completeExceptionally(new TimeoutException());
            }
            return isExpired;
        }
    }
}
