package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;

@Repository
public interface CheckInvoiceMailStatusRepository extends JpaRepository<CheckInvoiceMailStatusEntity, String> {
	
	@Query(value = "SELECT * from check_invoice_mail_status where mail_status = 'N';", nativeQuery = true)
	List<CheckInvoiceMailStatusEntity> getInvoiceMailStatusEntityByMailStatus();

}
