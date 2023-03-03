package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerInvoiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CustomerInvoiceService {
	
	CustomerInvoiceResponseDto addCustomerInvoiceAndServiceData(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	boolean updateCustomerServiceInfo(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	boolean removeCustomerInvoiceAndServiceData(String customerID, String invoiceNo);
	
	CustomerInvoiceResponseDto getCustomerInvoiceAndServiceResponseDto(String customerID);
	
	List<CustomerInvoiceResponseDto> getCustomerInvoiceListByCustomerID(String customerID);
	
//	List<CustomerServiceResponseDto> getAllCustomerServiceDetailsByCustomerServiceId(String customerServiceID);
	
	CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);
	
	List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity);
	
	List<CustomerInvoiceResponseDto> getAllCustomerInvoiceAndServiceList();
	
	boolean updateCustomerInvoicePaymentStatus(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);

}
