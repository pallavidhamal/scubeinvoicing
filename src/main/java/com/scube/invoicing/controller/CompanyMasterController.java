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

import com.scube.invoicing.dto.incoming.CompanyMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CompanyMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/companyMaster"}, produces = APPLICATION_JSON_VALUE)
public class CompanyMasterController {
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyMasterController.class);
	
  	@PostMapping( value = "/addCompanyDetails" , consumes = APPLICATION_JSON_VALUE)
  	public Response addCompanyDetails(@Valid @RequestBody CompanyMasterIncomingDto companyMasterIncomingDto) {
  		
  		logger.info("------- CompanyMasterController addCompanyDetails -------");
  		
  		return Response.ok().setPayload(companyMasterService.addCompanyDetails(companyMasterIncomingDto));
  		
  	}

  	@PostMapping( value = "/updateCompanyDetails" , consumes = APPLICATION_JSON_VALUE)
  	public Response updateCompanyDetails(@Valid @RequestBody CompanyMasterIncomingDto companyMasterIncomingDto) {
  		
  		logger.info("------- CompanyMasterController updateCompanyDetails -------");
  		
  		return Response.ok().setPayload(companyMasterService.updateCompanyDetails(companyMasterIncomingDto));
  		
  	}
  	
  	@GetMapping( value = "/deleteCompanyDetailsByCompanyId/{companyID}")
  	public Response deleteCompanyDetailsByCompanyId(@PathVariable("companyID") String companyID) {
  		
  		logger.info("------- CompanyMasterController deleteCompanyDetailsByCompanyId -------");
  		
  		return Response.ok().setPayload(companyMasterService.deleteCompanyDetailsByCompanyId(companyID));
  		
  	}
  	
  	@GetMapping( value = "/getCompanyDetailsByCompanyId/{companyID}")
  	public Response getCompanyDetailsByCompanyId(@PathVariable("companyID") String companyID) {
  		
  		logger.info("------- CompanyMasterController getCompanyDetailsByCompanyId -------");
  		
  		return Response.ok().setPayload(companyMasterService.getCompanyDetailsByCompanyId(companyID));
  		
  	}
  	
  	@GetMapping( value = "/getAllCompanyDetails")
  	public Response getAllCompanyDetails() {
  		
  		logger.info("------- CompanyMasterController getAllCompanyDetails -------");
  		
  		return Response.ok().setPayload(companyMasterService.getAllCompanyDetails());
  		
  	}
  	
}
