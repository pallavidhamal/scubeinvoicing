package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

@Repository
public interface CreditNoteDetailsRepository extends JpaRepository<CustomerCreditNoteDetailsEntity, String> {
	
	List<CustomerCreditNoteDetailsEntity> findByCustomerMasterEntityAndCustomerCreditNoteEntity(
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity);

}
