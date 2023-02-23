package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ExpenseInfoEntity;

@Repository
public interface ExpenseInfoRepository extends JpaRepository<ExpenseInfoEntity, String> {

}
