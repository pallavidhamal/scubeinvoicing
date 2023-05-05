package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.LedgerMasterEntity;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMasterEntity, String>{
	
	CustomerMasterEntity findByCompanyName(String companyName);
	
	CustomerMasterEntity findByPanNo(String panNo);
	
	CustomerMasterEntity findByGstRegistrationNo(String gstRegistrationNo);
	
	//for all customer list by status N
	@Query(value = "SELECT * FROM invoicing.mst_customer where is_deleted ='N';", nativeQuery = true)
	List<CustomerMasterEntity> getAllCustomerListByStatus();
	


}
