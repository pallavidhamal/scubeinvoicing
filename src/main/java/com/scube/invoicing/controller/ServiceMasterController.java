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

import com.scube.invoicing.dto.incoming.ServiceMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.ServiceMasterDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"api/v1/serviceMaster"}, produces = APPLICATION_JSON_VALUE)
public class ServiceMasterController {
	
	@Autowired
	ServiceMasterDetailsService serviceMasterDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceMasterController.class);
	
	@PostMapping(value = "/addServiceInfo", produces = APPLICATION_JSON_VALUE)
	public Response addServiceInfo(@Valid @RequestBody ServiceMasterIncomingDto serviceMasterIncomingDto) {
		
		logger.info("----- ServiceMasterController addServiceInfo ------");
		return Response.ok().setPayload(serviceMasterDetailsService.addServiceInfoDetails(serviceMasterIncomingDto));
	
	}
	
	@PostMapping(value = "/updateServiceInfo", produces = APPLICATION_JSON_VALUE)
	public Response updateServiceInfo(@Valid @RequestBody ServiceMasterIncomingDto serviceMasterIncomingDto) {
		
		logger.info("----- ServiceMasterController updateServiceInfo ------");
		return Response.ok().setPayload(serviceMasterDetailsService.updateServiceInfoDetails(serviceMasterIncomingDto));
	
	}
	
	@GetMapping(value = "/deleteServiceInfo/{serviceID}")
	public Response deleteServiceInfo(@Valid @PathVariable ("serviceID") String serviceID) {
		
		logger.info("----- ServiceMasterController deleteServiceInfo ------");
		return Response.ok().setPayload(serviceMasterDetailsService.deleteServiceInfoByServiceID(serviceID));
	
	}
	
	@GetMapping(value = "/getServiceInfoByServiceID/{serviceID}")
	public Response getServiceInfoByServiceID(@Valid @PathVariable ("serviceID") String serviceID) {
		
		logger.info("----- ServiceMasterController getServiceInfoByServiceID ------");
		return Response.ok().setPayload(serviceMasterDetailsService.getServiceInfoByServiceID(serviceID));
	
	}
	
	@GetMapping(value = "/getAllServiceInfo")
	public Response getAllServiceInfo() {
		
		logger.info("----- ServiceMasterController getAllServiceInfo ------");
		return Response.ok().setPayload(serviceMasterDetailsService.getAllServiceInfo());
	
	}
	
}
