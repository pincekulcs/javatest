package com.hg.medicalevolution.javatest.entity;

/**
 * @author Harangozó Gergő
 * 
 * Interface for mapping the return values of the postgresql medical_evolution.load_last_float_value(input_field character)
 *
 */
public interface MeasurementTimestampValuePair {

	public Long getCreated();
	public Float getValue();
}
