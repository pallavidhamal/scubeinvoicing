package com.scube.invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.LastIssuanceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/lastIssuance"})
public class LastIssuanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(LastIssuanceController.class);
	
	@Autowired
	LastIssuanceService lastIssuanceService;
	
	
	@PostMapping (value = "/getLastIssuanceData")
	public Response getLastIssuanceDataExcelToDb() throws Exception {
		
		logger.info("*****LastIssuanceController getLastIssuanceDataExcelToDb*****");	
		  
		   return Response.ok().setPayload(lastIssuanceService.uploadLastIssuanceData());
		 
	}

}
