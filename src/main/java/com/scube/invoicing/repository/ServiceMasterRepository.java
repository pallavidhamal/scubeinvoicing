package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;

@Repository
public interface ServiceMasterRepository extends JpaRepository<ServiceMasterEntity, String> {
	
	ServiceMasterEntity findByServiceName(String serviceName);
	
	ServiceMasterEntity findByIdAndStatus(String serviceId, String serviceStatus);
	
	List<ServiceMasterEntity> findByStatus(String serviceStatus);
	
	//for all service list by status N
	@Query(value = "SELECT * FROM invoicing.mst_service_info where is_deleted='N';", nativeQuery = true)
	List<ServiceMasterEntity> getAllServiceListByStatus();

}
