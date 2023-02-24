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

import com.scube.invoicing.dto.incoming.VendorMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.VendorMasterService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/vendorMaster"}, produces = APPLICATION_JSON_VALUE)
public class VendorMasterController {

	@Autowired
	VendorMasterService vendorMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/addNewVendor", produces = APPLICATION_JSON_VALUE)
	public Response addNewVendor(@Valid @RequestBody VendorMasterIncomingDto vendorMasterIncomingDto) {
		logger.info("----- VendorMasterController addNewVendor ------");
		return Response.ok().setPayload(vendorMasterService.addNewVendor(vendorMasterIncomingDto));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/updateVendor", produces = APPLICATION_JSON_VALUE)
	public Response updateVendor(@Valid @RequestBody VendorMasterIncomingDto vendorMasterIncomingDto) {
		logger.info("----- VendorMasterController updateVendor ------");
		return Response.ok().setPayload(vendorMasterService.updateVendor(vendorMasterIncomingDto));
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/deleteVendorByVendorID/{categoryID}")
	public Response deleteVendorByVendorID(@PathVariable("categoryID") String categoryID) {
		logger.info("----- VendorMasterController deleteVendorByVendorID ------");
		return Response.ok().setPayload(vendorMasterService.deleteVendorByVendorID(categoryID));
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getVendorInfoByVendorID/{categoryID}")
	public Response getVendorInfoByVendorID(@PathVariable("categoryID") String categoryID) {
		logger.info("----- VendorMasterController getVendorInfoByVendorID ------");
		return Response.ok().setPayload(vendorMasterService.getVendorInfoByVendorID(categoryID));
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getAllVendorList")
	public Response getAllVendorList() {
		logger.info("----- VendorMasterController getAllVendorList ------");
		return Response.ok().setPayload(vendorMasterService.getAllVendorList());
	}
	
}
