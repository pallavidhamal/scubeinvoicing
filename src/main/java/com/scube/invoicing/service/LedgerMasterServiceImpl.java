package com.scube.invoicing.service;

import com.scube.invoicing.entity.CategoryMasterEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.entity.LedgerTypeEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;
import com.scube.invoicing.entity.VendorMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.LedgerMasterRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedgerMasterServiceImpl implements LedgerMasterService {
	
	@Autowired
	LedgerTypeService ledgerTypeService;
	
	@Autowired
	LedgerMasterRepository ledgerMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LedgerMasterServiceImpl.class);

	@Override
	public LedgerMasterEntity getCustomerLedgerMasterEntityRecord(CustomerMasterEntity customerMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl getCustomerLedgerMasterEntityRecord ----");
		
		LedgerMasterEntity customerLedgerMasterEntity = ledgerMasterRepository.findByCustomerMasterEntity(customerMasterEntity);
		
		if(customerLedgerMasterEntity == null) {
			throw BRSException.throwException(EntityType.CUSTOMERLEDGER, ExceptionType.ENTITY_NOT_FOUND, customerMasterEntity.getCompanyName());
		}
		
		return customerLedgerMasterEntity;
	}

	@Override
	public List<LedgerMasterEntity> getInvoiceServiceListLedgerMasterRecords(ServiceMasterEntity serviceMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl getInvoiceServiceListLedgerMasterRecords ----");
		
		List<LedgerMasterEntity> serviceLedgerMasterEntityList = ledgerMasterRepository.findByServiceMasterEntity(serviceMasterEntity);
		
		if(serviceLedgerMasterEntityList == null) {
			throw BRSException.throwException(EntityType.SERVICELEDGER, ExceptionType.ENTITY_NOT_FOUND, serviceMasterEntity.getServiceName());
		}
		
		return serviceLedgerMasterEntityList;
	}

	@Override
	public List<LedgerMasterEntity> getServiceGstListLedgerMasterRecords(GSTMasterEntity gstMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl getServiceGstListLedgerMasterRecords ----");
		
		List<LedgerMasterEntity> gstLedgerMasterEntityList = ledgerMasterRepository.findByGstMasterEntity(gstMasterEntity);
		
		if(gstLedgerMasterEntityList == null) {
			throw BRSException.throwException(EntityType.GSTLEDGER, ExceptionType.ENTITY_NOT_FOUND, gstMasterEntity.getDescription());
		}
		
		return gstLedgerMasterEntityList;
	}

	@Override
	public boolean addLedgerMasterEntryForCategory(CategoryMasterEntity categoryMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl addCategoryLedgerMasterEntryForVendor ----");
		
		LedgerTypeEntity categoryLedgerTypeEntity = ledgerTypeService.getLedgerTypeEntityByLedgerName("Category Ledger");
		
		LedgerMasterEntity categoryLedgerMasterEntity = new LedgerMasterEntity();
		
		categoryLedgerMasterEntity.setIsdeleted("N");
		categoryLedgerMasterEntity.setLedgerName(categoryMasterEntity.getExpenseCategoryName());
		categoryLedgerMasterEntity.setCategoryMasterEntity(categoryMasterEntity);
		categoryLedgerMasterEntity.setLedgerTypeEntity(categoryLedgerTypeEntity);
		
		ledgerMasterRepository.save(categoryLedgerMasterEntity);
		
		return true;
	}

	@Override
	public boolean addLedgerMasterEntryForVendor(VendorMasterEntity vendorMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl addCategoryLedgerMasterEntryForVendor ----");
		
		LedgerTypeEntity vendorLedgerTypeEntity = ledgerTypeService.getLedgerTypeEntityByLedgerName("Vendor Ledger");
		
		LedgerMasterEntity categoryLedgerMasterEntity = new LedgerMasterEntity();
		
		categoryLedgerMasterEntity.setIsdeleted("N");
		categoryLedgerMasterEntity.setLedgerName(vendorMasterEntity.getCompanyName());
		categoryLedgerMasterEntity.setVendorMasterEntity(vendorMasterEntity);
		categoryLedgerMasterEntity.setLedgerTypeEntity(vendorLedgerTypeEntity);
		
		ledgerMasterRepository.save(categoryLedgerMasterEntity);
		
		return true;
	}

	@Override
	public boolean addLedgerMasterEntryForService(ServiceMasterEntity serviceMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl addServiceLedgerMasterEntryForService ----");
		
		LedgerTypeEntity serviceLedgerTypeEntity = ledgerTypeService.getLedgerTypeEntityByLedgerName("Service Ledger");
		
		LedgerMasterEntity serviceLedgerMasterEntity = new LedgerMasterEntity();
		
		serviceLedgerMasterEntity.setIsdeleted("N");
		serviceLedgerMasterEntity.setLedgerName(serviceMasterEntity.getServiceName());
		serviceLedgerMasterEntity.setServiceMasterEntity(serviceMasterEntity);
		serviceLedgerMasterEntity.setLedgerTypeEntity(serviceLedgerTypeEntity);
		
		ledgerMasterRepository.save(serviceLedgerMasterEntity);
		
		return true;
	}

	@Override
	public boolean addLedgerMasterEntryForCustomer(CustomerMasterEntity customerMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl addCustomerLedgerMasterEntryForCustomer ----");
		
		LedgerTypeEntity customerLedgerTypeEntity = ledgerTypeService.getLedgerTypeEntityByLedgerName("Customer Ledger");
		
		LedgerMasterEntity customerLedgerMasterEntity = new LedgerMasterEntity();
		
		customerLedgerMasterEntity.setIsdeleted("N");
		customerLedgerMasterEntity.setLedgerName(customerMasterEntity.getCompanyName());
		customerLedgerMasterEntity.setCustomerMasterEntity(customerMasterEntity);
		customerLedgerMasterEntity.setLedgerTypeEntity(customerLedgerTypeEntity);
		
		ledgerMasterRepository.save(customerLedgerMasterEntity);
		
		return true;
	}

	@Override
	public LedgerMasterEntity getLedgerMasterRecords(String ledgerName) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl getLedgerMasterRecords ----");
		
		LedgerMasterEntity tdsLedgerMasterEntity = ledgerMasterRepository.findByLedgerName(ledgerName);
		
		if(tdsLedgerMasterEntity == null) {
			throw BRSException.throwException(EntityType.TDSLEDGER, ExceptionType.ENTITY_NOT_FOUND, ledgerName);
		}
		
		return tdsLedgerMasterEntity;
	}

	@Override
	public List<LedgerMasterEntity> getCategoryLedgerMasterRecords(CategoryMasterEntity categoryMasterEntity) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerMasterServiceImpl getCategoryLedgerMasterRecords ----");
		
		List<LedgerMasterEntity> categoryLedgerMasterEntityList = ledgerMasterRepository.findByCategoryMasterEntity(categoryMasterEntity);
		
		return categoryLedgerMasterEntityList;
	}

}
