package com.scube.invoicing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, String> {

	@Query(value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date,report_date as rd,Brown as brown,Red as red, Green as green, Yellow as yellow, Blue as blue, "
			+ "Orange as orange FROM daily_item_colorcount_rows where report_date >= (?1) and report_date <= (?2) order by rd asc;", nativeQuery = true)
	List<Map<String, String>>  getItemColorCountByDateRange(String fromdate,String todate);
	
	//BrownInr ,BlueInr,RedInr,GreenInr,YellowInr,GreyInr
	//dto addition needed
	// Value in INR for each color product
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd,round(BrownInr/100000,2) as brown_value, round(RedInr/100000,2) as red_value, "
			+ "round(GreenInr/100000,2) as green_value, round(YellowInr/100000,2) as yellow_value, round(BlueInr/100000,2) as blue_value, round(OrangeInr/100000,2) as orange_value FROM daily_item_colorcount_rows "
			+ "where report_date >= (?1) and report_date <= (?2) order by rd asc;", nativeQuery = true)
	List<Map<String, String>> getValueInINRForEachColorItem(String fromDate, String toDate);
	
	//focused prodcut red or grey
	//select * from calculate_inv where report_date=(select max(report_date) from calculate_inv) and color in ('RED','GREY')
	@Query (value = "SELECT DATE_FORMAT(report_date, '%Y-%m-%d') as report_date,mat_code, mat_Desc, uom, custcast(unit_price ) as unit_price, custcast(cur_inv_days ) as cur_inv_days, custcast(cur_inv_units ) as cur_inv_units, color"
			+ " FROM rm_inventory_control where report_date=(select max(report_date) from rm_inventory_control) and color in ('RED','ORANGE');", nativeQuery = true)
	List<Map<String, String>> getFocusedProductByColorRedAndGrey();
	
	//get material detail by code, quantity
	
	
	//focused prodcut blue
		//select * from calculate_inv where report_date=(select max(report_date) from calculate_inv) and color in ('Blue')
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, mat_code, mat_Desc, uom, custcast(unit_price ) as unit_price, custcast(cur_inv_days ) as cur_inv_days, custcast(cur_inv_units ) as cur_inv_units, color "
			+ "FROM rm_inventory_control where report_date=(select max(report_date) from rm_inventory_control) and color in ('BLUE');", nativeQuery = true)
	List<Map<String, String>> getFocusedProductDetailsByColorBlue();

	//get material detail by code, quantity
		//date is report date
		//quantity means cur_inv_units, value means cur_inv_lacs
		//select * from calculate_inv where mat_code='RBRWR-M04-001' order by report_date desc limit 10
		@Query (value = "SELECT mat_code, custcast(cur_inv_lacs ) as cur_inv_lacs, custcast(cur_inv_units ) as cur_inv_units, DATE_FORMAT(report_date, '%Y-%m-%d') as report_date from rm_inventory_control where mat_code=(?1) order by report_date asc limit 30;", nativeQuery = true)
		List<Map<String, String>>  getMaterialDetails(String mat_code);
	
	//excess value report
	//input mat code,from date , to date
	//select *, (cur_inv_units-max_invnorm_units)*unit_price as excess_value from calculate_inv where  report_date >= (?1) and report_date <= (?2) and color in ('BLUE') and mat_code='(?3)'
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, round(((cur_inv_units-max_invnorm_units)*unit_price)/100000,2) as excess_value from rm_inventory_control where report_date >= (?1) "
			+ "and report_date <= (?2) and color in ('BLUE') and mat_code=(?3) order by rd asc;", nativeQuery = true)
	List<Map<String, String>> getExcessValuReportByProductAndDateRange(String fromDate, String toDate, String mat_code);
	
	
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, sum(round(((cur_inv_units-max_invnorm_units)*unit_price)/100000,2)) as excess_value "
			+ "from rm_inventory_control where report_date >= (?1) and report_date <= (?2)  and color in ('BLUE') group by report_date order by rd asc;", nativeQuery = true)
	List<Map<String, String>> getExcessValuReportByDefaultAll(String fromDate, String toDate);
	
	@Query (value = "SELECT DATE_FORMAT(report_date, '%Y-%m-%d') as report_date,round(((cur_inv_units-max_invnorm_units)*unit_price)/100000,2) as excess_value from rm_inventory_control where report_date >= (?1) and "
			+ "report_date <= (?2) and color in ('BLUE') order by report_date asc;", nativeQuery = true)
	List<Map<String, String>> getExcessValuReportByDateRange(String fromDate, String toDate);
	
	
	//orders to be placed
	//priority means color column
		//select po_relcan_units,pvo_relcan_lacs from calculate_inv where report_date=(select max(report_date) from calculate_inv) and  SIGN(po_relcan_units)=1 
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, mat_code, mat_Desc, uom, custcast(unit_price ) as unit_price, reg_inter, kept_instock, readily_avlble, custcast(avgcons_perdayunits ) as avgcons_perdayunits, leadtime, transtime, custcast(IFNULL(supplier_moq,0) )  as supplier_moq,  custcast(eoq ) as eoq, "
			+ " custcast(orders_piplinenorm_lacs ) as orders_piplinenorm_lacs, custcast(cur_orders_pipe_lacs ) as cur_orders_pipe_lacs, custcast(safety_factor ) as safety_factor,custcast(IFNULL( max_invnorm_plantpipeline_units,0) ) as max_invnorm_plantpipeline_units, custcast(cur_inv_units ) as cur_inv_units, custcast(cur_inv_lacs ) as cur_inv_lacs, custcast(ss_norm_units ) as ss_norm_units , custcast(ss_norm_units_lacs ) as ss_norm_units_lacsss_norm_units_lacs, fs_days, custcast(IFNULL(max_invnorm_units,0) ) as max_invnorm_units,custcast(IFNULL( max_invnorm_lacs,0) ) as max_invnorm_lacs ,"
			+ " custcast(IFNULL(avg_invnorm_units,0) ) as avg_invnorm_units, custcast(IFNULL(orders_piplinenorm_units,0) ) as orders_piplinenorm_units, custcast(cur_orders_pipe_units ) as cur_orders_pipe_units, custcast(maxinvnorm_curinv_curpiporders ) as maxinvnorm_curinv_curpiporders,custcast(IFNULL( po_relcan_units,0) ) as po_relcan_units, custcast(IFNULL(po_relcan_lacs,0) ) as po_relcan_lacs, custcast(avg_cons_lacs ) as avg_cons_lacs, cur_inv_days, custcast(IFNULL(maxinvnorm_curinvlacs,0) ) as maxinvnorm_curinvlacs,IFNULL( DATE_FORMAT(last_rcpt_date, '%d-%m-%Y'),'') as last_rcpt_date, last_rcpt_date as lrd, "
			+ " custcast(IFNULL(last_rcpt_qty,0) ) as last_rcpt_qty, custcast(IFNULL(recqty_by_max_moeq,0) ) as recqty_by_max_moeq,IFNULL( DATE_FORMAT(last_issue_date, '%d-%m-%Y'),'') as last_issue_date,  IFNULL(last_issue_date,'') as lid,custcast(IFNULL( last_issue_qty,0) ) as last_issue_qty,  custcast(IFNULL(issued_qty,0) ) as issued_qty, custcast(IFNULL(days_after_issue,0) ) as days_after_issue, color ,sortcolor from rm_inventory_control"
			+ " where report_date=(select max(report_date) from rm_inventory_control) and SIGN(po_relcan_units)=1 order by sortcolor " , nativeQuery = true)
	List <Map<String,String>> getOrdersToBePlaced(); //order by lrd and lid and rd desc
	
	
	//orders to be cancelled
			//select po_relcan_units from calculate_inv where report_date=(select max(report_date) from calculate_inv) and  SIGN(po_relcan_units)=-1
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, mat_code, mat_Desc, uom, custcast(unit_price ) as unit_price, reg_inter, kept_instock, readily_avlble, custcast(avgcons_perdayunits ) as avgcons_perdayunits, leadtime, transtime, custcast(IFNULL(supplier_moq,0) )  as supplier_moq,  custcast(eoq ) as eoq, "
			+ " custcast(orders_piplinenorm_lacs ) as orders_piplinenorm_lacs, custcast(cur_orders_pipe_lacs ) as cur_orders_pipe_lacs, custcast(safety_factor ) as safety_factor,custcast(IFNULL( max_invnorm_plantpipeline_units,0) ) as max_invnorm_plantpipeline_units, custcast(cur_inv_units ) as cur_inv_units, custcast(cur_inv_lacs ) as cur_inv_lacs, custcast(ss_norm_units ) as ss_norm_units , custcast(ss_norm_units_lacs ) as ss_norm_units_lacsss_norm_units_lacs, fs_days, custcast(IFNULL(max_invnorm_units,0) ) as max_invnorm_units,custcast(IFNULL( max_invnorm_lacs,0) ) as max_invnorm_lacs ,"
			+ " custcast(IFNULL(avg_invnorm_units,0) ) as avg_invnorm_units, custcast(IFNULL(orders_piplinenorm_units,0) ) as orders_piplinenorm_units, custcast(cur_orders_pipe_units ) as cur_orders_pipe_units, custcast(maxinvnorm_curinv_curpiporders ) as maxinvnorm_curinv_curpiporders,custcast(IFNULL( po_relcan_units,0) ) as po_relcan_units, custcast(IFNULL(po_relcan_lacs,0) ) as po_relcan_lacs, custcast(avg_cons_lacs ) as avg_cons_lacs, cur_inv_days, custcast(IFNULL(maxinvnorm_curinvlacs,0) ) as maxinvnorm_curinvlacs,IFNULL( DATE_FORMAT(last_rcpt_date, '%d-%m-%Y'),'') as last_rcpt_date, last_rcpt_date as lrd, "
			+ " custcast(IFNULL(last_rcpt_qty,0) ) as last_rcpt_qty, custcast(IFNULL(recqty_by_max_moeq,0) ) as recqty_by_max_moeq,IFNULL( DATE_FORMAT(last_issue_date, '%d-%m-%Y'),'') as last_issue_date,  IFNULL(last_issue_date,'') as lid,custcast(IFNULL( last_issue_qty,0) ) as last_issue_qty,  custcast(IFNULL(issued_qty,0) ) as issued_qty, custcast(IFNULL(days_after_issue,0) ) as days_after_issue, color ,sortcolor from rm_inventory_control"
			+ " where report_date=(select max(report_date) from rm_inventory_control) and SIGN(po_relcan_units)=-1 order by sortcolor desc" , nativeQuery = true)
	List<Map<String, String>> getOrdersToBeCancelledList();//order by color desc
	
	//material received and issued
		//select * from calculate_inv where report_date=(select max(report_date) from calculate_inv)
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, mat_code, mat_Desc, uom, DATE_FORMAT(last_rcpt_date, '%d-%m-%Y') as last_rcpt_date, custcast(last_rcpt_qty ) as last_rcpt_qty, DATE_FORMAT(last_issue_date, '%d-%m-%Y') as last_issue_date,custcast(IFNULL( last_issue_qty,0)) as last_issue_qty,custcast(IFNULL( issued_qty,0) ) as issued_qty , custcast(recqty_by_max_moeq) as recqty_by_max_moeq, days_after_issue from rm_inventory_control "
			+ " where report_date=(select max(report_date) from rm_inventory_control);" , nativeQuery = true)
	List<Map<String, String>> getMaterialReceivedAndIssued();
	
	
	// Run the procedure 
	@Query (value = "Call import_dailydata(?);", nativeQuery = true)
	List<Map<String, Object>> getDailyDataListForAllExcels(String reportDate);
	
	// Run the procedure 
	@Query (value = "Call import_import_item_dailydata(?);", nativeQuery = true)
	List<Map<String, Object>> callImportItemProcedure(String reportDate);
	
	
	
	//PO Details for Orders to be Cancelled Products
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, po_type, po_no, po_date, Cond_format(line_no) as line_no, item, Cond_format(current_order_pipeline_units) as current_order_pipeline_units , Cond_format(rate) as rate, currency from all_current_orders_pipeline_details\r\n"
			+ "where item=(?1) and report_date=(select max(report_date) from rm_inventory_control);", nativeQuery = true)
	List<Map<String, String>> getPODetailsForOrdersToBeCancelled(String mat_code);
	
	@Query (value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date from rm_inventory_control where report_date=(select max(report_date) FROM rm_inventory_control);", nativeQuery = true)
	List<Map<String, String>> getMaxReportDate();
	
	
	@Query (value = "SELECT round(sum(po_relcan_lacs),2) as po_relcan_lacs from rm_inventory_control where report_date=(select max(report_date) from rm_inventory_control) and SIGN(po_relcan_units)=1  ;", nativeQuery = true)
	List<Map<String, String>> getTotalValueOrdersToBeReleasedForOrdersToBePlacedReportInINR();
	
	@Query (value = "SELECT round(sum(po_relcan_lacs),2) as po_relcan_lacs from rm_inventory_control where report_date=(select max(report_date) from rm_inventory_control) and SIGN(po_relcan_units)=-1 ;", nativeQuery = true)
	List<Map<String, String>> getTotalValueOrdersToBeReleasedForOrdersToBeCancelledReportInINR();
	
	
	@Query(value = "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, mat_code, mat_Desc, custcast(avgcons_perdayunits ) as avgcons_perdayunits, DATE_FORMAT(last_issue_date, '%d-%m-%y') as last_issue_date, "
			+ "last_issue_date as ld, custcast(last_issue_qty ) as issued_qty from rm_inventory_control where mat_code=(?1) order by rd and ld desc limit 15;", nativeQuery = true)
	List<Map<String, String>> getMaterialReportByLast15Days(String mat_code);
	
	
	@Query(value = "SELECT DATE_FORMAT(record_date, '%d-%m-%Y') as record_date, record_date as rd, material_code, order_units " + 
			"from all_current_orders_pipeline where record_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByReportDate(String record_date);
	
	@Query(value= "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, po_type, po_no, po_date, Cond_format(line_no) as line_no, item, Cond_format(current_order_pipeline_units) as current_order_pipeline_units , Cond_format(rate) as rate, currency from all_current_orders_pipeline_details where report_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordDate(String report_date);
	
	@Query(value = "SELECT DATE_FORMAT(record_date, '%d-%m-%Y') as record_date, record_date as rd, material_code, issue_date, issuance_qty " + 
			"from all_last_issuance where record_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordForLastIssuance(String record_date);
	
	@Query(value = "SELECT DATE_FORMAT(record_date, '%d-%m-%Y') as record_date, record_date as rd, material_code, receipt_date, receipt_qty " + 
			"from all_last_purchase_receipt where record_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordDateForLastPurchase(String record_date);
	
	@Query(value= "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, material_code, material_desc, UOM, COND_FORMAT(unit_price) as unit_price, cur_inv_units, item_purchase_category from all_material_current_inventory where report_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordDateForMaterialCurrentInventory(String report_date);
	
	@Query(value= "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, material_code, mat_desc, domestic_imported, high_seas_transit_days, inland_transit_days, leadtime_days, material_port_ICD_units, material_high_seas_units, material_in_inland_transit_units, port_ICD_clrnc_days, total_purchase_odrs_units,unexecuted_orders_units from all_mat_import where report_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordDateForImportedMaterial(String report_date);
	
	@Query(value= "SELECT DATE_FORMAT(report_date, '%d-%m-%Y') as report_date, report_date as rd, mat_code, mat_Desc, uom, unit_price, reg_inter, kept_instock, readily_avlble, avgcons_perdayunits,"
			+ "leadtime, transtime, supplier_moq, eoq, fs_days, safety_factor, ss_norm_units, ss_norm_units_lacs, max_invnorm_units, max_invnorm_lacs, avg_invnorm_units, orders_piplinenorm_units, orders_piplinenorm_lacs,cur_inv_units,cur_inv_lacs, "
			+ "cur_orders_pipe_units, cur_orders_pipe_lacs, maxinvnorm_curinv_curpiporders, po_relcan_units, po_relcan_lacs, avg_cons_lacs, cur_inv_days, maxinvnorm_curinvlacs, last_rcpt_date, last_rcpt_qty, recqty_by_max_moeq, last_issue_date, "
			+ "last_issue_qty, issued_qty, days_after_issue, color from rm_inventory_control where report_date= (?1);" , nativeQuery = true)
	List<Map<String, String>> getRecordsByRecordDateForRmInventory(String report_date);
	
	@Query(value="SELECT * FROM rm_import_sufficiency where report_date=(?1);",nativeQuery = true )
	List<Map<String, String>> getrecordsByRecordDateForRmImportSufficiency(String report_date);
	
	@Query(value="SELECT mat_code,inventory, plant,  plant_it, plant_it_icd,    plant_it_icd_hs,    plant_it_icd_hs_opcd,   report_date,uom FROM rm_formatted_import_sufficiency  where report_date=(select max(report_date) from rm_formatted_import_sufficiency)",nativeQuery = true )
	List<Map<String, String>> getAllRmFormattedImportSuffData();
	
	@Query(value="SELECT mat_code,inventory, plant,  plant_it, plant_it_icd,    plant_it_icd_hs,    plant_it_icd_hs_opcd,   report_date,uom FROM rm_formatted_import_sufficiency  where report_date=(select max(report_date) from rm_formatted_import_sufficiency) and mat_code=(?1);",nativeQuery = true )
	List<Map<String, String>> getRmFormattedImportSuffDataByMatCode(String mat_code);
	
	
	
	
}