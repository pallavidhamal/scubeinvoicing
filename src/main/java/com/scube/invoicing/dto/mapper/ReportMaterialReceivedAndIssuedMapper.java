package com.scube.invoicing.dto.mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.util.NumberFormatUtils;
import com.scube.invoicing.util.StringNullEmpty;

public class ReportMaterialReceivedAndIssuedMapper {

	public static List<ReportResponseDto> toMaterialReceivedAndIssuedResponseDtos (List<Map<String , String>> listResponseMaterialReceivedAndIssued) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtosList = new ArrayList<ReportResponseDto>();
		
		for(int i=0; i<listResponseMaterialReceivedAndIssued.size(); i++) {
			
			ReportResponseDto reportResponseDtoObj = new ReportResponseDto();
			
			final ReportResponseDto pojo = mapper.convertValue(listResponseMaterialReceivedAndIssued.get(i), ReportResponseDto.class);
			
			reportResponseDtoObj.setMat_code(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getMat_code()));
			reportResponseDtoObj.setMat_Desc(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getMat_Desc()));
			reportResponseDtoObj.setUom(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getUom()));
			reportResponseDtoObj.setLast_rcpt_qty(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getLast_rcpt_qty()));
			reportResponseDtoObj.setLast_rcpt_date(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getLast_rcpt_date()));
			reportResponseDtoObj.setLast_issue_qty(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getLast_issue_qty()));
			reportResponseDtoObj.setLast_issue_date(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getLast_issue_date()));
			reportResponseDtoObj.setIssued_qty(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getIssued_qty()));
			reportResponseDtoObj.setRecqty_by_max_moeq(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getRecqty_by_max_moeq()));
			reportResponseDtoObj.setDays_after_issue(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getDays_after_issue()));
			
			reportResponseDtosList.add(reportResponseDtoObj);
		}
		
		return reportResponseDtosList;
	}
	
	
	public static List<ReportResponseDto> toMaterialReportForLast15DaysResponseDtos (List<Map<String , String>> materialReportResponseList) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtosList = new ArrayList<ReportResponseDto>();
		
		for(int i=0; i<materialReportResponseList.size(); i++) {
			
			ReportResponseDto reportResponseDtoObj = new ReportResponseDto();
			
			final ReportResponseDto pojo = mapper.convertValue(materialReportResponseList.get(i), ReportResponseDto.class);
			
			reportResponseDtoObj.setMat_code(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getMat_code()));
			reportResponseDtoObj.setReport_date(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getReport_date()));
			reportResponseDtoObj.setMat_Desc(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getMat_Desc()));
			reportResponseDtoObj.setLast_issue_date(pojo.getLast_issue_date());
			reportResponseDtoObj.setIssued_qty(StringNullEmpty.stringNullAndEmptyToBlank((pojo.getIssued_qty())));
			reportResponseDtoObj.setAvgcons_perdayunits(pojo.getAvgcons_perdayunits());
			
			reportResponseDtosList.add(reportResponseDtoObj);
		}
		
		return reportResponseDtosList;
	}

}
