package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.scube.invoicing.dto.ExpenseResponseDto;
import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;
import com.scube.invoicing.util.DateUtils;

public class ExpenseInfoAndItemListMapper {
	
	public static ExpenseResponseDto toExpenseResponseDto(ExpenseInfoEntity expenseInfoEntity, 
			Set<ExpenseCategoryItemListEntity> expenseCategoryItemListEntity) {
		return new ExpenseResponseDto()
				.setExpenseID(expenseInfoEntity.getId())
				.setPaymentAccount(expenseInfoEntity.getPaymentAccount())
				.setPaymentDate(DateUtils.formatDateToDDMMYYYYFormat(expenseInfoEntity.getPaymentDate()))
				.setReferenceNo(expenseInfoEntity.getReferenceNo())
				.setVendorInfo(VendorMasterMapper.toVendorMasterResponseDto(expenseInfoEntity.getVendorMasterEntity()))
				.setPaymentMethod(expenseInfoEntity.getPaymentMethodEntity().getMethodName())
				.setExpenseItemList(ExpenseItemListResponseMapper.toExpenseResponseDtosList(expenseCategoryItemListEntity));
	}
	
	
	public static List<ExpenseResponseDto> toExpenseResponseDtosList(List<ExpenseInfoEntity> expenseInfoEntitiesList,
			Set<ExpenseCategoryItemListEntity> expenseCategoryItemListEntitiesList) {
		// TODO Auto-generated method stub
		
		List<ExpenseResponseDto> expenseResponseDtosList = new ArrayList<ExpenseResponseDto>();
		for(ExpenseInfoEntity expenseInfoEntity : expenseInfoEntitiesList) {
			expenseResponseDtosList.add(toExpenseResponseDto(expenseInfoEntity, expenseCategoryItemListEntitiesList));			
		}
				
		return expenseResponseDtosList;
	}
	
}
