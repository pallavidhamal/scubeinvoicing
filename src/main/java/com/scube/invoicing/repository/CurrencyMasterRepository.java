package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CurrencyMasterEntity;

@Repository
public interface CurrencyMasterRepository extends JpaRepository<CurrencyMasterEntity, String> {

}
