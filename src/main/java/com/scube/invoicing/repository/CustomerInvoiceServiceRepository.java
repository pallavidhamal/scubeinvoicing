package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

@Repository
public interface CustomerInvoiceServiceRepository extends JpaRepository<CustomerInvoiceServiceEntity, String>{

	List<CustomerInvoiceServiceEntity> findByCustomerInvoiceEntityAndCustomerMasterEntity(
			CustomerInvoiceEntity customerInvoiceEntity, CustomerMasterEntity customerMasterEntity);
	
	List<CustomerInvoiceServiceEntity> findByCustomerInvoiceEntity(CustomerInvoiceEntity customerInvoiceEntity);
	
}
