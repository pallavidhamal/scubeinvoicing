package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.CreditNoteIncomingDto;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface CreditNoteService {
	
	boolean addCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto);
	
	boolean updateCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto);
	
	CustomerCreditNoteEntity getCustomerCreditNoteEntityByCustomerIDAndCreditNoteNo(CustomerMasterEntity customerMasterEntity, String creditNoteNo);
	
	List<CustomerCreditNoteDetailsEntity> getCreditNoteDetailsListByCustomerIDAndCreditNoteNo(
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity);
	
	List<CreditNoteResponseDto> getAllCreditNoteList();

}
