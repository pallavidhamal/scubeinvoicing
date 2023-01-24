package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ExcelFileResultEntity;

@Repository
public interface MaterialAndCurrentInventoryRepository extends JpaRepository<ExcelFileResultEntity, String> {
	
	List<ExcelFileResultEntity> findExcelFileResultEntityByReportDate(String report_date);

}
