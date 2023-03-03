package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.InvoiceCreditNoteReportIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.InvoiceAndCreditNoteReportService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/reports"}, produces = APPLICATION_JSON_VALUE)
public class InvoiceAndCreditNoteReportController {
	
	@Autowired
	InvoiceAndCreditNoteReportService invoiceAndCreditNoteReportService;
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceAndCreditNoteReportController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForInvoiceByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForInvoiceByCustomerIDAndDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForInvoiceByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForInvoiceByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto));
	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForCreditNoteByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForCreditNoteByCustomerIDAndDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForCreditNoteByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForCreditNoteByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto));
	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateReportForInvoicePendingAndPaidAmountByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForInvoicePendingAndPaidAmountByDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForPendingAndPaidAmountByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForInvoicePendingAndPaidAmountByDateRange(invoiceCreditNoteReportIncomingDto));
	
	}

	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateGSTReportForPaidInvoiceByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateGSTReportForPaidInvoiceByDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateGSTReportForInvoiceByDateRange ------");
		return Response.ok()
				.setPayload(invoiceAndCreditNoteReportService.generateGSTReportForPaidInvoiceByDateRange(invoiceCreditNoteReportIncomingDto))
				.setRespData(invoiceAndCreditNoteReportService.calculateTotalAmountForGSTReportForInvoice(invoiceCreditNoteReportIncomingDto));
	
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateGSTReportForCreditNoteByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateGSTReportForCreditNoteByDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateGSTReportForCreditNoteByDateRange ------");
		return Response.ok()
				.setPayload(invoiceAndCreditNoteReportService.generateGSTReportForCreditNoteByDateRange(invoiceCreditNoteReportIncomingDto))
				.setRespData(invoiceAndCreditNoteReportService.calculateTotalAmountForGSTReportForCreditNote(invoiceCreditNoteReportIncomingDto));
	
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateTDSReportForInvoiceByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateTDSReportForInvoiceByDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateTDSReportForInvoiceByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateTDSReportForInvoiceByDateRange(invoiceCreditNoteReportIncomingDto));
	
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/generateTDSReportForCreditNoteByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateTDSReportForCreditNoteByDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateTDSReportForCreditNoteByDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateTDSReportForInvoiceByDateRange(invoiceCreditNoteReportIncomingDto));
	
	}
}
