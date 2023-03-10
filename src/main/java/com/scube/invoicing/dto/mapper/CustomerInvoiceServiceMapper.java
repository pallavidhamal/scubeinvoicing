package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.CustomerInvoiceServiceResponseDto;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;

public class CustomerInvoiceServiceMapper {

	static Base64.Decoder baseDecoder = Base64.getDecoder();
	
	public static CustomerInvoiceServiceResponseDto toCustomerInvoiceServiceResponseDto(CustomerInvoiceServiceEntity customerInvoiceServiceEntity) {
		return new CustomerInvoiceServiceResponseDto()
				.setInvoiceServiceID(customerInvoiceServiceEntity.getId())
				
				.setServiceID(customerInvoiceServiceEntity.getServiceMasterEntity().getId())
				.setServiceName(customerInvoiceServiceEntity.getServiceMasterEntity().getServiceName())
				
				.setHsnOrSac(customerInvoiceServiceEntity.getHsnorSac())
				.setDescription(customerInvoiceServiceEntity.getDescription())
				.setRate(customerInvoiceServiceEntity.getRate())
				.setSku(customerInvoiceServiceEntity.getSku())
				
				.setAmount(new String(baseDecoder.decode(customerInvoiceServiceEntity.getAmount())))
				.setGstID(customerInvoiceServiceEntity.getGstMasterEntity().getId())
				.setGstTag(customerInvoiceServiceEntity.getGstMasterEntity().getDescription())
				.setGstValue(customerInvoiceServiceEntity.getGstMasterEntity().getValue())
				
				.setServiceAmountWithGst(new String(baseDecoder.decode(customerInvoiceServiceEntity.getServiceAmountWithGst())));
	}
	
	public static List<CustomerInvoiceServiceResponseDto> toCustomerInvoiceServiceResponseDtosSet(List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntities) {
		// TODO Auto-generated method stub
		
		List<CustomerInvoiceServiceResponseDto> customerInvoiceServiceResponseDtos = new ArrayList<CustomerInvoiceServiceResponseDto>();
		for(CustomerInvoiceServiceEntity customerInvoiceServiceEntity : customerInvoiceServiceEntities) {
			customerInvoiceServiceResponseDtos.add(toCustomerInvoiceServiceResponseDto(customerInvoiceServiceEntity));			
		}
				
		return customerInvoiceServiceResponseDtos;
	}
	
}
