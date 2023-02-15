package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerServiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerServiceIncomingDto;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CustomerInvoiceService {
	
	CustomerServiceResponseDto addCustomerInvoiceAndServiceData(@Valid CustomerServiceIncomingDto customerServiceIncomingDto);
	
	boolean updateCustomerServiceInfo(@Valid CustomerServiceIncomingDto customerServiceIncomingDto);
	
	boolean removeCustomerInvoiceAndServiceData(String customerID, String invoiceNo);
	
//	CustomerServiceResponseDto getCustomerServiceDetailsByCustomerServiceId(String customerServiceID);
	
//	List<CustomerServiceResponseDto> getAllCustomerServiceDetailsByCustomerServiceId(String customerServiceID);
	
	CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);
	
	List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity);
	
	List<CustomerServiceResponseDto> getAllCustomerInvoiceAndServiceList();

}
