package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerInvoiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CustomerInvoiceService {
	
	// Create New Invoice and Service Info
	CustomerInvoiceResponseDto addCustomerInvoiceAndServiceInfo(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	// Update Invoice and Service Info
	CustomerInvoiceResponseDto updateCustomerInvoiceAndServiceInfo(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	// Remove Customer Invoice and Service Info 
	boolean removeCustomerInvoiceAndServiceInfo(String customerID, String invoiceNo);
	
	CustomerInvoiceResponseDto getCustomerInvoiceAndServiceResponseDto(String customerID);
	
	// Get Customer Invoice and Service Details By Invoice ID
	CustomerInvoiceResponseDto getCustomerInvoiceListByInvoiceID (String invoiceID);
	
	// Get List of Customer Invoice By Customer ID
	List<CustomerInvoiceResponseDto> getCustomerInvoiceListByCustomerID(String customerID);
	
//	List<CustomerServiceResponseDto> getAllCustomerServiceDetailsByCustomerServiceId(String customerServiceID);
	
	CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);
	
	List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity);
	
	// Get All Customer Invoice List for Data Table
	List<CustomerInvoiceResponseDto> getAllCustomerInvoiceAndServiceList();
	
	// Get All Customer Invoice List By Date Range
	List<CustomerInvoiceResponseDto> getAllCustomerInvoiceListByDateRange(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	boolean updateCustomerInvoicePaymentStatus(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);

}
