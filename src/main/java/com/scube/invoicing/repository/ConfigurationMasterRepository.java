package com.scube.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ConfigurationMasterEntity;

@Repository
public interface ConfigurationMasterRepository extends JpaRepository<ConfigurationMasterEntity, String> {

}
