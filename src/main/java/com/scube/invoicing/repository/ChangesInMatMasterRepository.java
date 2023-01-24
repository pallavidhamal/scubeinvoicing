package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scube.invoicing.entity.ChangesInMatMasterEntity;

public interface ChangesInMatMasterRepository extends JpaRepository<ChangesInMatMasterEntity, String> {
	
	@Query (value = " SELECT * FROM audit_mat_master where auditTimestamp BETWEEN CURDATE()-7 AND CURDATE();", nativeQuery = true)
	List<ChangesInMatMasterEntity>  getChangesInMaterialMasterEntityByAuditDateAndTime();

	

	
	
}
