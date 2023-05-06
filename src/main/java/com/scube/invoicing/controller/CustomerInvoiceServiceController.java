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
  		return Response.ok().setPayload(customerInvoiceService.addCustomerInvoiceAndServiceInfo(customerServiceIncomingDto)) ;
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/updateCustomerInvoiceAndServiceData" , consumes = APPLICATION_JSON_VALUE)
  	public Response updateCustomerInvoiceAndServiceData(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController updateCustomerInvoiceAndServiceInfo ----");
  		return Response.ok().setPayload(customerInvoiceService.updateCustomerInvoiceAndServiceInfo(customerServiceIncomingDto)) ;
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/removeCustomerInvoiceAndServiceData/{customerID}/{invoiceNo}")
  	public Response removeCustomerInvoiceAndServiceInfo(@PathVariable("customerID") String customerID, 
  			@PathVariable("invoiceNo") String invoiceNo) {
  		logger.info("----- CustomerInvoiceServiceController removeCustomerInvoiceAndServiceInfo ----");
  		return Response.ok().setPayload(customerInvoiceService.removeCustomerInvoiceAndServiceInfo(customerID, invoiceNo));
  	}
  	
 	// Get Customer Invoice List By Customer ID
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/getCustomerInvoiceListByCustomerID/{customerID}")
  	public Response getCustomerInvoiceListByCustomerID(@PathVariable("customerID") String customerID) {
  		logger.info("----- CustomerInvoiceServiceController getCustomerInvoiceListByCustomerID ----");
  		return Response.ok().setPayload(customerInvoiceService.getCustomerInvoiceListByCustomerID(customerID));
  	}
  	
 // Get Customer Invoice List By Invoice ID
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/getCustomerInvoiceListByInvoiceID/{invoiceID}")
  	public Response getCustomerInvoiceListByInvoiceID(@PathVariable("invoiceID") String invoiceID) {
  		logger.info("----- CustomerInvoiceServiceController getCustomerInvoiceListByInvoiceID ----");
  		return Response.ok().setPayload(customerInvoiceService.getCustomerInvoiceListByInvoiceID(invoiceID));
  	}
  	
  	@SuppressWarnings("rawtypes")
	@GetMapping( value = "/getAllCustomerInvoiceAndServiceList")
  	public Response getAllCustomerInvoiceAndServiceList() {
  		logger.info("----- CustomerInvoiceServiceController getAllCustomerInvoiceAndServiceList ----");
  		return Response.ok().setPayload(customerInvoiceService.getAllCustomerInvoiceAndServiceList());
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/getAllCustomerInvoiceListByDateRange")
  	public Response getAllCustomerInvoiceListByDateRange(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController getAllCustomerInvoiceListByDateRange ----");
  		return Response.ok().setPayload(customerInvoiceService.getAllCustomerInvoiceListByDateRange(customerServiceIncomingDto));
  	}
  	
  	
  	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/updateCustomerInvoicePaymentStatus", consumes = APPLICATION_JSON_VALUE)
  	public Response updateCustomerInvoicePaymentStatus(@Valid @RequestBody CustomerInvoiceIncomingDto customerServiceIncomingDto) {
  		logger.info("----- CustomerInvoiceServiceController updateCustomerInvoicePaymentStatus ----");
  		return Response.ok().setPayload(customerInvoiceService.updateCustomerInvoicePaymentStatus(customerServiceIncomingDto));
  	}
	
  	
  	@SuppressWarnings("rawtypes")
  	@GetMapping( value = "/getInvoiceMailResponseByInvoiceID/{invoiceID}")
  	public Response getInvoiceMailResponseByInvoiceID(@PathVariable("invoiceID") String invoiceID) {
  		logger.info("----- CustomerInvoiceServiceController getInvoiceMailResponseByInvoiceID ----");
  		return Response.ok().setPayload(customerInvoiceService.getInvoiceMailResponseByInvoiceID(invoiceID));
  	}
	
}
