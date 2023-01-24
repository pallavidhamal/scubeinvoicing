package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;

public class OrderToBePlacedMapper {

	public static List<ReportResponseDto> toOrderToBePlacedResponseDtos (List<Map <String, String>> listResponseOrderToBePlaced){
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtosList = new ArrayList<ReportResponseDto>();
		
			for(int i=0; i<listResponseOrderToBePlaced.size(); i++) {
			
			ReportResponseDto reportResponseDtoObj = new ReportResponseDto();
			
			final ReportResponseDto pojo = mapper.convertValue(listResponseOrderToBePlaced.get(i), ReportResponseDto.class);
			

			reportResponseDtoObj.setMat_code(pojo.getMat_code());
			reportResponseDtoObj.setMat_Desc(pojo.getMat_Desc());
			reportResponseDtoObj.setUom(pojo.getUom());
			reportResponseDtoObj.setUnit_price(pojo.getUnit_price());
			reportResponseDtoObj.setPo_relcan_units(pojo.getPo_relcan_units());
			reportResponseDtoObj.setPo_relcan_lacs(pojo.getPo_relcan_lacs());
			reportResponseDtoObj.setReport_date(pojo.getReport_date());
			reportResponseDtoObj.setColor(pojo.getColor());
			reportResponseDtoObj.setSortcolor(pojo.getSortcolor());
			reportResponseDtoObj.setEoq(pojo.getEoq());
			reportResponseDtoObj.setSafety_factor(pojo.getSafety_factor());
			reportResponseDtoObj.setAvg_cons_lacs(pojo.getAvg_cons_lacs());
			reportResponseDtoObj.setAvg_invnorm_units(pojo.getAvg_invnorm_units());
			reportResponseDtoObj.setAvgcons_perdayunits(pojo.getAvgcons_perdayunits());
			reportResponseDtoObj.setCur_inv_days(pojo.getCur_inv_days());
			reportResponseDtoObj.setCur_inv_lacs(pojo.getCur_inv_lacs());
			reportResponseDtoObj.setCur_inv_units(pojo.getCur_inv_units());
			reportResponseDtoObj.setCur_orders_pipe_lacs(pojo.getCur_orders_pipe_lacs());
			reportResponseDtoObj.setCur_orders_pipe_units(pojo.getCur_orders_pipe_units());
			reportResponseDtoObj.setOrders_piplinenorm_units(pojo.getOrders_piplinenorm_units());
			reportResponseDtoObj.setOrders_piplinenorm_lacs(pojo.getOrders_piplinenorm_lacs());
			reportResponseDtoObj.setCurrency(pojo.getCurrency());
			reportResponseDtoObj.setDays_after_issue(pojo.getDays_after_issue());
			reportResponseDtoObj.setFs_days(pojo.getFs_days());
			reportResponseDtoObj.setLast_issue_date(pojo.getLast_issue_date());
			reportResponseDtoObj.setLast_issue_qty(pojo.getLast_issue_qty());
			reportResponseDtoObj.setLast_rcpt_date(pojo.getLast_rcpt_date());
			reportResponseDtoObj.setLast_rcpt_qty(pojo.getLast_rcpt_qty());
			reportResponseDtoObj.setLeadtime(pojo.getLeadtime());
			reportResponseDtoObj.setTranstime(pojo.getTranstime());
			reportResponseDtoObj.setSupplier_moq(pojo.getSupplier_moq());
			reportResponseDtoObj.setIssued_qty(pojo.getIssued_qty());
			reportResponseDtoObj.setReg_inter(pojo.getReg_inter());
			reportResponseDtoObj.setSs_norm_units(pojo.getSs_norm_units());
			reportResponseDtoObj.setSs_norm_units_lacs(pojo.getSs_norm_units_lacs());
			reportResponseDtoObj.setRecqty_by_max_moeq(pojo.getRecqty_by_max_moeq());
			reportResponseDtoObj.setMax_invnorm_lacs(pojo.getMax_invnorm_lacs());
			reportResponseDtoObj.setMax_invnorm_plantpipeline_units(pojo.getMax_invnorm_plantpipeline_units());
			reportResponseDtoObj.setMax_invnorm_units(pojo.getMax_invnorm_units());
			reportResponseDtoObj.setMaxinvnorm_curinv_curpiporders(pojo.getMaxinvnorm_curinv_curpiporders());
			reportResponseDtoObj.setMaxinvnorm_curinvlacs(pojo.getMaxinvnorm_curinvlacs());
		
			reportResponseDtosList.add(reportResponseDtoObj);
	}
			return reportResponseDtosList;
}

}