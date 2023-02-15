package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.GSTMasterResponseDto;
import com.scube.invoicing.entity.GSTMasterEntity;

public class GSTMasterMapper {

	public static GSTMasterResponseDto toGstMasterResponseDto(GSTMasterEntity gstMasterEntity) {
		
		return new GSTMasterResponseDto()
				.setGstId(gstMasterEntity.getId())
				.setPercentValue(gstMasterEntity.getValue())
				.setDescription(gstMasterEntity.getDescription());
	}
	
	public static List<GSTMasterResponseDto> toAllGSTMasterDataList(List<GSTMasterEntity> gstMasterEntityList) {
		// TODO Auto-generated method stub
		
		List<GSTMasterResponseDto> gstMasterResponseDtosList = new ArrayList<GSTMasterResponseDto>();
		for(GSTMasterEntity gstMasterEntity : gstMasterEntityList)
		{
			gstMasterResponseDtosList.add(toGstMasterResponseDto(gstMasterEntity));			
		}
				
		return gstMasterResponseDtosList;
	} 
	
}
