package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CategoryMasterEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;

@Repository
public interface LedgerMasterRepository extends JpaRepository<LedgerMasterEntity, String> {
	
	LedgerMasterEntity findByCustomerMasterEntity(CustomerMasterEntity customerMasterEntity);
	
	List<LedgerMasterEntity> findByServiceMasterEntity(ServiceMasterEntity serviceMasterEntity);
	
	List<LedgerMasterEntity> findByGstMasterEntity(GSTMasterEntity gstMasterEntity);
	
	List<LedgerMasterEntity> findByCategoryMasterEntity(CategoryMasterEntity categoryMasterEntity);
	
	LedgerMasterEntity findByLedgerName(String ledgerName);

}
