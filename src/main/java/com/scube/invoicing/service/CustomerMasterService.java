package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerMasterResponseDto;
import com.scube.invoicing.dto.incoming.CustomerMasterIncomingDto;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CustomerMasterService {
	
	boolean addCustomerInfoDetails(@Valid CustomerMasterIncomingDto customerMasterIncomingDto);
	
	boolean updateCustomerInfoDetails(@Valid CustomerMasterIncomingDto customerMasterIncomingDto);
	
	boolean deleteCustomerInfoDetailsByCustomerId(String customerID);
	
	CustomerMasterResponseDto getCustomerInfoDetailsByCustomerId(String customerID);
	
	CustomerMasterEntity getCustomerDetailsByCustomerId(String customerID);
	
	List<CustomerMasterResponseDto> getAllCustomerInfoDetails();

}
