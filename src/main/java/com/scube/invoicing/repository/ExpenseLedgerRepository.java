package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ExpenseLedgerEntity;

@Repository
public interface ExpenseLedgerRepository extends JpaRepository<ExpenseLedgerEntity, String> {
	
	@Query(value = "SELECT * FROM expense_ledger where fk_vendor=(?1) and created_at >=  STR_TO_DATE((?2), '%Y-%m-%d')"
			+ "and created_at <= STR_TO_DATE((?3), '%Y-%m-%d');", nativeQuery = true)
	List<ExpenseLedgerEntity> getExpenseLedgerListByVendorIDAndDateRange(String vendorID, String startDate, String endDate);

}
