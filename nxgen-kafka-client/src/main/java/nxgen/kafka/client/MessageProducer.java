package nxgen.kafka.client;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public interface MessageProducer<M extends Message>
{
    Future<RecordMetadata> produce(M event);

    void flush();
}
