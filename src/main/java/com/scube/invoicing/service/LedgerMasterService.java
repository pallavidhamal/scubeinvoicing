package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.entity.CategoryMasterEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;
import com.scube.invoicing.entity.VendorMasterEntity;

public interface LedgerMasterService {
	
	// Customer - Entry in Ledger Master
	boolean addLedgerMasterEntryForCustomer(CustomerMasterEntity customerMasterEntity);
	
	// Category - Entry in Ledger Master
	boolean addLedgerMasterEntryForCategory(CategoryMasterEntity categoryMasterEntity);
	
	// Vendor - Entry in Ledger Master
	boolean addLedgerMasterEntryForVendor(VendorMasterEntity vendorMasterEntity);
	
	// Service - Entry in Ledger Master
	boolean addLedgerMasterEntryForService(ServiceMasterEntity serviceMasterEntity);
	
	// Get Customer Record in Ledger Master
	LedgerMasterEntity getCustomerLedgerMasterEntityRecord(CustomerMasterEntity customerMasterEntity);
	
	// Get Invoice Service Records Ledger Master
	List<LedgerMasterEntity> getInvoiceServiceListLedgerMasterRecords(ServiceMasterEntity serviceMasterEntity);
	
	// Get Service GST List in Ledger Master
	List<LedgerMasterEntity> getServiceGstListLedgerMasterRecords(GSTMasterEntity gstMasterEntity);
	
	// Get TDS Records in Ledger Master
	LedgerMasterEntity getTDSLedgerMasterRecords(String ledgerName);

}
