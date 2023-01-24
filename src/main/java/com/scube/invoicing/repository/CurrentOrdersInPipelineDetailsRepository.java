package com.scube.invoicing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scube.invoicing.entity.CurrentOrdersInPipelineDetailsEntity;

public interface CurrentOrdersInPipelineDetailsRepository extends JpaRepository<CurrentOrdersInPipelineDetailsEntity, String> {
	
	List<CurrentOrdersInPipelineDetailsEntity> findByReportDate(String reportDate);
	
	@Query (value = "SELECT date_format(report_date, '%d-%m-%Y') as report_date, report_date as rd, po_type, po_no, po_date, custcast(line_no ) as line_no, item, current_order_pipeline_units, custcast(round(rate, 1) ) as rate, currency from all_current_orders_pipeline_details "
			+ "where report_date=(?1);", nativeQuery = true)
	List<Map<String, String>> getCurrentOrdersPipelineDetailsByDateRange(String fromDate);
	
	@Query (value = "SELECT date_format(report_date, '%d-%m-%Y') as report_date, report_date as rd, po_type, po_no, po_date, custcast(line_no ) as line_no, item, current_order_pipeline_units, custcast(round(rate, 1) ) as rate, currency from all_current_orders_pipeline_details "
			+ "where report_date=(?1) and item=(?2);", nativeQuery = true)
	List<Map<String, String>> getCurrentOrdersPipelineDetailsByReportDateAndMaterialCode(String fromDate, String materialCode);

}
