package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;

@Repository
public interface LedgerMasterRepository extends JpaRepository<LedgerMasterEntity, String> {
	
	LedgerMasterEntity findByCustomerMasterEntity(CustomerMasterEntity customerMasterEntity);
	
	LedgerMasterEntity findByServiceMasterEntity(ServiceMasterEntity serviceMasterEntity);

}
