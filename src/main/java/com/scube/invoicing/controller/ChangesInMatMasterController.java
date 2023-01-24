package com.scube.invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.scube.invoicing.service.ChangesInMatMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/ChangesInMaterialMaster"})
public class ChangesInMatMasterController {

	private static final Logger logger = LoggerFactory.getLogger(ChangesInMatMasterController.class);
	
	@Autowired
	ChangesInMatMasterService  changesInMatMasterService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping (value = "/getChangesInMatMasterByLast7Days")
	public Response getChangesInMatMasterByLast7Days () throws Exception {
		
		logger.info("*****ChangesInMatMasterController getChangesInMatMaster*****");
			  
		return Response.ok().setPayload(changesInMatMasterService.getChangesInMatMasterByLast7Days());
		
	}
	
	
}
