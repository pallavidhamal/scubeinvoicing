package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.ServiceMasterEntity;

@Repository
public interface ServiceMasterRepository extends JpaRepository<ServiceMasterEntity, String> {
	
	ServiceMasterEntity findByServiceName(String serviceName);
	
	ServiceMasterEntity findByIdAndStatus(String serviceId, String serviceStatus);
	
	List<ServiceMasterEntity> findByStatus(String serviceStatus);

}
