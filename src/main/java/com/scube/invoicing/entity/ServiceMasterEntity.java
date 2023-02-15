package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_service_info")
@Getter @Setter
public class ServiceMasterEntity extends BaseEntity {
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "status")
	private String status;
	
}
