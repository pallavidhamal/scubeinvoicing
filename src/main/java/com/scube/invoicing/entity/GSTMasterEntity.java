package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_gst_details")
@Getter
@Setter
public class GSTMasterEntity extends BaseEntity {
	
	@Column (name = "percent_value")
	private double value;
	
	@Column (name = "description")
	private String description;

}
