package com.scube.invoicing.service;

import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;

public interface LedgerService {

	boolean addLedgerEntryForInvoice(CheckInvoiceMailStatusEntity checkInvoiceMailStatusEntity);
	
	boolean updateLedgerEntryForInvoicePaymentReceived(CustomerInvoiceEntity customerInvoiceEntity);
	
	boolean addLedgerEntryForExpense(ExpenseInfoEntity expenseInfoEntity);
	
}
