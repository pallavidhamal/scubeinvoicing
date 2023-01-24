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
@Table (name = "material_current_inventory")
public class ExcelFileResultEntity  {
	
	
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column (name = "material_code")
	private String materialCode;
	
	@Column (name = "material_desc")
	private String materialDescription;
	
	@Column (name = "UOM")
	private String uOM;
	
	@Column (name = "unit_price")
	private double unitPrice;
	
	@Column (name = "cur_inv_units")
	private String currentInventoryInUnits;
	
	@Column (name = "report_date")
	private String reportDate;
	
	@Column (name = "item_purchase_category")
	private String itemPurchaseCategory;
	
}
