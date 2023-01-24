package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.util.StringNullEmpty;

public class OrdersToBeCancelledAndPlacedMapper {
	
	public static List<ReportResponseDto> toOrdersToBeCancelledReportList(List<Map<String, String>> ordersToBeCancelledList) {
		// TODO Auto-generated method stub
		final ObjectMapper objectMapper = new ObjectMapper(); 
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<ReportResponseDto>();
		
		for (int i = 0; i < ordersToBeCancelledList.size(); i++) {
			
			ReportResponseDto reportResponseDtoObject = new ReportResponseDto();
			final ReportResponseDto pojo = objectMapper.convertValue(ordersToBeCancelledList.get(i), ReportResponseDto.class);
			
			reportResponseDtoObject.setMat_code(pojo.getMat_code());
			reportResponseDtoObject.setMat_Desc(pojo.getMat_Desc());
			reportResponseDtoObject.setUom(pojo.getUom());
			reportResponseDtoObject.setUnit_price(pojo.getUnit_price());
			reportResponseDtoObject.setColor(pojo.getColor());
			reportResponseDtoObject.setSortcolor(pojo.getSortcolor());
			reportResponseDtoObject.setMat_Desc(pojo.getMat_Desc());
			reportResponseDtoObject.setReport_date(pojo.getReport_date());
			reportResponseDtoObject.setPo_relcan_units(pojo.getPo_relcan_units());
			reportResponseDtoObject.setPo_relcan_lacs(pojo.getPo_relcan_lacs());
			reportResponseDtoObject.setEoq(pojo.getEoq());
			reportResponseDtoObject.setSafety_factor(pojo.getSafety_factor());
			reportResponseDtoObject.setAvg_cons_lacs(pojo.getAvg_cons_lacs());
			reportResponseDtoObject.setAvg_invnorm_units(pojo.getAvg_invnorm_units());
			reportResponseDtoObject.setAvgcons_perdayunits(pojo.getAvgcons_perdayunits());
			reportResponseDtoObject.setCur_inv_days(pojo.getCur_inv_days());
			reportResponseDtoObject.setCur_inv_lacs(pojo.getCur_inv_lacs());
			reportResponseDtoObject.setCur_inv_units(pojo.getCur_inv_units());
			reportResponseDtoObject.setCur_orders_pipe_lacs(pojo.getCur_orders_pipe_lacs());
			reportResponseDtoObject.setCur_orders_pipe_units(pojo.getCur_orders_pipe_units());
			reportResponseDtoObject.setOrders_piplinenorm_units(pojo.getOrders_piplinenorm_units());
			reportResponseDtoObject.setOrders_piplinenorm_lacs(pojo.getOrders_piplinenorm_lacs());
			reportResponseDtoObject.setCurrency(pojo.getCurrency());
			reportResponseDtoObject.setDays_after_issue(pojo.getDays_after_issue());
			reportResponseDtoObject.setFs_days(pojo.getFs_days());
			reportResponseDtoObject.setLast_issue_date(pojo.getLast_issue_date());
			reportResponseDtoObject.setLast_issue_qty(pojo.getLast_issue_qty());
			reportResponseDtoObject.setLast_rcpt_date(pojo.getLast_rcpt_date());
			reportResponseDtoObject.setLast_rcpt_qty(pojo.getLast_rcpt_qty());
			reportResponseDtoObject.setLeadtime(pojo.getLeadtime());
			reportResponseDtoObject.setTranstime(pojo.getTranstime());
			reportResponseDtoObject.setSupplier_moq(pojo.getSupplier_moq());
			reportResponseDtoObject.setIssued_qty(pojo.getIssued_qty());
			reportResponseDtoObject.setReg_inter(pojo.getReg_inter());
			reportResponseDtoObject.setSs_norm_units(pojo.getSs_norm_units());
			reportResponseDtoObject.setSs_norm_units_lacs(pojo.getSs_norm_units_lacs());
			reportResponseDtoObject.setRecqty_by_max_moeq(pojo.getRecqty_by_max_moeq());
			reportResponseDtoObject.setMax_invnorm_lacs(pojo.getMax_invnorm_lacs());
			reportResponseDtoObject.setMax_invnorm_plantpipeline_units(pojo.getMax_invnorm_plantpipeline_units());
			reportResponseDtoObject.setMax_invnorm_units(pojo.getMax_invnorm_units());
			reportResponseDtoObject.setMaxinvnorm_curinv_curpiporders(pojo.getMaxinvnorm_curinv_curpiporders());
			reportResponseDtoObject.setMaxinvnorm_curinvlacs(pojo.getMaxinvnorm_curinvlacs());
			
					
			reportResponseDtoList.add(reportResponseDtoObject);
		}
		
		return reportResponseDtoList;

	}
	
	
	public static List<ReportResponseDto> toMaterialCodePODetailsList(List<Map<String, String>> materialCodePODetailsList) {
		// TODO Auto-generated method stub
		final ObjectMapper objectMapper = new ObjectMapper(); 
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<ReportResponseDto>();
		
		for (int i = 0; i < materialCodePODetailsList.size(); i++) {
			
			ReportResponseDto reportResponseDtoObject = new ReportResponseDto();
			final ReportResponseDto pojo = objectMapper.convertValue(materialCodePODetailsList.get(i), ReportResponseDto.class);
			
			reportResponseDtoObject.setPo_type(pojo.getPo_type());
			reportResponseDtoObject.setPo_no(pojo.getPo_no());
			reportResponseDtoObject.setPo_date(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getPo_date()));
			reportResponseDtoObject.setLine_no(pojo.getLine_no());
			reportResponseDtoObject.setItem(pojo.getItem());
			reportResponseDtoObject.setRate(pojo.getRate());
			reportResponseDtoObject.setCurrent_order_pipeline_units(pojo.getCurrent_order_pipeline_units());
			reportResponseDtoObject.setCurrency(pojo.getCurrency());
			reportResponseDtoObject.setReport_date(pojo.getReport_date());
					
			reportResponseDtoList.add(reportResponseDtoObject);
		}
		
		return reportResponseDtoList;

	}
	
	
	public static ReportResponseDto toMaxReportDateFromRMTable(List<Map<String, String>> maxReportDateFromRMTable) {
		
		final ObjectMapper objectMapper = new ObjectMapper(); 
		ReportResponseDto reportResponseDtoObject = new ReportResponseDto();
		
		for (int i = 0; i < maxReportDateFromRMTable.size(); i++) {

			final ReportResponseDto pojo = objectMapper.convertValue(maxReportDateFromRMTable.get(i), ReportResponseDto.class);
			
			reportResponseDtoObject.setReport_date(pojo.getReport_date());
			reportResponseDtoObject.setPo_relcan_lacs(pojo.getPo_relcan_lacs());
			
		}
		
		return reportResponseDtoObject;
	
	}
	
	
	public static ReportResponseDto toTotalValueOfOrdersReleasedInOrdersPlacedAndCancelled(List<Map<String, String>> maxReportDateFromRMTable) {
		
		final ObjectMapper objectMapper = new ObjectMapper(); 
		ReportResponseDto reportResponseDtoObject = new ReportResponseDto();
		
		for (int i = 0; i < maxReportDateFromRMTable.size(); i++) {

			final ReportResponseDto pojo = objectMapper.convertValue(maxReportDateFromRMTable.get(i), ReportResponseDto.class);
			
//			reportResponseDtoObject.setReport_date(pojo.getReport_date());
			reportResponseDtoObject.setPo_relcan_lacs(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getPo_relcan_lacs()));
			
		}
		
		return reportResponseDtoObject;
	}
	
}
