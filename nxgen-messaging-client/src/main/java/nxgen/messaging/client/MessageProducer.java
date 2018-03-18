package nxgen.messaging.client;

import nxgen.messaging.client.event.Event;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public interface MessageProducer<M extends Message>
{
    Future<RecordMetadata> produce(Event event);

    void flush();
}
