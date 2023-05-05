package com.scube.invoicing.repository;

import com.scube.invoicing.dto.LedgerResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.InvoiceLedgerEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceLedgerRepository extends JpaRepository<InvoiceLedgerEntity, String> {
	
	//get ledger entries for journal by cust ID

	// @Query(value = "SELECT * FROM invoice_ledger where fk_customer_invoice =(?1);", nativeQuery = true)
	List<InvoiceLedgerEntity> findByCustomerInvoiceEntity(CustomerInvoiceEntity customerInvoiceEntity);
	
	@Query(value = "SELECT * FROM invoice_ledger where fk_customer_invoice=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<InvoiceLedgerEntity> getCustomerLedgerListByCustomerIDAndDateRange(String customerID, String startDate, String endDate);

	
	//SELECT * FROM invoicing.invoice_ledger WHERE fk_customer_invoice='customerInvoiceEntityList.get(i)' and created_at between '' and '';
}
