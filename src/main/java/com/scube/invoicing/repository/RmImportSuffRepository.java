package com.scube.invoicing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.RmImportSuffEntity;

@Repository
public interface RmImportSuffRepository extends JpaRepository<RmImportSuffEntity, String>{
	
	
	//select report_date from rm_import_sufficiency where  report_date=(select max(report_date));
	@Query( value =" SELECT mat_code, material_description, custcast(plant_mnstknorm1 ) as plant_mnstknorm1, custcast(plant_mxstknorm1 ) as plant_mxstknorm1 ,custcast(plant_mnstknorm2 ) as plant_mnstknorm2, custcast(plant_mxstknorm2 ) as plant_mxstknorm2, custcast(plant_curinv_transitpipeorders ) as plant_curinv_transitpipeorders, custcast(plant_mnstknorm3 ) as plant_mnstknorm3,"
			+ " custcast(plant_mxstknorm3 ) as plant_mxstknorm3, custcast(plant_cum_curinv_transitpipeorders ) as plant_cum_curinv_transitpipeorders, custcast(inland_mnstknorm1 ) as inland_mnstknorm1, custcast(inland_mnstknorm2 ) as inland_mnstknorm2, custcast(inland_curinv_transitpipeorders ) as inland_curinv_transitpipeorders, custcast(inland_mnstknorm3 ) as inland_mnstknorm3, custcast(inland_mxstknorm3 ) as inland_mxstknorm3, "
			+ " custcast(plantit_cum_curinv_transitpipeorders ) as plantit_cum_curinv_transitpipeorders, custcast(icd_mnstknorm1 ) as icd_mnstknorm1, custcast(icd_mnstknorm2 ) as icd_mnstknorm2 , custcast(icd_curinv_transitpipeorders ) as icd_curinv_transitpipeorders, custcast(icd_mnstknorm3 ) as icd_mnstknorm3, custcast(icd_mxstknorm3 ) as icd_mxstknorm3, custcast(plantiticd_cum_curinv_transitpipeorders ) as plantiticd_cum_curinv_transitpipeorders, custcast(hs_mnstknorm1 ) as hs_mnstknorm1, custcast(hs_mnstknorm2 ) as hs_mnstknorm2,"
			+ "custcast(hs_curinv_transitpipeorders ) as hs_curinv_transitpipeorders, custcast(hs_mnstknorm3 ) as hs_mnstknorm3, custcast(hs_mxstknorm3 ) as hs_mxstknorm3, custcast(plantiticdhs_cum_curinv_transitpipeorders ) as plantiticdhs_cum_curinv_transitpipeorders, custcast(opcd_mnstknorm1 ) as opcd_mnstknorm1, custcast(opcd_mnstknorm2 ) as opcd_mnstknorm2, custcast(opcd_curinv_transitpipeorders ) as opcd_curinv_transitpipeorders,"
			+ " custcast(opcd_mnstknorm3 ) as opcd_mnstknorm3, custcast(opcd_mxstknorm3 ) as opcd_mxstknorm3, custcast(plantiticdhsopcd_cum_curinv_transitpipeorders ) as plantiticdhsopcd_cum_curinv_transitpipeorders , plant_suff, inland_suff, icd_suff, hs_suff, opcd_suff, report_date "
			+ " FROM rm_import_sufficiency WHERE mat_code = (?1) and report_date=(select max(report_date) FROM rm_import_sufficiency);", nativeQuery = true)
	//@Query (value = "SELECT * from rm_import_sufficiency where report_date=(select max(report_date) FROM rm_import_sufficiency);", nativeQuery = true)
	List<Map<String, String>> getRmImportSuffDataByMatCode(String materialCode);
	
	@Query( value =" SELECT mat_code, material_description, custcast(plant_mnstknorm1 ) as plant_mnstknorm1, custcast(plant_mxstknorm1 ) as plant_mxstknorm1 ,custcast(plant_mnstknorm2 ) as plant_mnstknorm2, custcast(plant_mxstknorm2 ) as plant_mxstknorm2, custcast(plant_curinv_transitpipeorders ) as plant_curinv_transitpipeorders, custcast(plant_mnstknorm3 ) as plant_mnstknorm3,"
			+ " custcast(plant_mxstknorm3 ) as plant_mxstknorm3, custcast(plant_cum_curinv_transitpipeorders ) as plant_cum_curinv_transitpipeorders, custcast(inland_mnstknorm1 ) as inland_mnstknorm1, custcast(inland_mnstknorm2 ) as inland_mnstknorm2, custcast(inland_curinv_transitpipeorders ) as inland_curinv_transitpipeorders, custcast(inland_mnstknorm3 ) as inland_mnstknorm3, custcast(inland_mxstknorm3 ) as inland_mxstknorm3, "
			+ " custcast(plantit_cum_curinv_transitpipeorders ) as plantit_cum_curinv_transitpipeorders, custcast(icd_mnstknorm1 ) as icd_mnstknorm1, custcast(icd_mnstknorm2 ) as icd_mnstknorm2 , custcast(icd_curinv_transitpipeorders ) as icd_curinv_transitpipeorders, custcast(icd_mnstknorm3 ) as icd_mnstknorm3, custcast(icd_mxstknorm3 ) as icd_mxstknorm3, custcast(plantiticd_cum_curinv_transitpipeorders ) as plantiticd_cum_curinv_transitpipeorders, custcast(hs_mnstknorm1 ) as hs_mnstknorm1, custcast(hs_mnstknorm2 ) as hs_mnstknorm2,"
			+ "custcast(hs_curinv_transitpipeorders ) as hs_curinv_transitpipeorders, custcast(hs_mnstknorm3 ) as hs_mnstknorm3, custcast(hs_mxstknorm3 ) as hs_mxstknorm3, custcast(plantiticdhs_cum_curinv_transitpipeorders ) as plantiticdhs_cum_curinv_transitpipeorders, custcast(opcd_mnstknorm1 ) as opcd_mnstknorm1, custcast(opcd_mnstknorm2 ) as opcd_mnstknorm2, custcast(opcd_curinv_transitpipeorders ) as opcd_curinv_transitpipeorders,"
			+ " custcast(opcd_mnstknorm3 ) as opcd_mnstknorm3, custcast(opcd_mxstknorm3 ) as opcd_mxstknorm3, custcast(plantiticdhsopcd_cum_curinv_transitpipeorders ) as plantiticdhsopcd_cum_curinv_transitpipeorders , plant_suff, inland_suff, icd_suff, hs_suff, opcd_suff, report_date " + 
			" FROM rm_import_sufficiency WHERE report_date=(select max(report_date) FROM rm_import_sufficiency);", nativeQuery = true)
	List<Map<String, String>> getAllRmImportSuffData();

	
	/*
	 * @Query(value =
	 * "SELECT mat_code, material_description, plant_mnstknorm1, plant_mxstknorm1,plant_mnstknorm2, plant_mxstknorm2, plant_curinv_transitpipeorders, plant_mnstknorm3,\r\n"
	 * +
	 * "plant_mxstknorm3, plant_cum_curinv_transitpipeorders, inland_mnstknorm1, inland_mnstknorm2, inland_curinv_transitpipeorders, inland_mnstknorm3, inland_mxstknorm3, \r\n"
	 * +
	 * "plantit_cum_curinv_transitpipeorders, icd_mnstknorm1, icd_mnstknorm2, icd_curinv_transitpipeorders, icd_mnstknorm3, icd_mxstknorm3, plantiticd_cum_curinv_transitpipeorders,\r\n"
	 * +
	 * "hs_mnstknorm1, hs_mnstknorm2, hs_curinv_transitpipeorders, hs_mnstknorm3, hs_mxstknorm3, plantiticdhs_cum_curinv_transitpipeorders, opcd_mnstknorm1, opcd_mnstknorm2, opcd_curinv_transitpipeorders,\r\n"
	 * +
	 * "opcd_mnstknorm3, opcd_mxstknorm3, plantiticdhsopcd_cum_curinv_transitpipeorders, plant_suff, inland_suff, icd_suff, hs_suff, opcd_suff, report_date "
	 * +
	 * "FROM rm_import_sufficiency WHERE report_date=(select max(report_date) FROM rm_import_sufficiency);"
	 * , nativeQuery = true)
	 * 
	 */
}
