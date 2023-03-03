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

import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CustomerInvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/customerService"}, produces = APPLICATION_JSON_VALUE)
public class CustomerInvoiceServiceController {
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerInvoiceServiceController.class);
	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/addCustomerInvoiceAndServiceData" , consumes = APPLICATION_JSON_VALUE)
  	public Response addCustomerInvoiceAndServiceData(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController addCustomerInvoiceAndServiceData ----");
  		return Response.ok().setPayload(customerInvoiceService.addCustomerInvoiceAndServiceData(customerServiceIncomingDto)) ;
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/updateCustomerInvoiceAndServiceData" , consumes = APPLICATION_JSON_VALUE)
  	public Response updateCustomerInvoiceAndServiceData(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController addCustomerInvoiceAndServiceData ----");
  		return Response.ok().setPayload(customerInvoiceService.updateCustomerServiceInfo(customerServiceIncomingDto)) ;
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/removeCustomerInvoiceAndServiceData/{customerID}/{invoiceNo}")
  	public Response removeCustomerInvoiceAndServiceData(@PathVariable("customerID") String customerID, 
  			@PathVariable("invoiceNo") String invoiceNo) {
  		logger.info("----- CustomerInvoiceServiceController removeCustomerInvoiceAndServiceData ----");
  		return Response.ok().setPayload(customerInvoiceService.removeCustomerInvoiceAndServiceData(customerID, invoiceNo));
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/getCustomerInvoiceListByCustomerID/{customerID}")
  	public Response getCustomerInvoiceListByCustomerID(@PathVariable("customerID") String customerID) {
  		logger.info("----- CustomerInvoiceServiceController getCustomerInvoiceListByCustomerID ----");
  		return Response.ok().setPayload(customerInvoiceService.getCustomerInvoiceListByCustomerID(customerID));
  	}
  	
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/getAllCustomerInvoiceAndServiceList")
  	public Response getAllCustomerInvoiceAndServiceList() {
  		logger.info("----- CustomerInvoiceServiceController getAllCustomerInvoiceAndServiceList ----");
  		return Response.ok().setPayload(customerInvoiceService.getAllCustomerInvoiceAndServiceList());
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/updateCustomerInvoicePaymentStatus", consumes = APPLICATION_JSON_VALUE)
  	public Response updateCustomerInvoicePaymentStatus(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController updateCustomerInvoicePaymentStatus ----");
  		return Response.ok().setPayload(customerInvoiceService.updateCustomerInvoicePaymentStatus(customerServiceIncomingDto));
  	}
	
	
}
