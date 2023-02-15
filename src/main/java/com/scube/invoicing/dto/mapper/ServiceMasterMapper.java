package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import com.scube.invoicing.dto.ServiceMasterResponseDto;
import com.scube.invoicing.entity.ServiceMasterEntity;

public class ServiceMasterMapper {
	
	public static ServiceMasterResponseDto toServiceMasterResponseDto (ServiceMasterEntity serviceMasterEntity) {
		
		return new ServiceMasterResponseDto()
				.setServiceID(serviceMasterEntity.getId())
				.setServiceName(serviceMasterEntity.getServiceName())
				.setServiceStatus(serviceMasterEntity.getStatus());
	}
	
	public static List<ServiceMasterResponseDto> toAllServiceDataList(List<ServiceMasterEntity> serviceMasterEntities) {
		// TODO Auto-generated method stub
		
		List<ServiceMasterResponseDto> serviceMasterResponseDtos= new ArrayList<ServiceMasterResponseDto>();
		for(ServiceMasterEntity serviceMasterEntity : serviceMasterEntities)
		{
			serviceMasterResponseDtos.add(toServiceMasterResponseDto(serviceMasterEntity));			
		}
				
		return serviceMasterResponseDtos;
	} 
		
}
