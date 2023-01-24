package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "mat_master")
public class MaterialInfoMasterEntity {
	
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "mat_code")
	private String materialCode;
	
	@Column (name = "reg_inter")
	private String regularIntermediate;
	
	@Column (name = "material_description")
	private String materialDescription;

	@OneToOne
	@JoinColumn (name = "fk_uom")
	private UnitOfMeasurementEntity unitOfMeasurementEntity;
	
	@Column (name = "kept_instock")
	private String keptInStock;
	
	@Column (name = "readily_avlble")
	private String readilyAvailable;
	
	@Column (name = "avgcons_perdayunits")
	private double averagePerDayUnits;
	
	@Column (name = "leadtime")
	private int leadTime;
	
	@Column (name = "transtime")
	private int transTime;
	
	@Column (name = "supplier_moq")
	private double supplierMOQ;
	
	@Column (name = "eoq")
	private double eoq;
	
	@Column (name = "safety_factor")
	private double safetyFactory;
	
	@Column (name="ss_norm_units")
	private double ssNormUnits;
	
	@Column(name="FS_days")
	private String fsDays;
	
	@Column(name="item_category")
	private String itemCategory;
	
	
}
