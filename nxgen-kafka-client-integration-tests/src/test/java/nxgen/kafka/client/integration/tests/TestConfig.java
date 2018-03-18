package nxgen.kafka.client.integration.tests;

import nxgen.kafka.client.MessageProducerFactory;
import nxgen.kafka.client.config.BrokerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig
{
    @Bean
    @ConfigurationProperties(prefix = "nxgen.kafka.client.tests.brokers")
    public BrokerProperties brokerProperties()
    {
        return new BrokerProperties();
    }

    @Bean
    public MessageProducerFactory messageProducerFactory(BrokerProperties brokerProperties)
    {
        return new MessageProducerFactory(brokerProperties);
    }
}
