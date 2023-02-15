package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;


@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoiceEntity, String>{
	
	CustomerInvoiceEntity findByCustomerMasterEntityAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);

}
