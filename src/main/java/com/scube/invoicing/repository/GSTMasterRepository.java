package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.GSTMasterEntity;

@Repository
public interface GSTMasterRepository extends JpaRepository<GSTMasterEntity, String>{

}
