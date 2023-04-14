package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CategoryMasterEntity;
import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;

@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMasterEntity, String> {

	CategoryMasterEntity findByExpenseCategoryName(String expenseCategoryName);
	
	//for all customer list by status N
	@Query(value = "SELECT * FROM invoicing.mst_expense_category where is_deleted='N';", nativeQuery = true)
	List<CategoryMasterEntity> getAllExpenseCategoryistByStatus();

}
