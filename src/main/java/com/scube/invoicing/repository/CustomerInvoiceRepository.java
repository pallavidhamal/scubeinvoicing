package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;


@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoiceEntity, String> {
	
	CustomerInvoiceEntity findByCustomerMasterEntityAndInvoiceNo(CustomerMasterEntity customerMasterEntity, String invoiceNo);
	
	List<CustomerInvoiceEntity> findByCustomerMasterEntity(CustomerMasterEntity customerMasterEntity);
	
	@Query(value = "SELECT * FROM customer_credit_note where fk_customer_master=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getCustomerInvoiceListByCustomerIDAndDateRange(String customerID, String startDate, String endDate);

}
