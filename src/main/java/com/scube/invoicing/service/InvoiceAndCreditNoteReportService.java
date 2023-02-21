package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.InvoiceCreditNoteReportIncomingDto;

public interface InvoiceAndCreditNoteReportService {
	
	List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<InvoiceCreditNoteResponseDto> generateReportForCreditNoteByCustomerIDAndDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);

}
