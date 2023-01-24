package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(	name = "audit_mat_master")
@Entity
@Getter @Setter
public class ChangesInMatMasterEntity {

	@Id
    private Long id;
	
	@Column(name="mat_code_old")
	private String materialCodeOld;
	
	@Column(name="mat_code_new")
	private String materialCodeNew;
	
	@Column(name = "reg_inter_old")
	private String regInterOld;
	
	@Column(name = "reg_inter_new")
	private String regInterNew;
	
	@Column(name = "kept_instock_old")
	private String keptInstockOld;
	
	@Column(name = "kept_instock_new")
	private String keptInstockNew;
	
	@Column(name="readily_avlble_old")
	private String readilyAvailableOld;
	
	@Column(name="readily_avlble_new")
	private String readilyAvailableNew;
	
	@Column(name="avgcons_perdayunits_old")
	private String avgconsPerdayunitsOld;
	
	@Column(name="avgcons_perdayunits_new")
	private String avgconsPerdayunitsNew;
	
	@Column(name="leadtime_old")
	private String leadTimeOld;
	
	@Column(name="leadtime_new")
	private String leadTimeNew;
	
	@Column(name="transtime_old")
	private String transTimeOld;
	
	@Column(name="transtime_new")
	private String transTimeNew;
	
	@Column(name="supplier_moq_old")
	private String supplierMoqOld;
	
	@Column(name="supplier_moq_new")
	private String supplierMoqNew;
	
	@Column(name="eoq_old")
	private String EoqOld;
	
	@Column(name="eoq_new")
	private String EoqNew;
	
	@Column(name="safety_factor_old")
	private String safetyFactorOld;
	
	@Column(name="safety_factor_new")
	private String safetyFactorNew;
	
	@Column(name="UOM_old")
	private String uOmOld;
	
	@Column(name="UOM_new")
	private String uOmNew;
	
	@Column(name="material_description_old")
	private String materialDescriptionOld;
	
	@Column(name="material_description_new")
	private String materialDescriptionNew;
	
	@Column(name="fk_uom_old")
	private String fkUomOld;
	
	@Column(name="fk_uom_new")
	private String fkUomNew;
	
	@Column(name="auditAction")
	private String auditActions;
	
	@Column(name="auditTimestamp")
	private String auditDateAndTime;
	
	@Column (name="ss_norm_units_old")
	private String ssNormUnitsOld;
	
	@Column (name="ss_norm_units_new")
	private String ssNormUnitsNew;
	
	@Column (name="FS_days_old")
	private String fsDaysOld;
	
	@Column(name="FS_days_new")
	private String fsDaysNew;
		
}
