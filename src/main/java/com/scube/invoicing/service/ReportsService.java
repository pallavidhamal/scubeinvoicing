package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.ReportsIncomingDto;

public interface ReportsService {
	
	List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange (@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<InvoiceCreditNoteResponseDto> generateReportForCreditNoteByCustomerIDAndDateRange (@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<InvoiceCreditNoteResponseDto> generateReportForInvoicePendingAndPaidAmountByDateRange (@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<GSTReportResponseDto> generateGSTReportForPaidInvoiceByDateRange (@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<GSTReportResponseDto> generateGSTReportForCreditNoteByDateRange (@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<GSTReportResponseDto> generateTDSReportForInvoiceByDateRange(@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<GSTReportResponseDto> generateTDSReportForCreditNoteByDateRange(@Valid ReportsIncomingDto reportsIncomingDto);
	
	GSTReportResponseDto calculateTotalAmountForGSTReportForCreditNote (@Valid ReportsIncomingDto reportsIncomingDto);
	
	GSTReportResponseDto calculateTotalAmountForGSTReportForInvoice (@Valid ReportsIncomingDto reportsIncomingDto);

}
