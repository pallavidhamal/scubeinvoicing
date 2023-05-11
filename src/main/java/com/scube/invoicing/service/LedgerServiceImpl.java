package com.scube.invoicing.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.ExpenseCategoryItemListEntity;
import com.scube.invoicing.entity.ExpenseInfoEntity;
import com.scube.invoicing.entity.ExpenseLedgerEntity;
import com.scube.invoicing.entity.InvoiceLedgerEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ExpenseLedgerRepository;
import com.scube.invoicing.repository.InvoiceLedgerRepository;

@Service
public class LedgerServiceImpl implements LedgerService {
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	@Autowired
	LedgerMasterService ledgerMasterService;
	
	@Autowired
	ExpenseInfoService expenseInfoService;
	
	@Autowired
	InvoiceLedgerRepository invoiceLedgerRepository;
	
	@Autowired
	ExpenseLedgerRepository expenseLedgerRepository;
	
	static Base64.Encoder encoder = Base64.getEncoder();
	static Base64.Decoder decoder = Base64.getDecoder();
	
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
		customerLedger.setTransactionType("Debit");
		customerLedger.setAmount(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getSubTotal());
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
				serviceLedger.setTransactionType("Credit");
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
				gstInvoiceLedgerEntity.setTransactionType("Credit");
				gstInvoiceLedgerEntity.setAmount(customerInvoiceServiceEntityList.get(i).getGstAmount());
				gstInvoiceLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
				gstInvoiceLedgerEntity.setLedgerMasterEntity(ledgerMasterEntity);
				
				invoiceLedgerRepository.save(gstInvoiceLedgerEntity);
			}
			
		}
		
		// For TDS Ledger
		LedgerMasterEntity tdsLedgerMasterEntity = ledgerMasterService.getLedgerMasterRecords("TDS");
		
		InvoiceLedgerEntity tdsLedgerEntity = new InvoiceLedgerEntity();
		
		tdsLedgerEntity.setIsdeleted("N");
		tdsLedgerEntity.setTransactionType("Debit");
		tdsLedgerEntity.setCustomerInvoiceEntity(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity());
		tdsLedgerEntity.setAmount(checkInvoiceMailStatusEntity.getCustomerInvoiceEntity().getInvoiceTds());
		tdsLedgerEntity.setLedgerMasterEntity(tdsLedgerMasterEntity);
		
		invoiceLedgerRepository.save(tdsLedgerEntity);
		
		return true;
	}

	@Override
	public boolean updateLedgerEntryForInvoicePaymentReceived(CustomerInvoiceEntity customerInvoiceEntity) {
		// TODO Auto-generated method stub
		logger.info("----- LedgerServiceImpl updateLedgerEntryForInvoicePaymentReceived ------");
		
		// For Customer Ledger
		LedgerMasterEntity customerLedgerMasterEntity = ledgerMasterService.getCustomerLedgerMasterEntityRecord(customerInvoiceEntity.getCustomerMasterEntity());
				
		InvoiceLedgerEntity customerLedger = new InvoiceLedgerEntity();
		
		Double subTotal = Double.valueOf(new String(decoder.decode(customerInvoiceEntity.getSubTotal())));
		Double actualTds = Double.valueOf(new String(decoder.decode(customerInvoiceEntity.getActualTds())));
		Double amount = subTotal - actualTds;
				
		customerLedger.setIsdeleted("N");
		customerLedger.setTransactionType("Credit");
		customerLedger.setCustomerInvoiceEntity(customerInvoiceEntity);
		customerLedger.setAmount(encoder.encodeToString(String.valueOf(amount).getBytes(StandardCharsets.UTF_8)));
		customerLedger.setLedgerMasterEntity(customerLedgerMasterEntity);
				
		invoiceLedgerRepository.save(customerLedger);
				
		// For Service Ledger
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = customerInvoiceService.getCustomerInvoiceServiceEntityByInvoiceID(customerInvoiceEntity.getId());
				
		for(int i=0; i<customerInvoiceServiceEntityList.size(); i++) {
					
			List<LedgerMasterEntity> servicLedgerMasterEntityList = ledgerMasterService.getInvoiceServiceListLedgerMasterRecords(customerInvoiceServiceEntityList.get(i).getServiceMasterEntity());
					
			for(int j=0; j<servicLedgerMasterEntityList.size(); j++) {
						
				InvoiceLedgerEntity serviceLedger = new InvoiceLedgerEntity();
						
				serviceLedger.setIsdeleted("N");
				serviceLedger.setAmount(customerInvoiceServiceEntityList.get(i).getAmount());
				serviceLedger.setTransactionType("Debit");
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
				gstInvoiceLedgerEntity.setTransactionType("Debit");
				gstInvoiceLedgerEntity.setAmount(customerInvoiceServiceEntityList.get(i).getGstAmount());
				gstInvoiceLedgerEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
				gstInvoiceLedgerEntity.setLedgerMasterEntity(ledgerMasterEntity);
						
				invoiceLedgerRepository.save(gstInvoiceLedgerEntity);
			}
					
		}
				
		// For TDS Ledger
		LedgerMasterEntity tdsLedgerMasterEntity = ledgerMasterService.getLedgerMasterRecords("TDS");
				
		InvoiceLedgerEntity tdsLedgerEntity = new InvoiceLedgerEntity();
				
		tdsLedgerEntity.setIsdeleted("N");
		tdsLedgerEntity.setTransactionType("Credit");
		tdsLedgerEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
		tdsLedgerEntity.setAmount(customerInvoiceEntity.getActualTds());
		tdsLedgerEntity.setLedgerMasterEntity(tdsLedgerMasterEntity);
				
		invoiceLedgerRepository.save(tdsLedgerEntity);
		
		// For Bank Balance Ledger
		LedgerMasterEntity bankLedgerMasterEntity = ledgerMasterService.getLedgerMasterRecords("Bank");
						
		InvoiceLedgerEntity bankInvoiceLedgerEntity = new InvoiceLedgerEntity();
						
		bankInvoiceLedgerEntity.setIsdeleted("N");
		bankInvoiceLedgerEntity.setTransactionType("Credit");
		bankInvoiceLedgerEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
		bankInvoiceLedgerEntity.setAmount(customerInvoiceEntity.getTotalAmount());
		bankInvoiceLedgerEntity.setLedgerMasterEntity(bankLedgerMasterEntity);
						
		invoiceLedgerRepository.save(bankInvoiceLedgerEntity);
		
		return true;
	}

	@Override
	public boolean addLedgerEntryForExpense(ExpenseInfoEntity expenseInfoEntity) {
		// TODO Auto-generated method stub
		logger.info("----- LedgerServiceImpl addLedgerEntryForExpense ------");
		
		LedgerMasterEntity ledgerMasterEntity = ledgerMasterService.getLedgerMasterRecords("Expense");
		
		if(ledgerMasterEntity == null) {
			throw BRSException.throwException("Error : Ledger Master record does not exist" );
		}
		
		
		// For Vendor
		ExpenseLedgerEntity expenseLedgerEntity = new ExpenseLedgerEntity();
		
		expenseLedgerEntity.setIsdeleted("N");
		expenseLedgerEntity.setAmount(expenseInfoEntity.getTotalAmount());
		expenseLedgerEntity.setVendorMasterEntity(expenseInfoEntity.getVendorMasterEntity());
		expenseLedgerEntity.setLedgerMasterEntity(ledgerMasterEntity);
		expenseLedgerEntity.setTransactionType("Credit");
		
		expenseLedgerRepository.save(expenseLedgerEntity);
		
		List<ExpenseCategoryItemListEntity> expenseCategoryItemListEntityList = expenseInfoService.getExpenseItemsByExpenseID(expenseInfoEntity.getId());
		
		if(expenseCategoryItemListEntityList == null) {
			throw BRSException.throwException("Error : Expense Items does not exist");
		}
		
		for(ExpenseCategoryItemListEntity expenseCategoryItemListEntity : expenseCategoryItemListEntityList) {
			
			List<LedgerMasterEntity> categoryLedgerMasterEntityList = ledgerMasterService.getCategoryLedgerMasterRecords(expenseCategoryItemListEntity.getCategoryMasterEntity());
			
			for(int j=0; j<categoryLedgerMasterEntityList.size(); j++) {
						
				ExpenseLedgerEntity expenseCategoryLedgerEntity = new ExpenseLedgerEntity();
				
				expenseCategoryLedgerEntity.setIsdeleted("N");
				expenseCategoryLedgerEntity.setAmount(expenseInfoEntity.getTotalAmount());
				expenseCategoryLedgerEntity.setCategoryMasterEntity(expenseCategoryItemListEntity.getCategoryMasterEntity());
				expenseCategoryLedgerEntity.setLedgerMasterEntity(categoryLedgerMasterEntityList.get(j));
				expenseLedgerEntity.setTransactionType("Debit");
				
				expenseLedgerRepository.save(expenseCategoryLedgerEntity);
			}
			
		}
		
		return true;
	}

}
