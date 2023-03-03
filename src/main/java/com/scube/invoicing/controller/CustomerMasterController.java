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

import com.scube.invoicing.dto.incoming.CustomerMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CustomerMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/customerMaster"}, produces = APPLICATION_JSON_VALUE)
public class CustomerMasterController {
	
	@Autowired
	CustomerMasterService customerMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerMasterController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/addCustomerInfoDetails", produces = APPLICATION_JSON_VALUE)
	public Response addCustomerInfoDetails(@Valid @RequestBody CustomerMasterIncomingDto customerMasterIncomingDto) {
		logger.info("-------- CustomerMasterController addCustomerInfoDetails ------");
		return Response.ok().setPayload(customerMasterService.addCustomerInfoDetails(customerMasterIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/updateCustomerInfoDetails", produces = APPLICATION_JSON_VALUE)
	public Response updateCustomerInfoDetails(@Valid @RequestBody CustomerMasterIncomingDto customerMasterIncomingDto) {
		logger.info("-------- CustomerMasterController updateCustomerInfoDetails ------");
		return Response.ok().setPayload(customerMasterService.updateCustomerInfoDetails(customerMasterIncomingDto));
	}

	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/deleteCustomerInfoDetailsByCustomerId/{customerID}")
	public Response deleteCustomerInfoDetailsByCustomerId(@PathVariable String customerID) {
		logger.info("-------- CustomerMasterController deleteCustomerInfoDetailsByCustomerId ------");
		return Response.ok().setPayload(customerMasterService.deleteCustomerInfoDetailsByCustomerId(customerID));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getCustomerInfoDetailsByCustomerId/{customerID}")
	public Response getCustomerInfoDetailsByCustomerId(@PathVariable String customerID) {
		logger.info("-------- CustomerMasterController getCustomerInfoDetailsByCustomerId ------");
		return Response.ok().setPayload(customerMasterService.getCustomerInfoDetailsByCustomerId(customerID));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getAllCustomerInfoDetails")
	public Response getAllCustomerInfoDetails() {
		logger.info("-------- CustomerMasterController getAllCustomerInfoDetails ------");
		return Response.ok().setPayload(customerMasterService.getAllCustomerInfoDetails());
	}
	
}
