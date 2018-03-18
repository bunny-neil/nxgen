package nxgen.kafka.client.command;

import nxgen.kafka.client.MessageProducer;
import nxgen.kafka.client.MessageQueueListener;
import nxgen.kafka.client.event.Event;

public class CommandFactory
{
    private CommandSpecification specification;
    private MessageProducer<Event> eventProducer;
    private MessageQueueListener<Event> eventQueueListener;
    private CommandManager commandManager;

    public CommandFactory(CommandSpecification specification,
                          MessageProducer<Event> eventProducer,
                          MessageQueueListener<Event> eventQueueListener)
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
