/**
 * 
 */
package com.hg.medicalevolution.javatest.entity;

import java.sql.Timestamp;

/**
 * @author Harangozó Gergő
 * 
 * Interface for mapping the return values of the postgresql function 
 * medical_evolution.load_measurement_aggregate(input_field character, input_granulation character, input_numberOfSeries integer)
 * 
 */
public interface MeasurementAggregate {
	
	public Long getMeasurementIdValue();
	public Timestamp getCreatedTimestampValue();
	public Double getAverageValue();
	public Double getMinimumValue();
	public Double getMaximumValue();
	public Integer getCountValue();
}
