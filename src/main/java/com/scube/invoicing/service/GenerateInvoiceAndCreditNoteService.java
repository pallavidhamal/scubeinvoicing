package com.scube.invoicing.service;

import javax.validation.Valid;

import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;

public interface CreateInvoiceService {
	
	boolean generateInvoiceAndSendMailToCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto);
	
	boolean generateCreditNoteForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto);

}
