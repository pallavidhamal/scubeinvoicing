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
import com.scube.invoicing.service.CurrentOrdersInPipelineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/currentOrdersInPipelineSummary"})
public class CurrentOrdersInPipelineSummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrentOrdersInPipelineSummaryController.class);	
	
	@Autowired
	CurrentOrdersInPipelineService currentOrdersInPipelineService;
	
	
	@PostMapping (value = "/getCurrentOrdersInPipelineSummaryExcelFileData")
	public Response getCurrentOrdersInPipelineSummaryExcelFileData() throws Exception {
		
		logger.info("*****CurrentOrdersInPipelineController getCurrentOrdersInPipelineSummaryExcelFileData*****");
		  
		   return Response.ok().setPayload(currentOrdersInPipelineService.getCurrentOrdersInPipelineSummaryExcelFileData()); 
		
	}
	
}
