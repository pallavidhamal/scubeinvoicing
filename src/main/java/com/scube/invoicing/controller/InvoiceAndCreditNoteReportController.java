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
	
	@PostMapping(value = "/generateReportForInvoiceByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForInvoiceByCustomerIDAndDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForInvoiceByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForInvoiceByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto));
	
	}
	
	
	@PostMapping(value = "/generateReportForCreditNoteByCustomerIDAndDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response generateReportForCreditNoteByCustomerIDAndDateRange(@Valid @RequestBody 
			InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		
		logger.info("----- InvoiceAndCreditNoteReportController generateReportForCreditNoteByCustomerIDAndDateRange ------");
		return Response.ok().setPayload(invoiceAndCreditNoteReportService.generateReportForCreditNoteByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto));
	
	}

}
