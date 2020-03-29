package com.hg.medicalevolution.javatest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hg.medicalevolution.javatest.entity.MeasurementEntity;
import com.hg.medicalevolution.javatest.service.util.MeasurementAggregationGranulation;

@Service
public interface MeasurementService {
	 
	public MeasurementEntity createMeasurement(String field, float value); 
	
	public List<MeasurementEntity> deleteMeasurements(String field, long epoch, long fromInclusive, long toExclusive);
	
	public List<MeasurementEntity> listMeasurements(String field, long epoch, long fromInclusive, long toExclusive);
	
	public List<List<Number>> listMeasurementTimestampValuePairs(long epoch, String field);
	
	public List<List<Number>> loadMeasurementAggregates(String field, MeasurementAggregationGranulation granulation, int numberOfSeries);
	
	public Float loadLastFloatValue(String field);
}
