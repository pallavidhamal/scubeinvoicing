package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.LastIssuanceEntity;

@Repository
public interface LastIssuanceRepository extends JpaRepository<LastIssuanceEntity, String> {
	
	List<LastIssuanceEntity> findByRecordDate(String recordDate);
	
}
