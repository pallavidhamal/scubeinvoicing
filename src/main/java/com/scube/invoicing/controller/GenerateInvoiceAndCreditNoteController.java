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
import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.GenerateInvoiceAndCreditNoteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/generateInvoiceAndCreditNote"}, produces = APPLICATION_JSON_VALUE)
public class GenerateInvoiceAndCreditNoteController {
	
	@Autowired
	GenerateInvoiceAndCreditNoteService generateInvoiceAndCreditNoteService;
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateInvoiceAndCreditNoteController.class);

	/*
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/generateInvoiceAndSendMailToCustomer" , consumes = APPLICATION_JSON_VALUE)
  	public Response generateInvoiceAndSendMailToCustomer(@Valid @RequestBody CreateInvoiceIncomingDto createInvoiceIncomingDto) {
  		logger.info("------ CreateInvoiceController generateInvoiceAndSendMailToCustomer ------");
  		return Response.ok().setPayload(generateInvoiceAndCreditNoteService.generateInvoiceAndSendMailToCustomer(createInvoiceIncomingDto));
  	}
	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/generateCreditNoteForCustomer" , consumes = APPLICATION_JSON_VALUE)
  	public Response generateCreditNoteForCustomer(@Valid @RequestBody CreateInvoiceIncomingDto createInvoiceIncomingDto) {
  		logger.info("------ CreateInvoiceController generateCreditNoteForCustomer ------");
  		return Response.ok().setPayload(generateInvoiceAndCreditNoteService.generateCreditNoteForCustomer(createInvoiceIncomingDto));
  	}*/
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/saveInvoiceMailStatusForCustomer" , consumes = APPLICATION_JSON_VALUE)
  	public Response saveInvoiceMailStatusForCustomer(@Valid @RequestBody CreateInvoiceIncomingDto createInvoiceIncomingDto) {
  		logger.info("------ CreateInvoiceController saveInvoiceMailStatusForCustomer ------");
  		return Response.ok().setPayload(generateInvoiceAndCreditNoteService.saveInvoiceMailStatusForCustomer(createInvoiceIncomingDto));
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/saveCreditNoteMailStatusForCustomer" , consumes = APPLICATION_JSON_VALUE)
  	public Response saveCreditNoteMailStatusForCustomer(@Valid @RequestBody CreateInvoiceIncomingDto createInvoiceIncomingDto) {
  		logger.info("------ CreateInvoiceController saveCreditNoteMailStatusForCustomer ------");
  		return Response.ok().setPayload(generateInvoiceAndCreditNoteService.saveCreditNoteMailStatusForCustomer(createInvoiceIncomingDto));
  	}
}
