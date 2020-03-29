package com.hg.medicalevolution.javatest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hg.medicalevolution.javatest.entity.MeasurementEntity;
import com.hg.medicalevolution.javatest.exception.NotFoundException;
import com.hg.medicalevolution.javatest.service.MeasurementService;
import com.hg.medicalevolution.javatest.service.util.MeasurementAggregationGranulation;

@RestController
public class MeasurementController {
	
	@Autowired
	MeasurementService measurementService;

	@GetMapping("/measurement/create/{fieldName}/{value}")
	public MeasurementEntity createMeasurement(@PathVariable String fieldName, @PathVariable float value ) {
		MeasurementEntity entity = measurementService.createMeasurement(fieldName, value);
		return entity;
	}
	
	@GetMapping("/measurement/delete/{epoch}/{fieldName}/{fromTimestamp}/{toTimestamp}")
	public List<MeasurementEntity> deleteMeasurements(@PathVariable String fieldName, @PathVariable long epoch, 
													  @PathVariable long fromTimestamp, @PathVariable long toTimestamp) throws NotFoundException{
		List<MeasurementEntity> entityList = 
				measurementService.deleteMeasurements(fieldName, epoch, fromTimestamp, toTimestamp);
		if(entityList.isEmpty()) {
			throw new NotFoundException();
		}
		return entityList;
	}
	
	@GetMapping("/measurement/list/{epoch}/{fieldName}/{fromTimestamp}/{toTimestamp}")
	public List<MeasurementEntity> listMeasurements(@PathVariable String fieldName, @PathVariable long epoch, 
													@PathVariable long fromTimestamp, @PathVariable long toTimestamp) throws NotFoundException{
		List<MeasurementEntity> entityList = 
				measurementService.listMeasurements(fieldName, epoch, fromTimestamp, toTimestamp);
		if(entityList.isEmpty()) {
			throw new NotFoundException();
		}
		return entityList;
	}	
	
	@GetMapping("/measurement/load/{epoch}/{fieldName}")
	public List<List<Number>> loadMeasurementTimestampValuePairs(@PathVariable String fieldName, @PathVariable long epoch) {
		List<List<Number>> listOfNumberList = measurementService.listMeasurementTimestampValuePairs(epoch, fieldName);
		if(listOfNumberList.isEmpty()) {
			throw new NotFoundException();
		}
		return listOfNumberList;
	}

	@GetMapping("/measurement/load/{fieldName}/{granulation}/{numberOfSeries}")
	public List<List<Number>> loadMeasurementAggregates(@PathVariable String fieldName, @PathVariable MeasurementAggregationGranulation granulation,
														@PathVariable int numberOfSeries){
		List<List<Number>>  listOfNumberList = measurementService.loadMeasurementAggregates(fieldName, granulation, numberOfSeries);
		if(listOfNumberList != null && listOfNumberList.isEmpty()) {
			throw new NotFoundException();
		}
		return listOfNumberList;
	}
	
	@GetMapping("/measurement/load/{fieldName}")
	public float loadLastFloatValue(@PathVariable String fieldName) {
		Float value = measurementService.loadLastFloatValue(fieldName);
		if(value == null) {
			throw new NotFoundException();
		}
		return value;
	}
}
