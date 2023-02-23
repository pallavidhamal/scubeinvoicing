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

import com.scube.invoicing.dto.incoming.CategoryMasterIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.CategoryMasterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/categoryMaster"}, produces = APPLICATION_JSON_VALUE)
public class CategoryMasterController {
	
	@Autowired
	CategoryMasterService expenseCategoryMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterController.class);
	
	@PostMapping(value = "/addNewExpenseCategory", produces = APPLICATION_JSON_VALUE)
	public Response addNewExpenseCategory(@Valid @RequestBody CategoryMasterIncomingDto expenseCategoryMasterIncomingDto) {
		logger.info("----- ExpenseCategoryMasterController addNewExpenseCategory ------");
		return Response.ok().setPayload(expenseCategoryMasterService.addNewExpenseCategory(expenseCategoryMasterIncomingDto));
	}
	
	
	@PostMapping(value = "/updateExpenseCategory", produces = APPLICATION_JSON_VALUE)
	public Response updateExpenseCategory(@Valid @RequestBody CategoryMasterIncomingDto expenseCategoryMasterIncomingDto) {
		logger.info("----- ExpenseCategoryMasterController updateExpenseCategory ------");
		return Response.ok().setPayload(expenseCategoryMasterService.updateExpenseCategory(expenseCategoryMasterIncomingDto));
	}
	
	
	@GetMapping(value = "/deleteExpenseCategoryByCategoryID/{categoryID}")
	public Response deleteExpenseCategoryByCategoryID(@PathVariable("categoryID") String categoryID) {
		logger.info("----- ExpenseCategoryMasterController deleteExpenseCategoryByCategoryID ------");
		return Response.ok().setPayload(expenseCategoryMasterService.deleteExpenseCategoryByCategoryID(categoryID));
	}
	
	
	@GetMapping(value = "/getExpenseCategoryInfoByCategoryID/{categoryID}")
	public Response getExpenseCategoryInfoByCategoryID(@PathVariable("categoryID") String categoryID) {
		logger.info("----- ExpenseCategoryMasterController getExpenseCategoryInfoByCategoryID ------");
		return Response.ok().setPayload(expenseCategoryMasterService.getExpenseCategoryInfoByCategoryID(categoryID));
	}
	
	
	@GetMapping(value = "/getAllExpenseCategoryList")
	public Response getAllExpenseCategoryList() {
		logger.info("----- ExpenseCategoryMasterController getAllExpenseCategoryList ------");
		return Response.ok().setPayload(expenseCategoryMasterService.getAllExpenseCategoryList());
	}

}
