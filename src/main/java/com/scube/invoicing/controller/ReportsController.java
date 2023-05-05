package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.ReportsIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.ReportsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/reports"}, produces = APPLICATION_JSON_VALUE)
public class ReportsController {
	
	@Autowired
	ReportsService invoiceAndCreditNoteReportService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForInvoiceByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForInvoiceByCustomerIDAndDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForInvoiceByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForInvoiceByCustomerIDAndDateRange(reportsIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForCreditNoteByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForCreditNoteByCustomerIDAndDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForCreditNoteByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForCreditNoteByCustomerIDAndDateRange(reportsIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForInvoicePendingAndPaidAmountByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForInvoicePendingAndPaidAmountByDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForPendingAndPaidAmountByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForInvoicePendingAndPaidAmountByDateRange(reportsIncomingDto));
	}

	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateGSTReportForPaidInvoiceByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateGSTReportForPaidInvoiceByDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateGSTReportForInvoiceByDateRange ------");
		return Response.ok()
				.setPayload(invoiceAndCreditNoteReportService.generateGSTReportForPaidInvoiceByDateRange(reportsIncomingDto))
				.setRespData(invoiceAndCreditNoteReportService.calculateTotalAmountForGSTReportForInvoice(reportsIncomingDto));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateGSTReportForCreditNoteByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateGSTReportForCreditNoteByDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateGSTReportForCreditNoteByDateRange ------");
		return Response.ok()
				.setPayload(invoiceAndCreditNoteReportService.generateGSTReportForCreditNoteByDateRange(reportsIncomingDto))
				.setRespData(invoiceAndCreditNoteReportService.calculateTotalAmountForGSTReportForCreditNote(reportsIncomingDto));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateTDSReportForInvoiceByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateTDSReportForInvoiceByDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateTDSReportForInvoiceByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateTDSReportForInvoiceByDateRange(reportsIncomingDto));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateTDSReportForCreditNoteByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateTDSReportForCreditNoteByDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateTDSReportForCreditNoteByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateTDSReportForInvoiceByDateRange(reportsIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getLedgerEntriesByinvoiceId/{invoiceID}")
	public Response getLedgerEntriesByCustomerId(@PathVariable String invoiceID) {
		logger.info("-------- CustomerMasterController getLedgerEntriesByinvoiceId ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.getLedgerEntriesByinvoiceId(invoiceID));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateLedgerReportByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateLedgerReportByCustomerIDAndDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateLedgerReportByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateLedgerReportByCustomerIDAndDateRange(reportsIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateExpenseReportByVendorIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateExpenseReportByVendorIDAndDateRange(@Valid @RequestBody ReportsIncomingDto reportsIncomingDto) {
		logger.info("----- InvoiceAndCreditNoteReportController generateExpenseReportByVendorIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateExpenseReportByVendorIDAndDateRange(reportsIncomingDto));
	}
	
}
