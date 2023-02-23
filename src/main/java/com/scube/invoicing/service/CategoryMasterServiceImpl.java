package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CategoryMasterResponseDto;
import com.scube.invoicing.dto.incoming.CategoryMasterIncomingDto;
import com.scube.invoicing.dto.mapper.CategoryMasterResponseMapper;
import com.scube.invoicing.entity.CategoryMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.CategoryMasterRepository;

@Service
public class CategoryMasterServiceImpl implements CategoryMasterService {

	@Autowired
	CategoryMasterRepository expenseCategoryMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterServiceImpl.class);

	@Override
	public boolean addNewExpenseCategory(@Valid CategoryMasterIncomingDto expenseCategoryMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- ExpenseCategoryMasterServiceImpl addNewExpenseCategory ------");
		
		if(expenseCategoryMasterIncomingDto.getCategoryName() == "" || 
				expenseCategoryMasterIncomingDto.getCategoryName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Expense Category Name cannot be blank or null.");
		}
		
		CategoryMasterEntity dupliExpenseCategoryMasterEntity = expenseCategoryMasterRepository.
				findByExpenseCategoryName(expenseCategoryMasterIncomingDto.getCategoryName());
		
		if(dupliExpenseCategoryMasterEntity != null) {
			throw BRSException.throwException(EntityType.CATEGORY, ExceptionType.ALREADY_EXIST, expenseCategoryMasterIncomingDto.getCategoryName());
		}
		
		CategoryMasterEntity expenseCategoryMasterEntity = new CategoryMasterEntity();
		expenseCategoryMasterEntity.setIsdeleted("N");
		expenseCategoryMasterEntity.setExpenseCategoryName(expenseCategoryMasterIncomingDto.getCategoryName());

		expenseCategoryMasterRepository.save(expenseCategoryMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateExpenseCategory(@Valid CategoryMasterIncomingDto expenseCategoryMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- ExpenseCategoryMasterServiceImpl updateExpenseCategory ------");
		
		if(expenseCategoryMasterIncomingDto.getCategoryName() == "" || 
				expenseCategoryMasterIncomingDto.getCategoryName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Expense Category Name cannot be blank or null.");
		}
		
		CategoryMasterEntity expenseCategoryMasterEntity = expenseCategoryMasterRepository.findById(expenseCategoryMasterIncomingDto.getCategoryID()).get();
		expenseCategoryMasterEntity.setIsdeleted("N");
		expenseCategoryMasterEntity.setExpenseCategoryName(expenseCategoryMasterIncomingDto.getCategoryName());

		expenseCategoryMasterRepository.save(expenseCategoryMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteExpenseCategoryByCategoryID(String categoryID) {
		// TODO Auto-generated method stub
		logger.info("----- ExpenseCategoryMasterServiceImpl deleteExpenseCategoryByCategoryID ------");
		
		CategoryMasterEntity expenseCategoryMasterEntity = expenseCategoryMasterRepository.findById(categoryID).get();
		expenseCategoryMasterRepository.delete(expenseCategoryMasterEntity);
		return true;
	}

	@Override
	public CategoryMasterResponseDto getExpenseCategoryInfoByCategoryID(String categoryID) {
		// TODO Auto-generated method stub
		logger.info("----- ExpenseCategoryMasterServiceImpl getExpenseCategoryInfoByCategoryID ------");
		
		CategoryMasterEntity expenseCategoryMasterEntity = expenseCategoryMasterRepository.findById(categoryID).get();
		
		return CategoryMasterResponseMapper.toExpenseCategoryMasterResponseDto(expenseCategoryMasterEntity);
	}

	@Override
	public List<CategoryMasterResponseDto> getAllExpenseCategoryList() {
		// TODO Auto-generated method stub
		logger.info("----- ExpenseCategoryMasterServiceImpl getAllExpenseCategoryList ------");
		
		List<CategoryMasterEntity> expenseCategoryMasterEntitesList = expenseCategoryMasterRepository.findAll();
		
		return CategoryMasterResponseMapper.toExpenseCategoryMasterResponseDtosList(expenseCategoryMasterEntitesList);
	}

	@Override
	public CategoryMasterEntity getCategoryMasterEntityByCategoryID(String categoryID) {
		// TODO Auto-generated method stub
		return expenseCategoryMasterRepository.findById(categoryID).get();
	}
	
}
