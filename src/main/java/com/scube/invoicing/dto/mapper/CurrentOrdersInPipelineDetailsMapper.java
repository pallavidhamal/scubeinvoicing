package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.CurrentOrdersInPipelineResponseDto;
import com.scube.invoicing.util.StringNullEmpty;

public class CurrentOrdersInPipelineDetailsMapper {
	
	public static List<CurrentOrdersInPipelineResponseDto> toCurrentOrdersInPipelineResponseDtoList(List<Map<String, String>> currentOrdersInPipelineDetailsEntityList) {
		
		final ObjectMapper objectMapper = new ObjectMapper(); 
		List<CurrentOrdersInPipelineResponseDto> currentOrdersResponseDtoList = new ArrayList<>();
		
		for (int i = 0; i < currentOrdersInPipelineDetailsEntityList.size(); i++) {
			
			CurrentOrdersInPipelineResponseDto currentOrdersResponseDtoObject = new CurrentOrdersInPipelineResponseDto();
			final CurrentOrdersInPipelineResponseDto pojo = objectMapper.convertValue(currentOrdersInPipelineDetailsEntityList.get(i), CurrentOrdersInPipelineResponseDto.class);
			
			currentOrdersResponseDtoObject.setId(pojo.getId());
			currentOrdersResponseDtoObject.setPo_type(pojo.getPo_type());
			currentOrdersResponseDtoObject.setPo_no(pojo.getPo_no());
			currentOrdersResponseDtoObject.setPo_date(StringNullEmpty.stringNullAndEmptyToBlank(pojo.getPo_date()));
			currentOrdersResponseDtoObject.setCurrency(pojo.getCurrency());
			currentOrdersResponseDtoObject.setRate(pojo.getRate());
			currentOrdersResponseDtoObject.setReport_date(pojo.getReport_date());
			currentOrdersResponseDtoObject.setCurrent_order_pipeline_units(pojo.getCurrent_order_pipeline_units());
			currentOrdersResponseDtoObject.setLine_no(pojo.getLine_no());
			currentOrdersResponseDtoObject.setItem(pojo.getItem());
					
			currentOrdersResponseDtoList.add(currentOrdersResponseDtoObject);
		}
		
		return currentOrdersResponseDtoList;
		
	}

}
