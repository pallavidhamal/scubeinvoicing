package com.scube.invoicing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scube.invoicing.entity.ImportedItemEntity;

public interface ImportedItemRepository extends JpaRepository<ImportedItemEntity, String> {
	
	//SELECT * FROM inventory_mgmt.mat_master where item_category='IMPORT';
	
	@Query(value = "SELECT * FROM mat_master WHERE  item_category='IMPORT';", nativeQuery = true)
	List<ImportedItemEntity> getImportedItemData();

}
