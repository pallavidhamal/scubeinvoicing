package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.util.DateUtils;

public class InvoiceCreditNoteResponseMapper {
	
	public static InvoiceCreditNoteResponseDto toInvoiceResponseDto(CustomerInvoiceEntity customerInvoiceEntity) {
		return new InvoiceCreditNoteResponseDto()
				.setCustomerID(customerInvoiceEntity.getId())
				.setCustomerCompanyName(customerInvoiceEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerInvoiceNo(customerInvoiceEntity.getInvoiceNo())
				.setDate(DateUtils.formatDateToDDMMYYYYFormat(customerInvoiceEntity.getInvoiceDate()));
	}
	
	public static List<InvoiceCreditNoteResponseDto> toInvoiceResponseDtosList(List<CustomerInvoiceEntity> customerInvoiceEntitiesList) {
		// TODO Auto-generated method stub
		
		List<InvoiceCreditNoteResponseDto> invoiceCreditNoteResponseDtos = new ArrayList<InvoiceCreditNoteResponseDto>();
		for(CustomerInvoiceEntity customerInvoiceEntity : customerInvoiceEntitiesList) {
			invoiceCreditNoteResponseDtos.add(toInvoiceResponseDto(customerInvoiceEntity));			
		}
		
		return invoiceCreditNoteResponseDtos;
	}
	
	
	public static InvoiceCreditNoteResponseDto toCreditNoteResponseDto(CustomerCreditNoteEntity creditNoteEntity) {
		return new InvoiceCreditNoteResponseDto()
				.setCustomerID(creditNoteEntity.getCustomerMasterEntity().getId())
				.setCustomerCompanyName(creditNoteEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerCreditNoteNo(creditNoteEntity.getCreditNoteNo())
				.setCustomerCreditsRemaining(creditNoteEntity.getCreditsRemaining());
	}
	
	public static List<InvoiceCreditNoteResponseDto> toCreditNoteResponseDtosList(List<CustomerCreditNoteEntity> creditNoteEntitiesList) {
		// TODO Auto-generated method stub
		
		List<InvoiceCreditNoteResponseDto> invoiceCreditNoteResponseDtos = new ArrayList<InvoiceCreditNoteResponseDto>();
		for(CustomerCreditNoteEntity creditNoteEntity : creditNoteEntitiesList) {
			invoiceCreditNoteResponseDtos.add(toCreditNoteResponseDto(creditNoteEntity));			
		}
		
		return invoiceCreditNoteResponseDtos;
	} 

}
