package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the IoT type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "IoTS", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class IoT implements Model {
  public static final QueryField ID = field("IoT", "id");
  public static final QueryField DATETIME = field("IoT", "datetime");
  public static final QueryField TEMPERATURE = field("IoT", "temperature");
  public static final QueryField HUMIDITY = field("IoT", "humidity");
  public static final QueryField NOTE = field("IoT", "note");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime datetime;
  private final @ModelField(targetType="Float") Double temperature;
  private final @ModelField(targetType="Float") Double humidity;
  private final @ModelField(targetType="String") String note;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Temporal.DateTime getDatetime() {
      return datetime;
  }
  
  public Double getTemperature() {
      return temperature;
  }
  
  public Double getHumidity() {
      return humidity;
  }
  
  public String getNote() {
      return note;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private IoT(String id, Temporal.DateTime datetime, Double temperature, Double humidity, String note) {
    this.id = id;
    this.datetime = datetime;
    this.temperature = temperature;
    this.humidity = humidity;
    this.note = note;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      IoT ioT = (IoT) obj;
      return ObjectsCompat.equals(getId(), ioT.getId()) &&
              ObjectsCompat.equals(getDatetime(), ioT.getDatetime()) &&
              ObjectsCompat.equals(getTemperature(), ioT.getTemperature()) &&
              ObjectsCompat.equals(getHumidity(), ioT.getHumidity()) &&
              ObjectsCompat.equals(getNote(), ioT.getNote()) &&
              ObjectsCompat.equals(getCreatedAt(), ioT.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), ioT.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDatetime())
      .append(getTemperature())
      .append(getHumidity())
      .append(getNote())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("IoT {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("datetime=" + String.valueOf(getDatetime()) + ", ")
      .append("temperature=" + String.valueOf(getTemperature()) + ", ")
      .append("humidity=" + String.valueOf(getHumidity()) + ", ")
      .append("note=" + String.valueOf(getNote()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static IoT justId(String id) {
    return new IoT(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      datetime,
      temperature,
      humidity,
      note);
  }
  public interface BuildStep {
    IoT build();
    BuildStep id(String id);
    BuildStep datetime(Temporal.DateTime datetime);
    BuildStep temperature(Double temperature);
    BuildStep humidity(Double humidity);
    BuildStep note(String note);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Temporal.DateTime datetime;
    private Double temperature;
    private Double humidity;
    private String note;
    @Override
     public IoT build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new IoT(
          id,
          datetime,
          temperature,
          humidity,
          note);
    }
    
    @Override
     public BuildStep datetime(Temporal.DateTime datetime) {
        this.datetime = datetime;
        return this;
    }
    
    @Override
     public BuildStep temperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }
    
    @Override
     public BuildStep humidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }
    
    @Override
     public BuildStep note(String note) {
        this.note = note;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Temporal.DateTime datetime, Double temperature, Double humidity, String note) {
      super.id(id);
      super.datetime(datetime)
        .temperature(temperature)
        .humidity(humidity)
        .note(note);
    }
    
    @Override
     public CopyOfBuilder datetime(Temporal.DateTime datetime) {
      return (CopyOfBuilder) super.datetime(datetime);
    }
    
    @Override
     public CopyOfBuilder temperature(Double temperature) {
      return (CopyOfBuilder) super.temperature(temperature);
    }
    
    @Override
     public CopyOfBuilder humidity(Double humidity) {
      return (CopyOfBuilder) super.humidity(humidity);
    }
    
    @Override
     public CopyOfBuilder note(String note) {
      return (CopyOfBuilder) super.note(note);
    }
  }
  
}
