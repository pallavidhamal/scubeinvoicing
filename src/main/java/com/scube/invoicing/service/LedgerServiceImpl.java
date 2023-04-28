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

@Service
public class LedgerServiceImpl implements LedgerService {
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	@Autowired
	LedgerMasterService ledgerMasterService;
	
	@Autowired
	InvoiceLedgerRepository invoiceLedgerRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LedgerServiceImpl.class);

	@Override
	public boolean addLedgerEntryForInvoice(CheckInvoiceMailStatusEntity checkInvoiceMailStatusEntity) {
		// TODO Auto-generated method stub
		logger.info("----- LedgerServiceImpl addLedgerEntryForInvoice ------");
		
		// For Customer Ledger
		LedgerMasterEntity customerLedgerMasterEntity = ledgerMasterService.
				getCustomerLedgerMasterEntityRecord(checkInvoiceMailStatusEntity.getCustomerMasterEntity());
		
		InvoiceLedgerEntity customerLedger = new InvoiceLedgerEntity();
		
		customerLedger.setIsdeleted("N");
		customerLedger.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		customerLedger.setLedgerMasterEntity(customerLedgerMasterEntity);
		
		invoiceLedgerRepository.save(customerLedger);
		
		// For Service Ledger
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = customerInvoiceService.
				getCustomerInvoiceServiceEntityByInvoiceID(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getId());
		
		for(int i=0; i<customerInvoiceServiceEntityList.size(); i++) {
			
			List<LedgerMasterEntity> servicLedgerMasterEntityList = ledgerMasterService.
					getInvoiceServiceListLedgerMasterRecords(customerInvoiceServiceEntityList.get(i).getServiceMasterEntity());
			
			for(int j=0; j<servicLedgerMasterEntityList.size(); j++) {
				
				InvoiceLedgerEntity serviceLedger = new InvoiceLedgerEntity();
				
				serviceLedger.setIsdeleted("N");
				serviceLedger.setAmount(customerInvoiceServiceEntityList.get(i).getAmount());
				serviceLedger.setCustomerInvoiceEntity(customerInvoiceServiceEntityList.get(i).getCustomerInvoiceEntity());
				serviceLedger.setLedgerMasterEntity(servicLedgerMasterEntityList.get(j));
				
				invoiceLedgerRepository.save(serviceLedger);
			}
			
		}
		
/*				
		// For Invoice Ledger
		InvoiceLedgerEntity invoiceLedgerEntity = new InvoiceLedgerEntity();
		
		invoiceLedgerEntity.setIsdeleted("N");
		invoiceLedgerEntity.setAmount(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getTotalAmount());
		invoiceLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		
		invoiceLedgerRepository.save(invoiceLedgerEntity);
*/
		
		// For GST Ledger 
		for(int i=0; i<customerInvoiceServiceEntityList.size(); i++) {
			
			logger.info("Invoice Services :---- " + customerInvoiceServiceEntityList.size());
			
			List<LedgerMasterEntity> gstLedgerMasterEntityList = ledgerMasterService.
					getServiceGstListLedgerMasterRecords(customerInvoiceServiceEntityList.get(i).getGstMasterEntity()); 
			
			for(LedgerMasterEntity ledgerMasterEntity : gstLedgerMasterEntityList) {
				
				InvoiceLedgerEntity gstInvoiceLedgerEntity = new InvoiceLedgerEntity();
			
				gstInvoiceLedgerEntity.setIsdeleted("N");
				gstInvoiceLedgerEntity.setAmount(customerInvoiceServiceEntityList.get(i).getGstAmount());
				gstInvoiceLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
				gstInvoiceLedgerEntity.setLedgerMasterEntity(ledgerMasterEntity);
				
				invoiceLedgerRepository.save(gstInvoiceLedgerEntity);
			}
			
		}
		
		// For TDS Ledger
		LedgerMasterEntity tdsLedgerMasterEntity = ledgerMasterService.getTDSLedgerMasterRecords("TDS");
		
		InvoiceLedgerEntity tdsLedgerEntity = new InvoiceLedgerEntity();
		
		tdsLedgerEntity.setIsdeleted("N");
		tdsLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		tdsLedgerEntity.setAmount(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getActualTds());
		tdsLedgerEntity.setLedgerMasterEntity(tdsLedgerMasterEntity);
		
		invoiceLedgerRepository.save(tdsLedgerEntity);
		
		return true;
	}

}
