package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

@Repository
public interface CreditNoteRepository extends JpaRepository<CustomerCreditNoteEntity, String>{
	
	CustomerCreditNoteEntity findByCustomerMasterEntityAndCreditNoteNo(CustomerMasterEntity customerMasterEntity, String creditNoteNo);
	
	// For a single Customer and Date Range
	@Query(value = "SELECT * FROM customer_credit_note where fk_customer=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerCreditNoteEntity> getCustomerCreditNoteListByCustomerIDAndDateRange(String customerID, String startDate, String endDate);
	
	// For All Customers
	@Query(value = "SELECT * FROM customer_credit_note where created_at >=  STR_TO_DATE((?1), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?2), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerCreditNoteEntity> getAllCustomerCreditNoteListByDateRange(String startDate, String endDate);
	
	@Query(value = "SELECT * FROM customer_credit_note where created_at >=  STR_TO_DATE((?1), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?2), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerCreditNoteEntity> getCustomerCreditNoteEntityByDateRange(String startDate, String endDate);
	
	//for all credit note list by status N
	@Query(value = "SELECT * FROM invoicing.customer_credit_note where is_deleted='N';", nativeQuery = true)
	List<CustomerCreditNoteEntity> getAllCreditNoteListByStatus();
	

}
