package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;

public class ReportFocusedProductMapper {
	
	public static List<ReportResponseDto> toFocusedProductRespDto(List<Map<String, String>> listResponseFocusedProductBlue) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ReportResponseDto> reportResponseDtosList = new ArrayList<ReportResponseDto>();
		
		for(int i=0; i<listResponseFocusedProductBlue.size(); i++) {
			
			ReportResponseDto reportResponseDtoObj = new ReportResponseDto();
			
			final ReportResponseDto pojo = mapper.convertValue(listResponseFocusedProductBlue.get(i), ReportResponseDto.class);
			
			System.out.println("-----------" + pojo.getReport_date());
			
			reportResponseDtoObj.setMat_code(pojo.getMat_code());
			reportResponseDtoObj.setMat_Desc(pojo.getMat_Desc());
			reportResponseDtoObj.setQuantity(pojo.getQuantity());
			reportResponseDtoObj.setReport_date(pojo.getReport_date());
			reportResponseDtoObj.setColor(pojo.getColor());
			reportResponseDtoObj.setUnit(pojo.getUnit());
			reportResponseDtoObj.setUom(pojo.getUom());
			reportResponseDtoObj.setUnit_price(pojo.getUnit_price());
			reportResponseDtoObj.setCur_inv_days(pojo.getCur_inv_days());
			reportResponseDtoObj.setCur_inv_units(pojo.getCur_inv_units());
			reportResponseDtoObj.setExcess_value(pojo.getExcess_value());
			
			reportResponseDtosList.add(reportResponseDtoObj);
			
		}
		
		return reportResponseDtosList;
		
	}

}
