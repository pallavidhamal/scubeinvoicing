package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CurrentOrdersInPipelineSummaryEntity;

@Repository
public interface CurrentOrdersInPipelineRepository extends JpaRepository<CurrentOrdersInPipelineSummaryEntity, String> {
	
	List<CurrentOrdersInPipelineSummaryEntity> findByRecordDate(String recordDate);
	
}
