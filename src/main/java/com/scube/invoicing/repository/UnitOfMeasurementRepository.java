package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scube.invoicing.entity.UnitOfMeasurementEntity;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurementEntity, String> {
	
	UnitOfMeasurementEntity getUnitOfMeasurementEntityById(long id);

}
