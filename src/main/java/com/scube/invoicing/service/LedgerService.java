package com.scube.invoicing.service;

import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;

public interface LedgerService {

	boolean addLedgerEntryForInvoice(CheckInvoiceMailStatusEntity checkInvoiceMailStatusEntity);
	
}
