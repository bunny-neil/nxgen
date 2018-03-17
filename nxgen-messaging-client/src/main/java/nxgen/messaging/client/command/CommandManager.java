package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.EventConsumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class CommandManager implements EventConsumer
{
    private Map<String, CommandImpl> idsAndCommands = new HashMap<>();

    public CommandImpl registerCommand(CommandImpl command)
    {
        idsAndCommands.put(command.getInitEvent().getId(), command);
        return command;
    }

    public void consume(Event event)
    {
        CommandImpl command = idsAndCommands.get(event.getId());
        if (command != null) {
            command.completeIfPossible(event);
        }
    }

    @Override
    public void afterPoll()
    {
        removeDoneCommands();
    }

    private void removeDoneCommands()
    {
        Predicate<CommandImpl> doneCond = CommandImpl::isDone;
        Predicate<CommandImpl> expiredCond = CommandImpl::timeoutIfNecessary;
        Predicate<CommandImpl> filter = doneCond.or(expiredCond);

        List<String> doneIds = idsAndCommands.values().stream()
                .filter(filter)
                .map(future -> future.getInitEvent().getId())
                .collect(Collectors.toList());
        for (String id : doneIds) {
            idsAndCommands.remove(id);
        }
    }
}
