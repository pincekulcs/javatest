package com.hg.medicalevolution.javatest.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "measurement", schema = "medical_evolution")
@SequenceGenerator(name = "sequence", sequenceName = "measurement_measurement_id_seq", schema = "medical_evolution", allocationSize = 50)
@JsonPropertyOrder({"epoch", "field", "created", "floatValue"})
public class MeasurementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name = "measurement_id")
	private Long measurementId;
	
	@Column (name = "field", nullable = false, length = 100)
	private String field;
	
	@Column (name = "created_timestamp", nullable = false)
	@JsonIgnore
	private Timestamp createdTimestamp;
	
	@Column (name = "float_value", nullable = false)
	private Float value;
	
	@Column (name = "epoch", nullable = false)
	private Long epoch;
	
	@Transient
	private Long created;
	
	public MeasurementEntity() {
		
	}
	
	public MeasurementEntity(String field, Float floatValue) {
		super();
		this.field = field;
		this.createdTimestamp = Timestamp.from(Instant.now());
		this.value = floatValue;
		this.epoch = getEpochOfDay(createdTimestamp);
		this.created = createdTimestamp.getTime();
	}

	@JsonIgnore
	public Long getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Long measurementId) {
		this.measurementId = measurementId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
		this.setEpoch(getEpochOfDay(createdTimestamp));
		this.created = createdTimestamp.getTime();
	}

	@JsonProperty(value = "floatValue")
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Long getEpoch() {
		return epoch;
	}

	public void setEpoch(Long epoch) {
		this.epoch = epoch;
	}
	
	public Long getCreated() {
		return created != null ? created : createdTimestamp.getTime();
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	protected Long getEpochOfDay(Timestamp created) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(created.toInstant(), ZoneId.of("UTC"));
		return localDateTime.toLocalDate().toEpochDay();
	}
}
