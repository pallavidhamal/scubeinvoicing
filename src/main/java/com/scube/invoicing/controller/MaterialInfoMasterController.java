package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.MaterialInfoMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.MaterialInfoMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/materialInfoData"}, produces = APPLICATION_JSON_VALUE)
public class MaterialInfoMasterController {
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialInfoMasterController.class);
	
	@Autowired
	MaterialInfoMasterService materialInfoMasterService;
	
	@PostMapping (value = "/addMaterialInfoData", consumes = APPLICATION_JSON_VALUE)
	public Response addMaterialInfoDetails(@Valid @RequestBody MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto) {
		
		logger.info("*****MaterialInfoMasterController addMaterialInfoData*****");	
		
		return Response.ok().setPayload(materialInfoMasterService.addMaterialInfoDetails(materialInfoMasterIncomingDto));
		
	}
	
	
	@PostMapping (value = "/updateMaterialInfoData", consumes = APPLICATION_JSON_VALUE)
	public Response updateMaterialInfoDetails(@Valid @RequestBody MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto) {
		
		logger.info("*****MaterialInfoMasterController updateMaterialInfoData*****");	
		
		return Response.ok().setPayload(materialInfoMasterService.updateMaterialInfoDetails(materialInfoMasterIncomingDto));
		
	}
	
	
	@GetMapping (value = "/getMaterialInfoById/{id}")
	public Response getMaterialInfoById (@PathVariable ("id") long materialInfoId) {
		
		logger.info("*****MaterialInfoMasterController getMaterialInfoById*****");
		
		return Response.ok().setPayload(materialInfoMasterService.getMaterialInfoById(materialInfoId));	
		
	}
	
	
	@GetMapping (value = "/getMaterialInfoByMaterialCode/{materialCode}")
	public Response getMaterialInfoByMaterialCode(@PathVariable ("materialCode") String materialCode) {
		
		logger.info("*****MaterialInfoMasterController getMaterialInfoByMaterialCode*****");
		
		return Response.ok().setPayload(materialInfoMasterService.getMaterialInfoByMaterialCode(materialCode));
		
	}
	
	@GetMapping (value = "/getAllMaterialInfo")
	public Response getAllMaterialInfo() {
		
		logger.info("*****MaterialInfoMasterController getAllMaterialInfo*****");
		
		return Response.ok().setPayload(materialInfoMasterService.getAllMaterialInfo());	
		
	}
	
	
	@GetMapping (value = "/getAllUnitOfMeasurementValue")
	public Response getAllUnitOfMeasurement() {
		
		logger.info("*****MaterialInfoMasterController getAllUnitOfMeasurementValue*****");
		
		return Response.ok().setPayload(materialInfoMasterService.getAllUnitOfMeasurementValue());
		
	}
	
	
	@GetMapping (value = "/deleteMaterialInfoRecordById/{id}")
	public Response deleteMaterialInfoRecordById(@PathVariable ("id") long materialInfoId) {
		
		logger.info("*****MaterialInfoMasterController deleteMaterialInfoRecordById*****");
		
		return Response.ok().setPayload(materialInfoMasterService.deleteMaterialInfoRecordById(materialInfoId));
		
	}

}
