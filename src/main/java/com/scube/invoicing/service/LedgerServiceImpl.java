package com.scube.invoicing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.InvoiceLedgerEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.repository.InvoiceLedgerRepository;
import com.scube.invoicing.repository.LedgerMasterRepository;
import com.scube.invoicing.repository.LedgerTypeRepository;

@Service
public class LedgerServiceImpl implements LedgerService {
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	@Autowired
	LedgerTypeRepository ledgerTypeRepository;
	
	@Autowired
	LedgerMasterRepository ledgerMasterRepository;
	
	@Autowired
	InvoiceLedgerRepository invoiceLedgerRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LedgerServiceImpl.class);

	@Override
	public boolean addLedgerEntryForInvoice(CheckInvoiceMailStatusEntity checkInvoiceMailStatusEntity) {
		// TODO Auto-generated method stub
		logger.info("----- LedgerServiceImpl addLedgerEntryForInvoice ------");
		
		// For Customer Ledger
		LedgerMasterEntity customerLedgerMasterEntity = ledgerMasterRepository.
				findByCustomerMasterEntity(checkInvoiceMailStatusEntity.getCustomerMasterEntity());
		
		InvoiceLedgerEntity customerLedger = new InvoiceLedgerEntity();
		
		customerLedger.setIsdeleted("N");
		customerLedger.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		customerLedger.setLedgerMasterEntity(customerLedgerMasterEntity);
		
		invoiceLedgerRepository.save(customerLedger);
		
		// For Service Ledger
		InvoiceLedgerEntity serviceLedger = new InvoiceLedgerEntity();
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = customerInvoiceService.
				getCustomerInvoiceServiceEntityByInvoiceID(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getId());
		
		for(int i=0; i<customerInvoiceServiceEntityList.size(); i++) {			
			LedgerMasterEntity servicLedgerMasterEntity = ledgerMasterRepository.
					findByServiceMasterEntity(customerInvoiceServiceEntityList.get(i).getServiceMasterEntity());
			
			serviceLedger.setIsdeleted("N");
			serviceLedger.setAmount(customerInvoiceServiceEntityList.get(i).getAmount());
			serviceLedger.setCustomerInvoiceEntity(customerInvoiceServiceEntityList.get(i).getCustomerInvoiceEntity());
			serviceLedger.setLedgerMasterEntity(servicLedgerMasterEntity);
			
		}
		invoiceLedgerRepository.save(serviceLedger); 
			
		// For Invoice Ledger
		InvoiceLedgerEntity invoiceLedgerEntity = new InvoiceLedgerEntity();
		
		invoiceLedgerEntity.setIsdeleted("N");
		invoiceLedgerEntity.setAmount(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getTotalAmount());
		invoiceLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		
		invoiceLedgerRepository.save(invoiceLedgerEntity);
		
		/*
		 * // For GST Ledger InvoiceLedgerEntity gstInvoiceLedgerEntity = new
		 * InvoiceLedgerEntity();
		 * 
		 * gstInvoiceLedgerEntity.setIsdeleted("N"); gstInvoiceLedgerEntity.set
		 */
		
		return true;
	}
	
	

}
