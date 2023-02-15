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

import com.scube.invoicing.dto.incoming.GSTMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.GSTMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/gstMaster"})
public class GSTMasterController {
	
	@Autowired
	GSTMasterService gstMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(GSTMasterController.class);
	
	@PostMapping(value = "/addGstInfoDetails", produces = APPLICATION_JSON_VALUE)
	public Response addGstInfoDetails(@Valid @RequestBody GSTMasterIncomingDto gstMasterIncomingDto) {
		
		logger.info("-------- GSTMasterController addGstInfoDetails ------");
		
		return Response.ok().setPayload(gstMasterService.addGstInfoDetails(gstMasterIncomingDto));
	}
	
	
	@PostMapping(value = "/updateGstInfoDetails", produces = APPLICATION_JSON_VALUE)
	public Response updateGstInfoDetails(@Valid @RequestBody GSTMasterIncomingDto gstMasterIncomingDto) {
		
		logger.info("-------- GSTMasterController updateGstInfoDetails ------");
		
		return Response.ok().setPayload(gstMasterService.updateGstInfoDetails(gstMasterIncomingDto));
	}

	
	@GetMapping(value = "/deleteGstInfoDetailsByGstId/{gstID}")
	public Response deleteGstInfoDetailsByGstId(@PathVariable String gstID) {
		
		logger.info("-------- GSTMasterController deleteGstInfoDetailsByGstId ------");
		
		return Response.ok().setPayload(gstMasterService.deleteGstInfoDetailsByGstId(gstID));
	}
	
	
	@GetMapping(value = "/getGstInfoDetailsByGstId/{gstID}")
	public Response getGstInfoDetailsByGstId(@PathVariable String gstID) {
		
		logger.info("-------- GSTMasterController getGstInfoDetailsByGstId ------");
		
		return Response.ok().setPayload(gstMasterService.getGstInfoDetailsByGstId(gstID));
	}
	
	
	@GetMapping(value = "/getAllGstInfoDetails")
	public Response getAllGstInfoDetails() {
		
		logger.info("-------- GSTMasterController getAllGstInfoDetails ------");
		
		return Response.ok().setPayload(gstMasterService.getAllGstInfoDetails());
	}

}
