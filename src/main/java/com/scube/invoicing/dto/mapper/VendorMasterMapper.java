package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.VendorMasterResponseDto;
import com.scube.invoicing.entity.VendorMasterEntity;
import com.scube.invoicing.util.DateUtils;

public class VendorMasterMapper {
	
	public static VendorMasterResponseDto toVendorMasterResponseDto(VendorMasterEntity vendorMasterEntity) {
		return new VendorMasterResponseDto()
				.setVendorID(vendorMasterEntity.getId())
				.setTitle(vendorMasterEntity.getTitle())
				.setVendorFirstName(vendorMasterEntity.getVendorFirstName())
				.setVendorLastName(vendorMasterEntity.getVendorLastName())
				.setVendorContactNo(vendorMasterEntity.getMobileNumber())
				.setEmailId(vendorMasterEntity.getEmailId())
				
				.setCompanyName(vendorMasterEntity.getCompanyName())
				.setFax(vendorMasterEntity.getFax())
				.setWebsite(vendorMasterEntity.getWebsite())
				
				.setBillingAddress(vendorMasterEntity.getBillingAddress())
				.setBillingCity(vendorMasterEntity.getBillingCity())
				.setBillingCountry(vendorMasterEntity.getBillingCountry())
				.setBillingState(vendorMasterEntity.getBillingState())
				.setBillingPinCode(vendorMasterEntity.getBillingPinCode())
				
				.setShippingAddress(vendorMasterEntity.getShippingAddress())
				.setShippingCity(vendorMasterEntity.getShippingCity())
				.setShippingCountry(vendorMasterEntity.getShippingCountry())
				.setShippingState(vendorMasterEntity.getShippingState())
				.setShippingPinCode(vendorMasterEntity.getShippingPinCode())
				
				.setGstRegistrationNo(vendorMasterEntity.getGstRegistrationNo())
				.setGstin(vendorMasterEntity.getGstin())
				.setTaxRegistrationNo(vendorMasterEntity.getTaxRegistrationNo())
				.setCstRegistrationNo(vendorMasterEntity.getCstRegistrationNo())
				.setPanNo(vendorMasterEntity.getPanNo())

				.setPaymentMethodID(vendorMasterEntity.getPaymentMethodEntity().getId())
				.setPrefDelieveryMethod(vendorMasterEntity.getPrefDelieveryMethod())
				.setPaymentDate(DateUtils.formattedDate(vendorMasterEntity.getPaymentDate()))
				.setPaymentTerms(vendorMasterEntity.getPaymentTerms())
				.setOpeningBalance(vendorMasterEntity.getOpeningBalance())
				.setPaysWith(vendorMasterEntity.getPaysWith())
				.setPrefPaymentMethod(vendorMasterEntity.getPaymentMethodEntity().getMethodName())
				
				.setCurrencyID(vendorMasterEntity.getCurrencyMasterEntity().getId())
				.setCurrencyName(vendorMasterEntity.getCurrencyMasterEntity().getCurrencyName());
	}
	
	
	public static List<VendorMasterResponseDto> toVendorMasterResponseDtosList(List<VendorMasterEntity> vendorMasterEntitiesList) {
		// TODO Auto-generated method stub	
		List<VendorMasterResponseDto> vendorMasterResponseDtosList = new ArrayList<VendorMasterResponseDto>();
		for(VendorMasterEntity vendorMasterEntity  : vendorMasterEntitiesList) {
			vendorMasterResponseDtosList.add(toVendorMasterResponseDto(vendorMasterEntity));
		}	
		return vendorMasterResponseDtosList;
	}
	
}
