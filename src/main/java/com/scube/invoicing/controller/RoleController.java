package com.scube.invoicing.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/role"}, produces = APPLICATION_JSON_VALUE)
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@GetMapping( value = "/roles" )
	public String findAllRoles() {
		logger.info("***RoleController findALLRoles***");
	//	logger.info(NEW_ORDER_LOG, createdUser.toString());
		return "roles";
		
	}
	
}
