package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(	name = "current_orders_pipeline")
@Entity
@Getter @Setter
public class CurrentOrdersInPipelineSummaryEntity  {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "material_code")
	private String materialCode;
	
	@Column(name = "order_units")
	private double orderUnits;
	
	@Column(name = "record_date") 
	private String recordDate;
	 
	
	
}
