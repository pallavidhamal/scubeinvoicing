package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CustomerMasterResponseDto;
import com.scube.invoicing.dto.ExpenseLedgerResponseDto;
import com.scube.invoicing.dto.ExpenseResponseDto;
import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.LedgerResponseDto;
import com.scube.invoicing.dto.incoming.ReportsIncomingDto;
import com.scube.invoicing.entity.InvoiceLedgerEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;

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
	
	List<LedgerResponseDto> getLedgerEntriesByinvoiceId(String invoiceID);
	
	List<LedgerResponseDto> generateLedgerReportByCustomerIDAndDateRange(@Valid ReportsIncomingDto reportsIncomingDto);
	
	List<ExpenseLedgerResponseDto> generateExpenseReportByVendorIDAndDateRange(@Valid ReportsIncomingDto reportsIncomingDto);

}
