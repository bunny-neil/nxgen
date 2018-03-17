/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package nxgen.messaging.client.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Event extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6483377665658596492L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Event\",\"namespace\":\"nxgen.messaging.client.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"sender\",\"type\":[\"null\",\"bytes\"]},{\"name\":\"payload\",\"type\":[\"null\",\"bytes\"]},{\"name\":\"dateCreated\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Event> ENCODER =
      new BinaryMessageEncoder<Event>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Event> DECODER =
      new BinaryMessageDecoder<Event>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Event> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Event> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Event>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Event to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Event from a ByteBuffer. */
  public static Event fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence id;
  @Deprecated public java.lang.CharSequence type;
  @Deprecated public java.nio.ByteBuffer sender;
  @Deprecated public java.nio.ByteBuffer payload;
  @Deprecated public long dateCreated;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Event() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param type The new value for type
   * @param sender The new value for sender
   * @param payload The new value for payload
   * @param dateCreated The new value for dateCreated
   */
  public Event(java.lang.CharSequence id, java.lang.CharSequence type, java.nio.ByteBuffer sender, java.nio.ByteBuffer payload, java.lang.Long dateCreated) {
    this.id = id;
    this.type = type;
    this.sender = sender;
    this.payload = payload;
    this.dateCreated = dateCreated;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return type;
    case 2: return sender;
    case 3: return payload;
    case 4: return dateCreated;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: type = (java.lang.CharSequence)value$; break;
    case 2: sender = (java.nio.ByteBuffer)value$; break;
    case 3: payload = (java.nio.ByteBuffer)value$; break;
    case 4: dateCreated = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.CharSequence getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.CharSequence value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'sender' field.
   * @return The value of the 'sender' field.
   */
  public java.nio.ByteBuffer getSender() {
    return sender;
  }

  /**
   * Sets the value of the 'sender' field.
   * @param value the value to set.
   */
  public void setSender(java.nio.ByteBuffer value) {
    this.sender = value;
  }

  /**
   * Gets the value of the 'payload' field.
   * @return The value of the 'payload' field.
   */
  public java.nio.ByteBuffer getPayload() {
    return payload;
  }

  /**
   * Sets the value of the 'payload' field.
   * @param value the value to set.
   */
  public void setPayload(java.nio.ByteBuffer value) {
    this.payload = value;
  }

  /**
   * Gets the value of the 'dateCreated' field.
   * @return The value of the 'dateCreated' field.
   */
  public java.lang.Long getDateCreated() {
    return dateCreated;
  }

  /**
   * Sets the value of the 'dateCreated' field.
   * @param value the value to set.
   */
  public void setDateCreated(java.lang.Long value) {
    this.dateCreated = value;
  }

  /**
   * Creates a new Event RecordBuilder.
   * @return A new Event RecordBuilder
   */
  public static nxgen.messaging.client.avro.Event.Builder newBuilder() {
    return new nxgen.messaging.client.avro.Event.Builder();
  }

  /**
   * Creates a new Event RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Event RecordBuilder
   */
  public static nxgen.messaging.client.avro.Event.Builder newBuilder(nxgen.messaging.client.avro.Event.Builder other) {
    return new nxgen.messaging.client.avro.Event.Builder(other);
  }

  /**
   * Creates a new Event RecordBuilder by copying an existing Event instance.
   * @param other The existing instance to copy.
   * @return A new Event RecordBuilder
   */
  public static nxgen.messaging.client.avro.Event.Builder newBuilder(nxgen.messaging.client.avro.Event other) {
    return new nxgen.messaging.client.avro.Event.Builder(other);
  }

  /**
   * RecordBuilder for Event instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Event>
    implements org.apache.avro.data.RecordBuilder<Event> {

    private java.lang.CharSequence id;
    private java.lang.CharSequence type;
    private java.nio.ByteBuffer sender;
    private java.nio.ByteBuffer payload;
    private long dateCreated;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(nxgen.messaging.client.avro.Event.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.sender)) {
        this.sender = data().deepCopy(fields()[2].schema(), other.sender);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.payload)) {
        this.payload = data().deepCopy(fields()[3].schema(), other.payload);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.dateCreated)) {
        this.dateCreated = data().deepCopy(fields()[4].schema(), other.dateCreated);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Event instance
     * @param other The existing instance to copy.
     */
    private Builder(nxgen.messaging.client.avro.Event other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.sender)) {
        this.sender = data().deepCopy(fields()[2].schema(), other.sender);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.payload)) {
        this.payload = data().deepCopy(fields()[3].schema(), other.payload);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.dateCreated)) {
        this.dateCreated = data().deepCopy(fields()[4].schema(), other.dateCreated);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.CharSequence getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder setType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'sender' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getSender() {
      return sender;
    }

    /**
      * Sets the value of the 'sender' field.
      * @param value The value of 'sender'.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder setSender(java.nio.ByteBuffer value) {
      validate(fields()[2], value);
      this.sender = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'sender' field has been set.
      * @return True if the 'sender' field has been set, false otherwise.
      */
    public boolean hasSender() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'sender' field.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder clearSender() {
      sender = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'payload' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getPayload() {
      return payload;
    }

    /**
      * Sets the value of the 'payload' field.
      * @param value The value of 'payload'.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder setPayload(java.nio.ByteBuffer value) {
      validate(fields()[3], value);
      this.payload = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'payload' field has been set.
      * @return True if the 'payload' field has been set, false otherwise.
      */
    public boolean hasPayload() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'payload' field.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder clearPayload() {
      payload = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'dateCreated' field.
      * @return The value.
      */
    public java.lang.Long getDateCreated() {
      return dateCreated;
    }

    /**
      * Sets the value of the 'dateCreated' field.
      * @param value The value of 'dateCreated'.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder setDateCreated(long value) {
      validate(fields()[4], value);
      this.dateCreated = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'dateCreated' field has been set.
      * @return True if the 'dateCreated' field has been set, false otherwise.
      */
    public boolean hasDateCreated() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'dateCreated' field.
      * @return This builder.
      */
    public nxgen.messaging.client.avro.Event.Builder clearDateCreated() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Event build() {
      try {
        Event record = new Event();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.sender = fieldSetFlags()[2] ? this.sender : (java.nio.ByteBuffer) defaultValue(fields()[2]);
        record.payload = fieldSetFlags()[3] ? this.payload : (java.nio.ByteBuffer) defaultValue(fields()[3]);
        record.dateCreated = fieldSetFlags()[4] ? this.dateCreated : (java.lang.Long) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Event>
    WRITER$ = (org.apache.avro.io.DatumWriter<Event>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Event>
    READER$ = (org.apache.avro.io.DatumReader<Event>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
