package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.ExpenseResponseDto;
import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;
import com.scube.invoicing.util.DateUtils;

public class ExpenseInfoAndItemListMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	public static ExpenseResponseDto toExpenseAndExpenseItemListResponseDto(ExpenseInfoEntity expenseInfoEntity,
			List<ExpenseCategoryItemListEntity> expenseCategoryItemListEntityList) {
		return new ExpenseResponseDto()
				.setExpenseID(expenseInfoEntity.getId())
				.setPaymentAccount(expenseInfoEntity.getPaymentAccount())
				.setExpenseDate(DateUtils.formattedDate(expenseInfoEntity.getPaymentDate()))
				.setReferenceNo(expenseInfoEntity.getReferenceNo())
				.setVendorInfo(VendorMasterMapper.toVendorMasterResponseDto(expenseInfoEntity.getVendorMasterEntity()))
				.setPaymentMethod(expenseInfoEntity.getPaymentMethodEntity().getMethodName())
				.setSubTotal(new String(decoder.decode(expenseInfoEntity.getSubTotal())))
				.setTotalAmount(new String(decoder.decode(expenseInfoEntity.getTotalAmount())))
				.setRoundOffAmount(new String(decoder.decode(expenseInfoEntity.getRoundOffAmount())))
				.setMemo(expenseInfoEntity.getMemo() != null ? expenseInfoEntity.getMemo() :null)
				.setExpenseItemList(ExpenseItemListResponseMapper.toExpenseItemListResponseDtosList(expenseCategoryItemListEntityList));
	}
	
	
	public static ExpenseResponseDto toExpenseResponseDto(ExpenseInfoEntity expenseInfoEntity) {
		return new ExpenseResponseDto()
				.setExpenseID(expenseInfoEntity.getId())
				.setPaymentAccount(expenseInfoEntity.getPaymentAccount())
				.setExpenseDate(DateUtils.formatDateToDDMMYYYYFormat(expenseInfoEntity.getPaymentDate()))
				.setReferenceNo(expenseInfoEntity.getReferenceNo())
				.setVendorInfo(VendorMasterMapper.toVendorMasterResponseDto(expenseInfoEntity.getVendorMasterEntity()))
				.setPaymentMethod(expenseInfoEntity.getPaymentMethodEntity().getMethodName())
				.setSubTotal(new String(decoder.decode(expenseInfoEntity.getSubTotal())))
				.setTotalAmount(new String(decoder.decode(expenseInfoEntity.getTotalAmount())))
				.setRoundOffAmount(new String(decoder.decode(expenseInfoEntity.getRoundOffAmount())))
				.setMemo(expenseInfoEntity.getMemo() != null ? expenseInfoEntity.getMemo() : null);
	}
	
	public static List<ExpenseResponseDto> toExpenseResponseDtosList(List<ExpenseInfoEntity> expenseInfoEntitiesList) {
		// TODO Auto-generated method stub
		
		List<ExpenseResponseDto> expenseResponseDtosList = new ArrayList<ExpenseResponseDto>();
		for(ExpenseInfoEntity expenseInfoEntity : expenseInfoEntitiesList) {
			expenseResponseDtosList.add(toExpenseResponseDto(expenseInfoEntity));			
		}
				
		return expenseResponseDtosList;
	}
	
}
