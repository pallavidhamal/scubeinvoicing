package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

@Repository
public interface CreditNoteRepository extends JpaRepository<CustomerCreditNoteEntity, String>{
	
	CustomerCreditNoteEntity findByCustomerMasterEntityAndCreditNoteNo(CustomerMasterEntity customerMasterEntity, String creditNoteNo);

}
