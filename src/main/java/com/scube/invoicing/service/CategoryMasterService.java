package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CategoryMasterResponseDto;
import com.scube.invoicing.dto.incoming.CategoryMasterIncomingDto;
import com.scube.invoicing.entity.CategoryMasterEntity;

public interface CategoryMasterService {
	
	boolean addNewExpenseCategory(@Valid CategoryMasterIncomingDto expenseCategoryMasterIncomingDto);
	
	boolean updateExpenseCategory(@Valid CategoryMasterIncomingDto expenseCategoryMasterIncomingDto);
	
	boolean deleteExpenseCategoryByCategoryID(String categoryID);
	
	CategoryMasterResponseDto getExpenseCategoryInfoByCategoryID(String categoryID);
	
	List<CategoryMasterResponseDto> getAllExpenseCategoryList();
	
	CategoryMasterEntity getCategoryMasterEntityByCategoryID(String categoryID);

}
