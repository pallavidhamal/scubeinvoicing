package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.CreditNoteIncomingDto;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CreditNoteService {
	
	CreditNoteResponseDto addCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto);
	
	CreditNoteResponseDto updateCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto);
	
	boolean deleteCreditNoteByCustomerIDAndCreditNoteNo(String customerID, String creditNoteNo);
	
	CreditNoteResponseDto getCreditNoteDetailsByCreditNoteID(String creditNoteID);
	
	CustomerCreditNoteEntity getCustomerCreditNoteEntityByCustomerIDAndCreditNoteNo(CustomerMasterEntity customerMasterEntity, String creditNoteNo);
	
	List<CustomerCreditNoteDetailsEntity> getCreditNoteDetailsListByCustomerIDAndCreditNoteNo(
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity);
	
	List<CreditNoteResponseDto> getAllCreditNoteList();

}
