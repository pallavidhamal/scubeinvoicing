package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.MaterialInfoMasterEntity;

@Repository
public interface MaterialInfoMasterRepository extends JpaRepository<MaterialInfoMasterEntity, String> {
	
	MaterialInfoMasterEntity findMaterialInfoMasterEntityById(long id);
	
	MaterialInfoMasterEntity findByMaterialCode(String materialCode);
	
	MaterialInfoMasterEntity findMaterialInfoMasterEntityByMaterialCode(String materialCode);
	
}
