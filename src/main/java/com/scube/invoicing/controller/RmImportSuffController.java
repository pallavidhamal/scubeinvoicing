package com.scube.invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.RmImportSuffService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/RmImportSufficiency"})
public class RmImportSuffController {
	
	private static final Logger logger = LoggerFactory.getLogger(RmImportSuffController.class);
	
	@Autowired
	RmImportSuffService rmImportSuffService;
	
	@PostMapping(value="/getRmImportSuffData", consumes = APPLICATION_JSON_VALUE)
	public Response getRmImportData(@Valid @RequestBody ReportIncomingDto reportIncomingDto) throws Exception {
		
		logger.info("***Report Controller getOrdersToBePlaced***");
		
		return Response.ok().setPayload(rmImportSuffService.getRmImportData(reportIncomingDto));
		
		
	}

	@PostMapping(value="/getRmFormattedImportSuffData", consumes = APPLICATION_JSON_VALUE)
	public Response getRmFormattedImportSuffData(@Valid @RequestBody ReportIncomingDto reportIncomingDto) throws Exception {
		
		logger.info("***Report Controller getRmFormattedImportSuffData");
		return Response.ok().setPayload(rmImportSuffService.getRmFormattedImportData(reportIncomingDto));
		
		
	}
	
}
