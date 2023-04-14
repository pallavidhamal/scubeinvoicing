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
	
	// By Customer ID and Date Range
	@Query(value = "SELECT * FROM customer_invoice where fk_customer=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getCustomerInvoiceListByCustomerIDAndDateRange(String customerID, String startDate, String endDate);
	
	// For All Customer
	@Query(value = "SELECT * FROM customer_invoice where created_at >=  STR_TO_DATE((?1), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?2), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getAllCustomerInvoiceListByDateRange(String startDate, String endDate);
	
	//By Single Customer and Payment Status - Pending/Paid
	@Query(value = "SELECT * FROM customer_invoice where fk_customer=(?1) and payment_status=(?2) and created_at >=  STR_TO_DATE((?3), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?4), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getCustomerInvoiceListByCustomerIDAndPaymentStatusAndDateRange(String customerID, 
			String paymentStatus, String startDate, String endDate);
	
	// For All Customer and Payment Status
	@Query(value = "SELECT * FROM customer_invoice where payment_status=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getAllCustomerInvoiceListByPaymentStatusAndDateRange(String paymentStatus, String startDate, String endDate);
	
	//By Single Customer and Payment Status -> Payment Completed (TDS Report - Invoice)
	@Query(value = "SELECT * FROM customer_invoice where fk_customer=(?1) and payment_status = 'Payment Completed' and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getInvoiceListForPaymentCompletedInvoiceByCustomerIDAndDateRange(String customerID, String startDate, String endDate);
	
	// For All Customer and Payment Status -> Payment Completed (TDS Report - Invoice)
	@Query(value = "SELECT * FROM customer_invoice where payment_status = 'Payment Completed' and created_at >=  STR_TO_DATE((?1), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?2), '%Y-%m-%d');", nativeQuery = true)
	List<CustomerInvoiceEntity> getAllCustomerInvoiceListForPaymentCompletedInvoiceByDateRange(String startDate, String endDate);
	
	//for all customer invoices list by status N
	@Query(value = "SELECT * FROM invoicing.customer_invoice where is_deleted='N';", nativeQuery = true)
	List<CustomerInvoiceEntity> getAllCustomerInvoiceListByStatus();

}
