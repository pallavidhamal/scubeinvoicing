package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.VendorMasterEntity;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMasterEntity, String> {
	
	VendorMasterEntity findByCompanyName(String vendorName);
	
	//for all vendor list by status N
	@Query(value = "SELECT * FROM invoicing.mst_vendor where is_deleted='N';", nativeQuery = true)
	List<VendorMasterEntity> getAllVendorListByStatus();

}
