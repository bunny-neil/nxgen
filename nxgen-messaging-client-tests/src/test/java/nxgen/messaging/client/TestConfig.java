package nxgen.messaging.client;

import nxgen.messaging.client.config.BrokerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig
{
    @Bean
    @ConfigurationProperties(prefix = "nxgen.messaging.client.tests.brokers")
    public BrokerProperties brokerProperties()
    {
        return new BrokerProperties();
    }
}
