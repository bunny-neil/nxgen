package nxgen.kafka.client;

public class MessagingException extends RuntimeException
{
    private static final long serialVersionUID = 8086557287266554159L;

    public MessagingException(Throwable cause)
    {
        super(cause);
    }
}
