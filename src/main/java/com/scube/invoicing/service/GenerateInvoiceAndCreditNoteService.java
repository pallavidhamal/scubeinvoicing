package com.scube.invoicing.service;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.core.io.Resource;

import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.entity.CheckCreditNoteMailStatusEntity;
import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

public interface GenerateInvoiceAndCreditNoteService {
	
	Resource generateInvoice(String invoiceID);
	
	Resource generateCreditNote(String creditNoteID);
	
	boolean generateInvoiceAndSendMailToCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto, 
			CustomerInvoiceEntity customerInvoiceEntity, CustomerMasterEntity customerMasterEntity, 
			List<CheckInvoiceMailStatusEntity> checkMailStatusEntityList);
	
	boolean generateCreditNoteForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto, 
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity,
			List<CheckCreditNoteMailStatusEntity> checkCreditNoteMailStatusEntityList);
	
	boolean saveInvoiceMailStatusForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto);
	
	boolean saveCreditNoteMailStatusForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto);
	
	boolean sendGeneratedInvoiceMail();
	
	boolean sendGeneratedCreditNoteMail();

}
