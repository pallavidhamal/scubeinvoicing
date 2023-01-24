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
@Getter @Setter
@Table (name = "mat_import")
public class ImportedMatDataEntity {
	
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "material_code")
	private String materialCode;
	
	@Column (name="mat_desc")
	private String materialDescription;
	
	@Column (name="report_date")
	private String reportDate;
	
	@Column (name="domestic_imported ")
	private String DomesticOrImportedBasedOnLocationOfSupplier;
	
	@Column (name="inland_transit_days")
	private double InlandTransit;
	
	@Column (name="material_in_inland_transit_units")
	private double MaterialInlandTransit;
	
	@Column (name="port_ICD_clrnc_days")
	private double PortICDclearanceTime;
	
	@Column (name="material_port_ICD_units")
	private double MaterialAtPortIcdUnits;
	
	@Column (name="high_seas_transit_days")
	private double HighSeasTransitDays;

	@Column (name="material_high_seas_units")
	private double MaterialHighSeasUnits;
	
	@Column (name="leadtime_days")
	private double LeadTimeDays;
	
	@Column (name="unexecuted_orders_units")
	private double unexecutedOrdersUnits;
	
	@Column (name="total_purchase_odrs_units")
	private double TotalPurchaseOrdersUnits;
}
