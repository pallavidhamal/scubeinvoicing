package com.scube.invoicing.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import com.scube.invoicing.dto.CustomerInvoiceServiceResponseDto;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;

public class CustomerInvoiceServiceMapper {

	public static CustomerInvoiceServiceResponseDto toCustomerInvoiceServiceResponseDto(CustomerInvoiceServiceEntity customerInvoiceServiceEntity) {
		return new CustomerInvoiceServiceResponseDto()
				.setInvoiceServiceID(customerInvoiceServiceEntity.getId())
				
				.setServiceID(customerInvoiceServiceEntity.getServiceMasterEntity().getId())
				.setServiceName(customerInvoiceServiceEntity.getServiceMasterEntity().getServiceName())
				
				.setHsnOrSac(customerInvoiceServiceEntity.getHsnorSac())
				.setDescription(customerInvoiceServiceEntity.getDescription())
				.setRate(customerInvoiceServiceEntity.getRate())
				.setSku(customerInvoiceServiceEntity.getSku())
				
				.setAmount(customerInvoiceServiceEntity.getAmount())
				.setGstID(customerInvoiceServiceEntity.getGstMasterEntity().getId())
				.setGstTag(customerInvoiceServiceEntity.getGstMasterEntity().getDescription())
				.setGstValue(customerInvoiceServiceEntity.getGstMasterEntity().getValue());
	}
	
	public static Set<CustomerInvoiceServiceResponseDto> toCustomerInvoiceServiceResponseDtosSet(Set<CustomerInvoiceServiceEntity> customerInvoiceServiceEntities) {
		// TODO Auto-generated method stub
		
		Set<CustomerInvoiceServiceResponseDto> customerInvoiceServiceResponseDtos = new HashSet<CustomerInvoiceServiceResponseDto>();
		for(CustomerInvoiceServiceEntity customerInvoiceServiceEntity : customerInvoiceServiceEntities) {
			customerInvoiceServiceResponseDtos.add(toCustomerInvoiceServiceResponseDto(customerInvoiceServiceEntity));			
		}
				
		return customerInvoiceServiceResponseDtos;
	}
	
}
