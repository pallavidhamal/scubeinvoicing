package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(	name = "current_orders_pipeline_details")
@Getter @Setter
public class CurrentOrdersInPipelineDetailsEntity {
	
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column (name = "po_type")
	private String poType;
	
	@Column (name = "po_no")
	private String poNo;
	
	@Column (name = "po_date")
	private String poDate;
	
	@Column (name = "line_no")
	private String lineNo;
	
	@Column (name = "item")
	private String item;
	
	@Column (name = "current_order_pipeline_units")
	private double currentOrderPipelineUnits;
	
	@Column (name = "rate")
	private double rate;
	
	@Column (name = "currency")
	private String currency;
	
	@Column (name = "report_date")
	private String reportDate;

}
