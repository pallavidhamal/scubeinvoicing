package com.scube.invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CurrentOrdersInPipelineDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/currentOrdersPipelineDetailsExcel"})
public class CurrentOrdersInPipelineDetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrentOrdersInPipelineSummaryController.class);
	
	@Autowired
	CurrentOrdersInPipelineDetailService currentOrdersInPipelineDetailService;
	
	@PostMapping( value = "/getCurrentOrdersInPipelineDetailsExcelFileData")
	public Response getCurrentOrdersInPipelineDetailsExcelFileData() throws Exception {
			
		logger.info("*****CurrentOrdersInPipelineController getCurrentOrdersInPipelineDetailsExcelFileData*****");
		
		return Response.ok().setPayload(currentOrdersInPipelineDetailService.getCurrentOrdersInPipelineDetailsExcelFileData());
			
	}
	
	
	@PostMapping (value = "/getCurrentOrdersInPipelineDetailsByDateRange", consumes = APPLICATION_JSON_VALUE)
	public Response getCurrentOrdersInPipelineDetailsByDateRange(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("*****CurrentOrdersInPipelineController getCurrentOrdersInPipelineDetailsByDateRange*****");
		
		return Response.ok().setPayload(currentOrdersInPipelineDetailService.getCurrentOrdersInPipelineDetailsByDateRange(reportIncomingDto));
		
	}

}
