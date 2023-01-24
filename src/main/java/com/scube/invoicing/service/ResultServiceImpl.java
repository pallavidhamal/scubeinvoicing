package com.scube.invoicing.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.service.ResultServiceImpl;

@Service
public class ResultServiceImpl implements ResultService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);
	
	@Autowired
	ReportRepository reportRepository;
	
	
	@Override
	public List<ReportResponseDto> runProcedureForImportedExcelDataDate()  {
		// TODO Auto-generated method stub
		
		logger.info("---------------------------" + "ResultServiceImpl runProcedureForImportedExcelDataDate" + "--------------------------");
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<ReportResponseDto>();
		
		Date inputDate = new Date();
		String inputDateInString = new SimpleDateFormat("yyyy-MM-dd").format(inputDate);
		
		logger.info("--------------" + "Date Format" + inputDate + "-----------" + "String Format" + inputDateInString);
		
		List<Map<String, Object>> procedureRecordsList = reportRepository.getDailyDataListForAllExcels(inputDateInString);
		
		logger.info("--------" + " List Size " + procedureRecordsList.size());
		
		for(int i=0; i<procedureRecordsList.size(); i++) {
			
			final ReportResponseDto reportdataObj = mapper.convertValue(procedureRecordsList.get(i), ReportResponseDto.class);
			ReportResponseDto reportResponseDto = new ReportResponseDto();
			
			reportResponseDto.setMat_code(reportdataObj.getMat_code());
			reportResponseDto.setMat_Desc(reportdataObj.getMat_Desc());
			reportResponseDto.setUom(reportdataObj.getUom());
			reportResponseDto.setUnit_price(reportdataObj.getUnit_price());
			reportResponseDto.setColor(reportdataObj.getColor());
			reportResponseDto.setMat_Desc(reportdataObj.getMat_Desc());
			reportResponseDto.setReport_date(reportdataObj.getReport_date());
			reportResponseDto.setPo_relcan_units(reportdataObj.getPo_relcan_units());
			reportResponseDto.setPo_relcan_lacs(reportdataObj.getPo_relcan_lacs());
			reportResponseDto.setEoq(reportdataObj.getEoq());
			reportResponseDto.setSafety_factor(reportdataObj.getSafety_factor());
			reportResponseDto.setAvg_cons_lacs(reportdataObj.getAvg_cons_lacs());
			reportResponseDto.setAvg_invnorm_units(reportdataObj.getAvg_invnorm_units());
			reportResponseDto.setAvgcons_perdayunits(reportdataObj.getAvgcons_perdayunits());
			reportResponseDto.setCur_inv_days(reportdataObj.getCur_inv_days());
			reportResponseDto.setCur_inv_lacs(reportdataObj.getCur_inv_lacs());
			reportResponseDto.setCur_inv_units(reportdataObj.getCur_inv_units());
			reportResponseDto.setCur_orders_pipe_lacs(reportdataObj.getCur_orders_pipe_lacs());
			reportResponseDto.setCur_orders_pipe_units(reportdataObj.getCur_orders_pipe_units());
			reportResponseDto.setOrders_piplinenorm_units(reportdataObj.getOrders_piplinenorm_units());
			reportResponseDto.setOrders_piplinenorm_lacs(reportdataObj.getOrders_piplinenorm_lacs());
			reportResponseDto.setCurrency(reportdataObj.getCurrency());
			reportResponseDto.setDays_after_issue(reportdataObj.getDays_after_issue());
			reportResponseDto.setFs_days(reportdataObj.getFs_days());
			reportResponseDto.setLast_issue_date(reportdataObj.getLast_issue_date());
			reportResponseDto.setLast_issue_qty(reportdataObj.getLast_issue_qty());
			reportResponseDto.setLast_rcpt_date(reportdataObj.getLast_rcpt_date());
			reportResponseDto.setLast_rcpt_qty(reportdataObj.getLast_rcpt_qty());
			reportResponseDto.setLeadtime(reportdataObj.getLeadtime());
			reportResponseDto.setTranstime(reportdataObj.getTranstime());
			reportResponseDto.setSupplier_moq(reportdataObj.getSupplier_moq());
			reportResponseDto.setIssued_qty(reportdataObj.getIssued_qty());
			reportResponseDto.setReg_inter(reportdataObj.getReg_inter());
			reportResponseDto.setSs_norm_units(reportdataObj.getSs_norm_units());
			reportResponseDto.setSs_norm_units_lacs(reportdataObj.getSs_norm_units_lacs());
			reportResponseDto.setRecqty_by_max_moeq(reportdataObj.getRecqty_by_max_moeq());
			reportResponseDto.setMax_invnorm_lacs(reportdataObj.getMax_invnorm_lacs());
			reportResponseDto.setMax_invnorm_plantpipeline_units(reportdataObj.getMax_invnorm_plantpipeline_units());
			reportResponseDto.setMax_invnorm_units(reportdataObj.getMax_invnorm_units());
			reportResponseDto.setMaxinvnorm_curinv_curpiporders(reportdataObj.getMaxinvnorm_curinv_curpiporders());
			reportResponseDto.setMaxinvnorm_curinvlacs(reportdataObj.getMaxinvnorm_curinvlacs());
			
			reportResponseDtoList.add(reportResponseDto);
			
		}
		
		return reportResponseDtoList;
		
	}


	@Override
	public List<ReportResponseDto> callImportItemProcedure() {
		// TODO Auto-generated method stub
		
		logger.info("---------------------------" + "ResultServiceImpl callImportItemProcedure" + "--------------------------");
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<ReportResponseDto>();
		
		Date inputDate = new Date();
		String inputDateInString = new SimpleDateFormat("yyyy-MM-dd").format(inputDate);
		
		logger.info("--------------" + "Date Format" + inputDate + "-----------" + "String Format" + inputDateInString);
		
		List<Map<String, Object>> procedureRecordsList = reportRepository.callImportItemProcedure(inputDateInString);
		
		logger.info("--------" + " List Size " + procedureRecordsList.size());
				
		return null;
	}

}
