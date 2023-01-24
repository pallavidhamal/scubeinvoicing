package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;

public class MaterialDetailsMapper {

	public static List<ReportResponseDto> toMaterialDetailsRespDto(List<Map<String, String>> listResponseMaterialDetails) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtosList = new ArrayList<ReportResponseDto>();
		
		for(int i=0; i<listResponseMaterialDetails.size(); i++) {
			
			ReportResponseDto reportResponseDtoObj = new ReportResponseDto();
			
			final ReportResponseDto pojo = mapper.convertValue(listResponseMaterialDetails.get(i), ReportResponseDto.class);
			
			reportResponseDtoObj.setMat_code(pojo.getMat_code());
			reportResponseDtoObj.setReport_date(pojo.getReport_date());
			reportResponseDtoObj.setCur_inv_units(pojo.getCur_inv_units());
			reportResponseDtoObj.setCur_inv_lacs(pojo.getCur_inv_lacs());
			
			reportResponseDtosList.add(reportResponseDtoObj);
			
		}
		return reportResponseDtosList;
	}
} 
