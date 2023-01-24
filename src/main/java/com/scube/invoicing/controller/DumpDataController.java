package com.scube.invoicing.controller;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.DumpDataIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.DumpDataService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/DumpData"}, produces = APPLICATION_JSON_VALUE)
public class DumpDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(DumpDataController.class);
	
	@Autowired
	DumpDataService dumpDataService;
	
	@PostMapping(value = "/getDumpDataByDate", consumes = APPLICATION_JSON_VALUE)
	public Response getDumpDataByDate(@Valid @RequestBody DumpDataIncomingDto dumpDataIncomingDto) throws Exception {
		
		logger.info("***DumpDataController getDumpDataByDate***");
		
		return Response.ok().setPayload(dumpDataService.getDumpDataByDate(dumpDataIncomingDto));
		
	}
	
	
	
	
	
	

}
