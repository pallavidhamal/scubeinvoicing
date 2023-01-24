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
import com.scube.invoicing.service.LastPurchaseReceiptService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/inventoryManagement"})
public class LastPurchaseReceiptController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LastPurchaseReceiptController.class);
	
	@Autowired
	LastPurchaseReceiptService lastPurchaseReceiptService;
	
	
	@PostMapping (value = "/getLastPurchaseReceipt")
	public Response getLastPurchaseReceiptExcelToDb() throws Exception {
		
		logger.info("*****LastPurchaseReceiptController getLastPurchaseReceipt*****");
		  
		return Response.ok().setPayload(lastPurchaseReceiptService.uploadLastPurchaseReceiptData());
 
	}

}
