package nxgen.messaging.client;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public interface EventProducer
{
    Future<RecordMetadata> produceEvent(Event event);

    void flush();
}
