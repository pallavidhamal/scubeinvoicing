package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ReportResponseDto;


public class ReportColorCountMapper {

	public static List<ReportResponseDto> tocolorCountRespDto(List<Map<String, String>>  list) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); 
		List<ReportResponseDto> resp = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) 
		{
			ReportResponseDto obj=new ReportResponseDto();
			
		
			final ReportResponseDto pojo = mapper.convertValue(list.get(i), ReportResponseDto.class);
			
			obj.setReport_date(pojo.getReport_date());
			
			if(Integer.parseInt(pojo.getBlue())>0) {		
				obj.setBlue(pojo.getBlue());
			}
			
			if(Integer.parseInt(pojo.getBrown())>0) {
				obj.setBrown(pojo.getBrown());				
			}
			
			if(Integer.parseInt(pojo.getRed())>0) {		
				obj.setRed(pojo.getRed());	
			}
			
			if(Integer.parseInt(pojo.getOrange())>0) {				
				obj.setOrange(pojo.getOrange());		
			}
			
			if(Integer.parseInt(pojo.getYellow())>0) {
				obj.setYellow(pojo.getYellow());				
			}
			
			if(Integer.parseInt(pojo.getGreen())>0) {				
				obj.setGreen(pojo.getGreen());			
			}	
			
			resp.add(obj);
		}
		return resp;
	}
	
	public static List<ReportResponseDto> toValueInINRForEachItemInColorCode(List<Map<String, String>> valueForEachItemInColorCodeList) {
		// TODO Auto-generated method stub
		final ObjectMapper objectMapper = new ObjectMapper(); 
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();
		
		for (int i = 0; i < valueForEachItemInColorCodeList.size(); i++) {
			
			ReportResponseDto reportResponseDtoObject = new ReportResponseDto();
			
			final ReportResponseDto pojo = objectMapper.convertValue(valueForEachItemInColorCodeList.get(i), ReportResponseDto.class);
			
			reportResponseDtoObject.setReport_date(pojo.getReport_date());
			
			if(Double.parseDouble(pojo.getBlue_value())>0) {		
				reportResponseDtoObject.setBlue_value(pojo.getBlue_value());
			}
			
			if(Double.parseDouble(pojo.getBrown_value())>0) {
				reportResponseDtoObject.setBrown_value(pojo.getBrown_value());				
			}
			
			if(Double.parseDouble(pojo.getRed_value())>0) {		
				reportResponseDtoObject.setRed_value(pojo.getRed_value());	
			}
			
			if(Double.parseDouble(pojo.getOrange_value())>0) {				
				reportResponseDtoObject.setOrange_value(pojo.getOrange_value());		
			}
			
			if(Double.parseDouble(pojo.getYellow_value())>0) {
				reportResponseDtoObject.setYellow_value(pojo.getYellow_value());				
			}
			
			if(Double.parseDouble(pojo.getGreen_value())>0) {				
				reportResponseDtoObject.setGreen_value(pojo.getGreen_value());			
			}	
			
			reportResponseDtoList.add(reportResponseDtoObject);
		}
		return reportResponseDtoList;
	}
	
}
