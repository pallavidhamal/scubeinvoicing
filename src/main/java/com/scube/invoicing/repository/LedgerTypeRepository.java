package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.LedgerTypeEntity;

@Repository
public interface LedgerTypeRepository extends JpaRepository<LedgerTypeEntity, String> {

}
