package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CreditNoteResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.util.DateUtils;

public class CustomerCreditNoteMapper {

	public static CreditNoteResponseDto toCreditNoteResponseDto(CustomerCreditNoteEntity customerCreditNoteEntity) {
		return new CreditNoteResponseDto()
				.setCustomerID(customerCreditNoteEntity.getCustomerMasterEntity().getId())
				.setCustomerName(customerCreditNoteEntity.getCustomerMasterEntity().getTitle() + " " + 
						customerCreditNoteEntity.getCustomerMasterEntity().getFirstName() + 
						" " + customerCreditNoteEntity.getCustomerMasterEntity().getLastName())
				.setCustomerCompanyName(customerCreditNoteEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCustomerCreditNoteDate(DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate()))
				.setSubTotal(customerCreditNoteEntity.getSubTotal())
				.setCreditsRemaining(customerCreditNoteEntity.getCreditsRemaining())
				.setTotalAmount(customerCreditNoteEntity.getTotalAmount());
	}
	
	public static List<CreditNoteResponseDto> toCustomerInvoiceResponseDtosList(List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList) {
		// TODO Auto-generated method stub
		
		List<CreditNoteResponseDto> creditNoteResponseDtos = new ArrayList<CreditNoteResponseDto>();
		for(CustomerCreditNoteEntity customerCreditNoteEntity :customerCreditNoteEntitiesList) {
			creditNoteResponseDtos.add(toCreditNoteResponseDto(customerCreditNoteEntity));			
		}
				
		return creditNoteResponseDtos;
	} 
	
}
