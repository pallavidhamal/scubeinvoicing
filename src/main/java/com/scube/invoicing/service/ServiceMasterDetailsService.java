package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.ServiceMasterResponseDto;
import com.scube.invoicing.dto.incoming.ServiceMasterIncomingDto;

public interface ServiceMasterDetailsService {
	
	boolean addServiceInfoDetails(@Valid ServiceMasterIncomingDto serviceMasterIncomingDto);
	
	boolean updateServiceInfoDetails(@Valid ServiceMasterIncomingDto serviceMasterIncomingDto);
	
	boolean deleteServiceInfoByServiceID(String serviceID);
	
	ServiceMasterResponseDto getServiceInfoByServiceID(String serviceID);
	
	List<ServiceMasterResponseDto> getAllServiceInfo();

}
