package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.CreditNoteServiceResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;


public class CustomerCreditNoteServiceMapper {
	
	static Base64.Decoder baseDecoder = Base64.getDecoder();
	
	public static CreditNoteServiceResponseDto toCustomerCreditNoteServiceResponseDto(CustomerCreditNoteDetailsEntity customerCreditNoteDetailsEntity) {
		return new CreditNoteServiceResponseDto()
				.setCreditNoteServiceID(customerCreditNoteDetailsEntity.getId())
				
				.setServiceID(customerCreditNoteDetailsEntity.getServiceMasterEntity().getId())
				.setServiceName(customerCreditNoteDetailsEntity.getServiceMasterEntity().getServiceName())
				
				.setDescription(customerCreditNoteDetailsEntity.getDescription())
				.setQuantity(customerCreditNoteDetailsEntity.getQuantity())
				.setRate(customerCreditNoteDetailsEntity.getRate())
				.setGstAmount(new String(baseDecoder.decode(customerCreditNoteDetailsEntity.getGstAmount())))
				
				.setAmount(new String(baseDecoder.decode(customerCreditNoteDetailsEntity.getAmount())))
				.setGstID(customerCreditNoteDetailsEntity.getGstMasterEntity().getId())
				.setGstTag(customerCreditNoteDetailsEntity.getGstMasterEntity().getDescription())
				.setGstValue(customerCreditNoteDetailsEntity.getGstMasterEntity().getValue())
				
				
				.setServiceAmountWithGst(new String(baseDecoder.decode(customerCreditNoteDetailsEntity.getServiceAmountWithGst())));
	}
	
	public static List<CreditNoteServiceResponseDto> toCustomerCreditNoteServiceResponseDtosSet(List<CustomerCreditNoteDetailsEntity> 
					customerCreditNoteDetailsEntityList) {
		// TODO Auto-generated method stub
		
		List<CreditNoteServiceResponseDto> creditNoteServiceResponseDtosList = new ArrayList<CreditNoteServiceResponseDto>();
		for(CustomerCreditNoteDetailsEntity creditNoteDetailsEntity : customerCreditNoteDetailsEntityList) {
			creditNoteServiceResponseDtosList.add(toCustomerCreditNoteServiceResponseDto(creditNoteDetailsEntity));			
		}
				
		return creditNoteServiceResponseDtosList;
	}

}
