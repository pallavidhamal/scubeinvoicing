package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "configuration_master")
@Getter @Setter
public class ConfigurationMasterEntity extends BaseEntity {	
	
	@Column(name = "logo_path")
	private String logoPath;
	
}
