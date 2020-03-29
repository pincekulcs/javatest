package com.hg.medicalevolution.javatest.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hg.medicalevolution.javatest.entity.MeasurementAggregate;
import com.hg.medicalevolution.javatest.entity.MeasurementEntity;
import com.hg.medicalevolution.javatest.entity.MeasurementTimestampValuePair;
import com.hg.medicalevolution.javatest.repository.MeasurementRepository;
import com.hg.medicalevolution.javatest.service.util.MeasurementAggregationGranulation;

@Component
public class MeasurementServiceImplementation implements MeasurementService{
	
	@Autowired
	MeasurementRepository repository;
	
	@Override
	public MeasurementEntity createMeasurement(String field, float value) {
		MeasurementEntity entity = new MeasurementEntity(field, value);
		repository.save(entity);
		return entity;
	}

	@Override
	public List<MeasurementEntity> deleteMeasurements(String field, long epoch, long fromInclusive, long toExclusive) {
		List<MeasurementEntity> entityList = new ArrayList<>();
		entityList = repository.findByFieldAndCreatedBetween(field, epoch, Timestamp.from(Instant.ofEpochMilli(fromInclusive)),
															Timestamp.from(Instant.ofEpochMilli(toExclusive)));		
		repository.deleteInBatch(entityList);
		return entityList;
	}

	@Override
	public List<MeasurementEntity> listMeasurements(String field, long epoch, long fromInclusive, long toExclusive) {
		List<MeasurementEntity> entityList = new ArrayList<>();
		entityList = repository.findByFieldAndCreatedBetween(field, epoch, Timestamp.from(Instant.ofEpochMilli(fromInclusive)),
															Timestamp.from(Instant.ofEpochMilli(toExclusive)));
		return entityList;
	}
	
	@Override
	public List<List<Number>> listMeasurementTimestampValuePairs(long epoch, String field) {
		List<MeasurementTimestampValuePair> measurementTimestampValuePairs = new ArrayList<>();
		measurementTimestampValuePairs = repository.findByEpochAndField(epoch, field);
		List<List<Number>> listOfNumberList = createListOfNumberListFromMeasurementTimestampValuePair(measurementTimestampValuePairs);
		return listOfNumberList;
	}

	@Override
	public List<List<Number>> loadMeasurementAggregates(String field, MeasurementAggregationGranulation granulation, int numberOfSeries) {
		List<MeasurementAggregate> measurementAggregates = new ArrayList<>();
		measurementAggregates = repository.executeMeasurementAggregateQuery(field, granulation.getGranulationValue(), numberOfSeries);
		List<List<Number>> listOfNumberList = createListOfNumberListFromMeasurementAggregate(measurementAggregates, granulation);
		return listOfNumberList;
	}

	@Override
	public Float loadLastFloatValue(String field) {
		return repository.executeLastFloatValueQuery(field);
	}
	
	private List<List<Number>> createListOfNumberListFromMeasurementTimestampValuePair(List<MeasurementTimestampValuePair> timestampValuePairs){
		List<List<Number>> listOfNumberList = new ArrayList<List<Number>>();
		for(MeasurementTimestampValuePair pair : timestampValuePairs) {
			List<Number> list = new ArrayList<Number>();
			list.add(pair.getCreated());
			list.add(pair.getValue());
			listOfNumberList.add(list);
		}
		return listOfNumberList;
	}
	
	private List<List<Number>> createListOfNumberListFromMeasurementAggregate(List<MeasurementAggregate> measurementAggregates, 
																				MeasurementAggregationGranulation granulation){
		List<List<Number>> listOfNumberList = new ArrayList<List<Number>>();
		for(MeasurementAggregate aggreagte : measurementAggregates) {
			List<Number> list = new ArrayList<Number>();
			list.add(aggreagte.getCreatedTimestampValue().getTime());
			list.add(aggreagte.getAverageValue());
			list.add(aggreagte.getMinimumValue());
			list.add(aggreagte.getMaximumValue());
			list.add(aggreagte.getCountValue());
			listOfNumberList.add(list);
		}
		return listOfNumberList;
	}
}
