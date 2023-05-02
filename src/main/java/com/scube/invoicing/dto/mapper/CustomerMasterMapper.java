package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CustomerMasterResponseDto;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.StringNullEmpty;

public class CustomerMasterMapper {
	
	public static CustomerMasterResponseDto toCustomerMasterResponseDto (CustomerMasterEntity customerMasterEntity) {
		
		return new CustomerMasterResponseDto()
				.setCustomerId(customerMasterEntity.getId())
				
				.setTitle(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getTitle()))
				.setFirstName(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getFirstName()))
				.setLastName(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getLastName()))
				
				.setCompanyName(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getCompanyName()))
				.setEmailId(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getEmailId()))
				.setMobileNumber(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getMobileNumber()))
				.setFax(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getFax()))
				.setWebsite(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getWebsite()))
				
				.setBillingAddress(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getBillingAddress()))
				.setBillingCity(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getBillingCity()))
				.setBillingState(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getBillingState()))
				.setBillingCountry(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getBillingCountry()))
				.setBillingPinCode(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getBillingPinCode()))
				
				.setShippingAddress(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getShippingAddress()))
				.setShippingCity(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getShippingCity()))
				.setShippingState(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getShippingState()))
				.setShippingCountry(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getShippingCountry()))
				.setShippingPinCode(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getShippingPinCode()))
				
				
				.setGstRegistrationNo(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getGstRegistrationNo()))
				.setGstin(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getGstin()))
				.setTaxRegistrationNo(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getTaxRegistrationNo()))
				.setCstRegistrationNo(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getCstRegistrationNo()))
				.setPanNo(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPanNo()))
				
				.setPaymentMethodID(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPaymentMethodEntity().getId()))
				.setPrefPaymentMethod(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPaymentMethodEntity().getMethodName()))
				
				.setPrefDelieveryMethod(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPrefDelieveryMethod()))
				.setPaymentTerms(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPaymentTerms()))
				.setOpeningBalance(customerMasterEntity.getOpeningBalance())
				.setPaymentDate(DateUtils.formattedDate(customerMasterEntity.getPaymentDate()))
				.setPaysWith(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getPaysWith()))
				
				.setCurrencyID(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getCurrencyMasterEntity().getId()))
				.setCurrencyName(StringNullEmpty.stringNullAndEmptyToBlank(customerMasterEntity.getCurrencyMasterEntity().getCurrencyName()));
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
