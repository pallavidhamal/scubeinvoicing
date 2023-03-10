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
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/addCreditNoteAndService" , consumes = APPLICATION_JSON_VALUE)
  	public Response addCreditNoteAndService(@Valid @RequestBody CreditNoteIncomingDto creditNoteIncomingDto) {
  		logger.info("------ CreditNoteController addCreditNoteAndService ------");
  		return Response.ok().setPayload(creditNoteService.addCreditNoteAndService(creditNoteIncomingDto));
  	}
	
	// Update Customer Credit Note
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/updateCreditNoteAndService" , consumes = APPLICATION_JSON_VALUE)
  	public Response updateCreditNoteAndService(@Valid @RequestBody CreditNoteIncomingDto creditNoteIncomingDto) {
  		logger.info("------ CreditNoteController updateCreditNoteAndService ------");
  		return Response.ok().setPayload(creditNoteService.updateCreditNoteAndService(creditNoteIncomingDto));
  	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/deleteCreditNoteByCustomerIDAndCreditNoteNo/{customerID}/{creditNoteNo}")
  	public Response deleteCreditNoteByCustomerIDAndCreditNoteNo(@PathVariable("customerID") String customerID, 
  			@PathVariable("creditNoteNo") String creditNoteNo) {
  		logger.info("------ CreditNoteController deleteCreditNoteByCustomerIDAndCreditNoteNo ------");
  		return Response.ok().setPayload(creditNoteService.deleteCreditNoteByCustomerIDAndCreditNoteNo(customerID, creditNoteNo));
  	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getCreditNoteDetailsByCreditNoteID/{creditNoteID}")
  	public Response getCreditNoteDetailsByCreditNoteID(@PathVariable("creditNoteID") String creditNoteID) {
  		logger.info("------ CreditNoteController getCreditNoteDetailsByCreditNoteID ------");
  		return Response.ok().setPayload(creditNoteService.getCreditNoteDetailsByCreditNoteID(creditNoteID));
  	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getAllCreditNoteList")
  	public Response getAllCreditNoteList() {
  		logger.info("------ CreditNoteController getAllCreditNoteList ------");
  		return Response.ok().setPayload(creditNoteService.getAllCreditNoteList());
  	}

}
