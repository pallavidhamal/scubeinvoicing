package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.CreditNoteIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CreditNoteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/creditNote"}, produces = APPLICATION_JSON_VALUE)
public class CreditNoteController {
	
	@Autowired
	CreditNoteService creditNoteService;
	
	private static final Logger logger = LoggerFactory.getLogger(CreditNoteController.class);
	
	@PostMapping( value = "/addCreditNoteAndService" , consumes = APPLICATION_JSON_VALUE)
  	public Response addCreditNoteAndService(@Valid @RequestBody CreditNoteIncomingDto creditNoteIncomingDto) {
  		 
  		logger.info("------ CreditNoteController addCreditNoteAndService ------");
  		return Response.ok().setPayload(creditNoteService.addCreditNoteAndService(creditNoteIncomingDto));
  		
  	}
	
	
	@GetMapping( value = "/getAllCreditNoteList")
  	public Response getAllCreditNoteList() {
  		 
  		logger.info("------ CreditNoteController getAllCreditNoteList ------");
  		return Response.ok().setPayload(creditNoteService.getAllCreditNoteList());
  		
  	}

}
