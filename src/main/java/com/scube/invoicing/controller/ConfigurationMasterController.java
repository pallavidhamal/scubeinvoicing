package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.incoming.ConfigurationMasterService;
import com.scube.invoicing.dto.response.Response;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"api/v1/configurationMaster"}, produces = APPLICATION_JSON_VALUE)
public class ConfigurationMasterController {
	
	@Autowired
	ConfigurationMasterService configurationMasterService;

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationMasterController.class);
	
	
	@PostMapping(value = "/uploadLogo")
	public ResponseEntity<Response> uploadLogo(@RequestParam("logo") MultipartFile multipartFile) {
		
		logger.info("------- ConfigurationMasterController uploadLogo -------");
		Response response = new Response();
		String logoPath = configurationMasterService.uploadLogoAndSave(multipartFile);
		response.setRespData(logoPath);
		return ResponseEntity.ok(response);
		
	}
	
}
