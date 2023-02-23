package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CategoryMasterResponseDto;
import com.scube.invoicing.entity.CategoryMasterEntity;

public class CategoryMasterResponseMapper {

	public static CategoryMasterResponseDto toExpenseCategoryMasterResponseDto(CategoryMasterEntity expenseCategoryMasterEntity) {
		return new CategoryMasterResponseDto()
				.setExpenseCategoryID(expenseCategoryMasterEntity.getId())
				.setExpenseCategoryName(expenseCategoryMasterEntity.getExpenseCategoryName());
	}
	
	
	public static List<CategoryMasterResponseDto> toExpenseCategoryMasterResponseDtosList(
			List<CategoryMasterEntity> expenseCategoryMasterEntitiesList) {
		// TODO Auto-generated method stub	
		List<CategoryMasterResponseDto> expenseCategoryMasterResponseDtos = new ArrayList<CategoryMasterResponseDto>();
		for(CategoryMasterEntity expenseCategoryMasterEntity : expenseCategoryMasterEntitiesList) {
			expenseCategoryMasterResponseDtos.add(toExpenseCategoryMasterResponseDto(expenseCategoryMasterEntity));			
		}	
		return expenseCategoryMasterResponseDtos;
	}
	
}
