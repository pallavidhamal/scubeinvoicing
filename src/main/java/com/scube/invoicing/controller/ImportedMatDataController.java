package com.scube.invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.ImportedMatDataService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/ImportedMatData"})
public class ImportedMatDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportedMatDataController.class);
	
	@Autowired
	ImportedMatDataService importedMatDataService;
	
	@PostMapping (value="/getImpoertedMaterialData")
	public Response getImportedMatData() throws Exception {
		
		logger.info("*****ImportedMatDataController getImpoertedMaterialData*****");	
		  
		   return Response.ok().setPayload(importedMatDataService.uploadImportedMatData());
		 
	}
	
	@GetMapping (value = "/getAllImportedMatData")
	public Response getImportedMaterialData() throws Exception {
		
		logger.info("***Report Controller getOrdersToBePlaced***");
		
		return Response.ok().setPayload(importedMatDataService.getImportedMaterialData());
	}
	

}
