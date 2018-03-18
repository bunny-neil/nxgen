package nxgen.kafka.client;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Message implements Serializable
{
    private static final long serialVersionUID = -1278287872470649001L;

    private String id;
    private String type;
    private byte[] sender;
    private byte[] payload;
    private Date dateCreated = new Date();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public byte[] getSender()
    {
        return sender;
    }

    public void setSender(byte[] sender)
    {
        this.sender = sender;
    }

    public byte[] getPayload()
    {
        return payload;
    }

    public void setPayload(byte[] payload)
    {
        this.payload = payload;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(type, message.type) &&
                Objects.equals(dateCreated, message.dateCreated);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, type, dateCreated);
    }
}
