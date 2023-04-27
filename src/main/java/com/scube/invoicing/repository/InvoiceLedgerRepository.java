package com.scube.invoicing.repository;

import com.scube.invoicing.entity.InvoiceLedgerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceLedgerRepository extends JpaRepository<InvoiceLedgerEntity, String> {

}
