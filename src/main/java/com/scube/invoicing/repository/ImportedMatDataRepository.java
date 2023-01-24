package com.scube.invoicing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ImportedMatDataEntity;

@Repository
public interface ImportedMatDataRepository extends JpaRepository<ImportedMatDataEntity, String> {
	
	List<ImportedMatDataEntity> findByReportDate(String reportDate);

	//SELECT * FROM mat_import;
	@Query ( value = "SELECT material_code, mat_desc, domestic_imported, high_seas_transit_days, inland_transit_days, leadtime_days, material_port_ICD_units,"
			+ "material_high_seas_units, material_in_inland_transit_units, port_ICD_clrnc_days, total_purchase_odrs_units, unexecuted_orders_units,report_date FROM mat_import "
			+ "WHERE domestic_imported='IMPORT';", nativeQuery = true)
	List <Map<String,String>> getImportedMaterialData();
}
