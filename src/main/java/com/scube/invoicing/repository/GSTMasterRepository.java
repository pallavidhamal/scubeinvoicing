package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;

@Repository
public interface GSTMasterRepository extends JpaRepository<GSTMasterEntity, String>{
	
	GSTMasterEntity findByDescription(String description);
	
	//for all customer list by status N
	@Query(value = "SELECT * FROM invoicing.mst_gst_details where is_deleted='N';", nativeQuery = true)
	List<GSTMasterEntity> getAllGstListByStatus();

}
