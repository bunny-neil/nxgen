package nxgen.messaging.client.command;

import nxgen.messaging.client.event.Event;
import nxgen.messaging.client.event.EventProducer;
import nxgen.messaging.client.event.EventQueueListener;

public class CommandFactory
{
    private CommandSpecification specification;
    private EventProducer eventProducer;
    private EventQueueListener eventQueueListener;
    private CommandManager commandManager;

    public CommandFactory(CommandSpecification specification, EventProducer eventProducer, EventQueueListener eventQueueListener)
    {
        this.specification = specification;
        this.eventProducer = eventProducer;
        this.eventQueueListener = eventQueueListener;
        this.commandManager = new CommandManager();
        this.eventQueueListener.start(this.commandManager);
    }

    public Command createCommand(Event initEvent)
    {
        return new CommandImpl(specification, initEvent, eventProducer, commandManager);
    }
}
