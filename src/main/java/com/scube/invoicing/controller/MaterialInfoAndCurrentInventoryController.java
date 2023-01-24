package com.scube.invoicing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.MaterialInfoAndCurrentInventoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/inventoryManagement"})
public class MaterialInfoAndCurrentInventoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialInfoAndCurrentInventoryController.class);
	
	@Autowired
	MaterialInfoAndCurrentInventoryService excelFileToDatabaseService;
	
	
	
	@PostMapping (value = "/getMaterialInfoData")
	public Response getMaterialInfoDataFromExcelToDB() throws Exception {
		
		logger.info("*****ExcelFileToDatabaseController getMaterialInfoData*****");	
		  
		   return Response.ok().setPayload(excelFileToDatabaseService.uploadExcelFileData());
		 
		
	}

}
