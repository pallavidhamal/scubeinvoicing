package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CategoryMasterEntity;

@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMasterEntity, String> {

	CategoryMasterEntity findByExpenseCategoryName(String expenseCategoryName);

}
