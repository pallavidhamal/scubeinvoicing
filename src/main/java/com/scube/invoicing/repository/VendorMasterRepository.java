package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.VendorMasterEntity;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMasterEntity, String> {
	
	VendorMasterEntity findByVendorName(String vendorName);

}
