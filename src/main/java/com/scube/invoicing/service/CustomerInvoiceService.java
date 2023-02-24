package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerServiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CustomerInvoiceService {
	
	CustomerServiceResponseDto addCustomerInvoiceAndServiceData(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	boolean updateCustomerServiceInfo(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);
	
	boolean removeCustomerInvoiceAndServiceData(String customerID, String invoiceNo);
	
	List<CustomerServiceResponseDto> getCustomerInvoiceListByCustomerID(String customerID);
	
//	List<CustomerServiceResponseDto> getAllCustomerServiceDetailsByCustomerServiceId(String customerServiceID);
	
	CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);
	
	List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity);
	
	List<CustomerServiceResponseDto> getAllCustomerInvoiceAndServiceList();
	
	boolean updateCustomerInvoicePaymentStatus(@Valid CustomerInvoiceIncomingDto customerServiceIncomingDto);

}
