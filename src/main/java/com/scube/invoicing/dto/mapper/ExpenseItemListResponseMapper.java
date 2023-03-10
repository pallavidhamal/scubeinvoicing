package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.ExpenseItemListResponseDto;
import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;

public class ExpenseItemListResponseMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	public static ExpenseItemListResponseDto toExpenseItemListResponseDto(ExpenseCategoryItemListEntity expenseCategoryItemListEntity) {
		return new ExpenseItemListResponseDto()
				// Expense Item ID
				.setExpenseItemID(expenseCategoryItemListEntity.getId())
				
				// Category ID/ Category Name
				.setCategoryID(expenseCategoryItemListEntity.getCategoryMasterEntity().getId())
				.setCategory(expenseCategoryItemListEntity.getCategoryMasterEntity().getExpenseCategoryName())
				
				// Description/ Amount
				.setDescription(expenseCategoryItemListEntity.getDescription())
				.setAmount(new String(decoder.decode(expenseCategoryItemListEntity.getAmount())))
				
				// GST Details
				.setGstID(expenseCategoryItemListEntity.getGstMasterEntity().getId())
				.setGstTag(expenseCategoryItemListEntity.getGstMasterEntity().getDescription())
				.setGstValue(expenseCategoryItemListEntity.getGstMasterEntity().getValue())
				
				// Customer Info
				.setCustomerMasterResponseDto(CustomerMasterMapper.toCustomerMasterResponseDto(expenseCategoryItemListEntity.getCustomerMasterEntity()));
	}
	
	public static List<ExpenseItemListResponseDto> toExpenseItemListResponseDtosList(List<ExpenseCategoryItemListEntity> expenseCategoryItemListEntity2) {
		// TODO Auto-generated method stub
		
		List<ExpenseItemListResponseDto> expenseItemListResponseDtosList = new ArrayList<ExpenseItemListResponseDto>();
		for(ExpenseCategoryItemListEntity expenseCategoryItem : expenseCategoryItemListEntity2) {
			expenseItemListResponseDtosList.add(toExpenseItemListResponseDto(expenseCategoryItem));			
		}
				
		return expenseItemListResponseDtosList;
	}

}
