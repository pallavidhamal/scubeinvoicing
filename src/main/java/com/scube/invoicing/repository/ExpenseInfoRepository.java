package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;

@Repository
public interface ExpenseInfoRepository extends JpaRepository<ExpenseInfoEntity, String> {
	
	//for all customer list by status N
	@Query(value = "SELECT * FROM invoicing.expense where is_deleted='N';", nativeQuery = true)
	List<ExpenseInfoEntity> getAllExpenseListByStatus();

}
