package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(	name = "calculate_inv")
@Entity
@Getter @Setter
public class DumpDataEntity {
	
	@Id

	@Column (name="mat_code")
	private String materialCode;
	
	@Column(name="mat_Desc")
	private String materialDescription;
	
	@Column(name="uom")
	private String uOm;
	
	@Column(name="unit_price")
	private String unitPrice;
	
	@Column(name="reg_inter")
	private String regInter;
	
	@Column(name="kept_instock")
	private String keptInstock;
	
	@Column(name="readily_avlble")
	private String readilyAvlble;
	
	@Column(name="avgcons_perdayunits")
	private String avgconsPerdayunits;
	
	@Column(name="leadtime")
	private String leadTime;
	
	@Column(name="transtime")
	private String transTime;
	
	@Column(name="supplier_moq")
	private String supplierMoq;
	
	@Column(name="eoq")
	private String eOq;
	
	@Column(name="FS_days")
	private String FsDays;
	
	@Column(name="safety_factor")
	private String safetyFactor;
	
	@Column (name="ss_norm_units")
	private String ssNormUnits;
	
	@Column(name="ss_norm_units_lacs")
	private String ssNormUnitsLacs;
	
	@Column(name="max_invnorm_units")
	private String maxInvnormUnits;
	
	@Column(name="max_invnorm_lacs")
	private String maxInvnormLacs;
	
	@Column(name="avg_invnorm_units")
	private String avgInvnormUnits;
	
	@Column(name="orders_piplinenorm_units")
	private String ordersPipelinenormUnits;
	
	@Column(name="orders_piplinenorm_lacs")
	private String ordersPipelinenormLacs;
	
	@Column(name="max_invnorm_plantpipeline_units")
	private String maxInvnormPlantPipelineUnits;
	
	@Column(name="cur_inv_units")
	private String curInvUnits;
	
	@Column (name="cur_inv_lacs")
	private String curInvLacs;
	
	@Column(name="cur_orders_pipe_units")
	private String curOrdersPipeUnits;
	
	@Column(name="cur_orders_pipe_lacs")
	private String curOrdersPipeLacs;
	
	@Column(name="maxinvnorm_curinv_curpiporders")
	private String maxinvnormCurinvCurpiporders;
	
	@Column(name="po_relcan_units")
	private String poRelcanUnits;
	
	@Column(name="po_relcan_lacs")
	private String poRelcanLacs;
	
	@Column(name="avg_cons_lacs")
	private String avgConsLacs;
	
	@Column(name="cur_inv_days")
	private String curInvDays;
	
	@Column(name="maxinvnorm_curinvlacs")
	private String maxinvnormCurinvLacs;
	
	@Column(name="last_rcpt_date")
	private String lastRcptDate;
	
	@Column(name="last_rcpt_qty")
	private String lastRcptQty;
	
	@Column(name="recqty_by_max_moeq")
	private String recqtyByMaxMoeq;
	
	@Column(name="last_issue_date")
	private String lastIssueDate;
	
	@Column(name="last_issue_qty")
	private String lastIssueQty;
	
	@Column(name="issued_qty")
	private String issuedQty;
	
	@Column(name="days_after_issue")
	private String daysAfterIssue;
	
	@Column(name="report_date")
	private String reportDate;
	
	@Column(name="color")
	private String color;
	
}
