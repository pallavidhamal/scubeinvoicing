package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.LastPurchaseReceiptEntity;

@Repository
public interface LastPurchaseReceiptRepository extends JpaRepository<LastPurchaseReceiptEntity, String>{
	
	List<LastPurchaseReceiptEntity> findByRecordDate(String recordDate);

}
