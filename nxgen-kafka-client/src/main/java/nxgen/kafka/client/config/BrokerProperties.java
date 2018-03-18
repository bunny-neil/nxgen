package nxgen.kafka.client.config;

import org.apache.kafka.clients.CommonClientConfigs;

import java.util.Properties;

public class BrokerProperties
{
    private String bootstrapServers;
    private String securityProtocol;

    public String getBootstrapServers()
    {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers)
    {
        this.bootstrapServers = bootstrapServers;
    }

    public String getSecurityProtocol()
    {
        return securityProtocol;
    }

    public void setSecurityProtocol(String securityProtocol)
    {
        this.securityProtocol = securityProtocol;
    }

    public Properties toPropertiesMap()
    {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        return properties;
    }
}
