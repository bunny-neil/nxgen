package nxgen.kafka.client.config;

public class TopicProperties
{
    private String topicName;
    private String keySerializerClassName;
    private String valueSerializerClassName;
    private String keyDeserializerClassName;
    private String valueDeserializerClassName;

    public String getTopicName()
    {
        return topicName;
    }

    public void setTopicName(String topicName)
    {
        this.topicName = topicName;
    }

    public String getKeySerializerClassName()
    {
        return keySerializerClassName;
    }

    public void setKeySerializerClassName(String keySerializerClassName)
    {
        this.keySerializerClassName = keySerializerClassName;
    }

    public String getValueSerializerClassName()
    {
        return valueSerializerClassName;
    }

    public void setValueSerializerClassName(String valueSerializerClassName)
    {
        this.valueSerializerClassName = valueSerializerClassName;
    }

    public String getKeyDeserializerClassName()
    {
        return keyDeserializerClassName;
    }

    public void setKeyDeserializerClassName(String keyDeserializerClassName)
    {
        this.keyDeserializerClassName = keyDeserializerClassName;
    }

    public String getValueDeserializerClassName()
    {
        return valueDeserializerClassName;
    }

    public void setValueDeserializerClassName(String valueDeserializerClassName)
    {
        this.valueDeserializerClassName = valueDeserializerClassName;
    }
}
