package com.hg.medicalevolution.javatest.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hg.medicalevolution.javatest.entity.MeasurementAggregate;
import com.hg.medicalevolution.javatest.entity.MeasurementEntity;
import com.hg.medicalevolution.javatest.entity.MeasurementTimestampValuePair;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Long> {
	
	@Query("SELECT m FROM MeasurementEntity m "
		+ "WHERE m.field = ?1 and m.epoch = ?2 and m.createdTimestamp >= ?3 and m.createdTimestamp < ?4")
	public List<MeasurementEntity> findByFieldAndCreatedBetween(String field, long epoch, Timestamp fromInclusive, Timestamp toExclusive);
	
	@Query("SELECT m FROM MeasurementEntity m WHERE m.epoch = ?1 and m.field = ?2")
	public List<MeasurementTimestampValuePair> findByEpochAndField(long epoch, String field);
	
	@Query(nativeQuery = true, value = "SELECT * FROM medical_evolution.load_measurement_aggregate(:field, :granulation, :numberOfSeries)")
	public List<MeasurementAggregate> executeMeasurementAggregateQuery(@Param("field") String field, @Param("granulation") String granulation,
																		@Param("numberOfSeries") int numberOfSeries); 
	
	@Query(nativeQuery = true, value = "SELECT medical_evolution.load_last_float_value(:field)")
	public Float executeLastFloatValueQuery(@Param("field") String field);
}
