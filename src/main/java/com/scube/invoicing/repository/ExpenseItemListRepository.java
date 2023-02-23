package com.scube.invoicing.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;

@Repository
public interface ExpenseItemListRepository extends JpaRepository<ExpenseCategoryItemListEntity, String> {
	
	Set<ExpenseCategoryItemListEntity> findByExpenseInfoEntity(ExpenseInfoEntity expenseInfoEntity);

}
