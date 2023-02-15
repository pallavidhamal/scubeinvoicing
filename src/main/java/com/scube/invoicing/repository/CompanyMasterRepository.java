package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CompanyMasterEntity;

@Repository
public interface CompanyMasterRepository extends JpaRepository<CompanyMasterEntity, String> {

}
