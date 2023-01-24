package com.scube.invoicing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DumpDataDto {
	
	
	private String materialCode;
	
	private String materialDescription;
	
	private String uOm;
	
	private String unitPrice;
	
	private String regInter;
	
	private String keptInstock;
	
	private String readilyAvlble;
	
	private String avgconsPerdayunits;
	
	private String leadTime;
	
	private String transTime;
	
	private String supplierMoq;
	
	private String eOq;
	
	private String FsDays;
	
	private String safetyFactor;
	
	private String ssNormUnits;
	
	private String ssNormUnitsLacs;
	
	private String maxInvnormUnits;
	
	private String maxInvnormLacs;
	
	private String avgInvnormUnits;
	
	private String ordersPipelinenormUnits;
	
	private String ordersPipelinenormLacs;
	
	private String maxInvnormPlantPipelineUnits;
	
	private String curInvUnits;
	
	private String curInvLacs;
	
	private String curOrdersPipeUnits;
	
	private String curOrdersPipeLacs;
	
	private String maxinvnormCurinvCurpiporders;
	
	private String poRelcanUnits;
	
	private String poRelcanLacs;
	
	private String avgConsLacs;
	
	private String curInvDays;
	
	private String maxinvnormCurinvLacs;
	
	private String lastRcptDate;
	
	private String lastRcptQty;
	
	private String recqtyByMaxMoeq;
	
	private String lastIssueDate;
	
	private String lastIssueQty;
	
	private String issuedQty;
	
	private String daysAfterIssue;
	
	private String reportDate;
	
	private String color;
	
	

}
