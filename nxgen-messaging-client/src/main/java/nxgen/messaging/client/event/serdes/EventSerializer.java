package nxgen.messaging.client.event.serdes;

import nxgen.messaging.client.MessagingException;
import nxgen.messaging.client.event.Event;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.ExtendedSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class EventSerializer implements ExtendedSerializer<Event>
{
    @Override
    public byte[] serialize(String topic, Headers headers, Event data)
    {
        return serialize(topic, data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey)
    {
    }

    @Override
    public byte[] serialize(String topic, Event data)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        DatumWriter<nxgen.messaging.client.event.avro.Event> writer
                = new SpecificDatumWriter<>(nxgen.messaging.client.event.avro.Event.getClassSchema());
        try {
            writer.write(toAvroEvent(data), encoder);
            encoder.flush();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            throw new MessagingException(e);
        }
    }

    @Override
    public void close()
    {
    }

    private nxgen.messaging.client.event.avro.Event toAvroEvent(Event domainEvent)
    {
        return nxgen.messaging.client.event.avro.Event.newBuilder()
                .setId(domainEvent.getId())
                .setType(domainEvent.getType())
                .setDateCreated(domainEvent.getDateCreated().getTime())
                .setPayload(ByteBuffer.wrap(domainEvent.getPayload()))
                .setSender(ByteBuffer.wrap(domainEvent.getSender()))
                .build();
    }
}
