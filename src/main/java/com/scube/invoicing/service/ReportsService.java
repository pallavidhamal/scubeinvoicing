package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.InvoiceCreditNoteReportIncomingDto;

public interface InvoiceAndCreditNoteReportService {
	
	List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<InvoiceCreditNoteResponseDto> generateReportForCreditNoteByCustomerIDAndDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<InvoiceCreditNoteResponseDto> generateReportForInvoicePendingAndPaidAmountByDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<GSTReportResponseDto> generateGSTReportForPaidInvoiceByDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<GSTReportResponseDto> generateGSTReportForCreditNoteByDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<GSTReportResponseDto> generateTDSReportForInvoiceByDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	List<GSTReportResponseDto> generateTDSReportForCreditNoteByDateRange
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	GSTReportResponseDto calculateTotalAmountForGSTReportForCreditNote
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);
	
	GSTReportResponseDto calculateTotalAmountForGSTReportForInvoice
	(@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto);

}
