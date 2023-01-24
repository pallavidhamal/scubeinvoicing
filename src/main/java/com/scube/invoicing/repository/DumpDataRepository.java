package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scube.invoicing.entity.DumpDataEntity;

public interface DumpDataRepository  extends JpaRepository<DumpDataEntity, String> {
	
	@Query (value ="SELECT * FROM rm_inventory_control where report_date=(?1);" , nativeQuery = true)
	List<DumpDataEntity> getDumpDataByReportDate( String reportDate);
	

}
