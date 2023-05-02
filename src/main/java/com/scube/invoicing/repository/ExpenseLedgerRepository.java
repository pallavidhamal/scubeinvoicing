package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ExpenseLedgerEntity;

@Repository
public interface ExpenseLedgerRepository extends JpaRepository<ExpenseLedgerEntity, String> {

}
