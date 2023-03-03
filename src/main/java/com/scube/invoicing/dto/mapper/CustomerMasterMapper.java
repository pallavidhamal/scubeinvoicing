package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CustomerMasterResponseDto;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.util.DateUtils;

public class CustomerMasterMapper {
	
	public static CustomerMasterResponseDto toCustomerMasterResponseDto (CustomerMasterEntity customerMasterEntity) {
		
		return new CustomerMasterResponseDto()
				.setCustomerId(customerMasterEntity.getId())
				
				.setTitle(customerMasterEntity.getTitle())
				.setFirstName(customerMasterEntity.getFirstName())
				.setLastName(customerMasterEntity.getLastName())
				
				.setCompanyName(customerMasterEntity.getCompanyName())
				.setEmailId(customerMasterEntity.getEmailId())
				.setMobileNumber(customerMasterEntity.getMobileNumber())
				.setFax(customerMasterEntity.getFax())
				.setWebsite(customerMasterEntity.getWebsite())
				
				.setBillingAddress(customerMasterEntity.getBillingAddress())
				.setBillingCity(customerMasterEntity.getBillingCity())
				.setBillingState(customerMasterEntity.getBillingState())
				.setBillingCountry(customerMasterEntity.getBillingCountry())
				.setBillingPinCode(customerMasterEntity.getBillingPinCode())
				
				.setShippingAddress(customerMasterEntity.getShippingAddress())
				.setShippingCity(customerMasterEntity.getShippingCity())
				.setShippingState(customerMasterEntity.getShippingState())
				.setShippingCountry(customerMasterEntity.getShippingCountry())
				.setShippingPinCode(customerMasterEntity.getShippingPinCode())
				
				.setGstRegistrationNo(customerMasterEntity.getGstRegistrationNo())
				.setGstin(customerMasterEntity.getGstin())
				.setTaxRegistrationNo(customerMasterEntity.getTaxRegistrationNo())
				.setCstRegistrationNo(customerMasterEntity.getCstRegistrationNo())
				.setPanNo(customerMasterEntity.getPanNo())
				
				.setPaymentMethodID(customerMasterEntity.getPaymentMethodEntity().getId())
				.setPrefPaymentMethod(customerMasterEntity.getPaymentMethodEntity().getMethodName())
				
				.setPrefDelieveryMethod(customerMasterEntity.getPrefDelieveryMethod())
				.setPaymentTerms(customerMasterEntity.getPaymentTerms())
				.setOpeningBalance(customerMasterEntity.getOpeningBalance())
				.setPaymentDate(DateUtils.formatDateToDDMMYYYYFormat(customerMasterEntity.getPaymentDate()))
				.setPaysWith(customerMasterEntity.getPaysWith())
				
				.setCurrencyID(customerMasterEntity.getCurrencyMasterEntity().getId())
				.setCurrencyName(customerMasterEntity.getCurrencyMasterEntity().getCurrencyName());
	}
	
	public static List<CustomerMasterResponseDto> toAllCustomerDataList(List<CustomerMasterEntity> customerMasterEntitiesList) {
		// TODO Auto-generated method stub
		
		List<CustomerMasterResponseDto> customerMasterResponseDtos= new ArrayList<CustomerMasterResponseDto>();
		for(CustomerMasterEntity customerMasterEntity :customerMasterEntitiesList) {
			customerMasterResponseDtos.add(toCustomerMasterResponseDto(customerMasterEntity));			
		}
				
		return customerMasterResponseDtos;
	} 

}
