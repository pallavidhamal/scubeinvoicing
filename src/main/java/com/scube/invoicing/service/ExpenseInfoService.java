package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.ExpenseResponseDto;
import com.scube.invoicing.dto.incoming.ExpenseIncomingDto;

public interface ExpenseInfoService {
	
	boolean createNewExpense(@Valid ExpenseIncomingDto expenseIncomingDto);
	
	boolean updateExpense(@Valid ExpenseIncomingDto expenseIncomingDto);
	
	boolean deleteExpenseByExpenseID(String expenseID);
	
	ExpenseResponseDto getExpenseInfoByExpenseID(String expenseID);
	
	List<ExpenseResponseDto> getAllExpenseList();

}
