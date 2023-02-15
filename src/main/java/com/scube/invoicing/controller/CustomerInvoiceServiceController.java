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

import com.scube.invoicing.dto.incoming.CustomerServiceIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CustomerInvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/customerService"}, produces = APPLICATION_JSON_VALUE)
public class CustomerInvoiceServiceController {
	
	@Autowired
	CustomerInvoiceService customerService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerInvoiceServiceController.class);
	
  	@PostMapping( value = "/addCustomerInvoiceAndServiceData" , consumes = APPLICATION_JSON_VALUE)
  	public Response addCustomerInvoiceAndServiceData(@Valid @RequestBody CustomerServiceIncomingDto customerServiceIncomingDto) {
  		
  		logger.info("----- CustomerInvoiceServiceController addCustomerInvoiceAndServiceData ----");
  		
  		return Response.ok().setPayload(customerService.addCustomerInvoiceAndServiceData(customerServiceIncomingDto)) ;
  		
  	}
  	
  	
  	@GetMapping( value = "/removeCustomerInvoiceAndServiceData/{customerID}/{invoiceNo}")
  	public Response removeCustomerInvoiceAndServiceData(@PathVariable("customerID") String customerID, 
  			@PathVariable("invoiceNo") String invoiceNo) {
  		
  		logger.info("----- CustomerInvoiceServiceController removeCustomerInvoiceAndServiceData ----");
  		
  		return Response.ok().setPayload(customerService.removeCustomerInvoiceAndServiceData(customerID, invoiceNo));
  		
  	}
  	
  	
  	@GetMapping( value = "/getAllCustomerInvoiceAndServiceList")
  	public Response getAllCustomerInvoiceAndServiceList() {
  		
  		logger.info("----- CustomerInvoiceServiceController getAllCustomerInvoiceAndServiceList ----");
  		
  		return Response.ok().setPayload(customerService.getAllCustomerInvoiceAndServiceList());
  		
  	}
	
	
}
