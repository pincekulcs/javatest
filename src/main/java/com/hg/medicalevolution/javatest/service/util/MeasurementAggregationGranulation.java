package com.hg.medicalevolution.javatest.service.util;

public enum MeasurementAggregationGranulation {
	
	MINUTES ("minute"),
	HOURS ("hour"),
	DAYS ("day");

	String granulationValue;

	private MeasurementAggregationGranulation(String granulationValue) {
		this.granulationValue = granulationValue;
	}

	public String getGranulationValue() {
		return granulationValue;
	}
}
