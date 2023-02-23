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

import com.scube.invoicing.dto.incoming.ExpenseIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.ExpenseInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"api/v1/expense"}, produces = APPLICATION_JSON_VALUE)
public class ExpenseInfoController {
	
	@Autowired
	ExpenseInfoService expenseInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(ExpenseInfoController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/createNewExpense", produces = APPLICATION_JSON_VALUE)
	public Response createNewExpense(@Valid @RequestBody ExpenseIncomingDto expenseIncomingDto) {
		logger.info("------- ExpenseInfoController createNewExpense ------");
		return Response.created().setPayload(expenseInfoService.createNewExpense(expenseIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/updateExpense", produces = APPLICATION_JSON_VALUE)
	public Response updateExpense(@Valid @RequestBody ExpenseIncomingDto expenseIncomingDto) {
		logger.info("------- ExpenseInfoController updateExpense ------");
		return Response.ok().setPayload(expenseInfoService.updateExpense(expenseIncomingDto));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/deleteExpenseByExpenseID/{expenseID}")
	public Response deleteExpenseByExpenseID(@PathVariable("expenseID") String expenseID) {
		logger.info("------- ExpenseInfoController deleteExpenseByExpenseID ------");
		return Response.ok().setPayload(expenseInfoService.deleteExpenseByExpenseID(expenseID));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getExpenseInfoByExpenseID/{expenseID}")
	public Response getExpenseInfoByExpenseID(@PathVariable("expenseID") String expenseID) {
		logger.info("------- ExpenseInfoController getExpenseInfoByExpenseID ------");
		return Response.ok().setPayload(expenseInfoService.getExpenseInfoByExpenseID(expenseID));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getAllExpenseList")
	public Response getAllExpenseList() {
		logger.info("------- ExpenseInfoController getAllExpenseList ------");
		return Response.ok().setPayload(expenseInfoService.getAllExpenseList());
	}
	

}
