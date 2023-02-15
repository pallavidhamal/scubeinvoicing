package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMasterEntity, String>{
	
	CustomerMasterEntity findByPanNo(String panNo);
	
	CustomerMasterEntity findByGstRegistrationNo(String gstRegistrationNo);

}
