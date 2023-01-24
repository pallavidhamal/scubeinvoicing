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
@Table (name = "rm_import_sufficiency")
public class RmImportSuffEntity {
	
	
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY) private Long id;
	  
	@Column (name = "mat_code")
	private String materialCode;
	
	@Column (name="material_description")
	private String matDesc;
	
	@Column (name="plant_mnstknorm1")
	private double plantMnstknorm1;
	
	@Column (name="plant_mxstknorm1")
	private double plantMxstknorm1;
	
	@Column (name = "plant_mnstknorm2")
	private double plantMnstknorm2;
	
	@Column (name= "plant_mxstknorm2")
	private double plantMxstknorm2;
	
	@Column (name= "plant_curinv_transitpipeorders")
	private double plantCurinvTransitpipeorders;
	
	@Column (name= "plant_mnstknorm3")
	private double plantMnstknorm3;
	
	@Column (name=" plant_mxstknorm3")
	private double plantMxstknorm3;
	
	@Column (name="plant_cum_curinv_transitpipeorders")
	private double plantCumCurinvTtransitpipeorders;
	
	@Column (name=" inland_mnstknorm1")
	private double inlandMnstknorm1;
	
	@Column( name="inland_mnstknorm2")
	private double inlandMnstknorm2;
	
	@Column(name=" inland_curinv_transitpipeorders")
	private double inlandCurinvTransitpipeorders;
	
	@Column (name="inland_mnstknorm3")
	private double inlandMnstknorm3;
	
	@Column (name="inland_mxstknorm3")
	private double inlandMxstknorm3;
	
	@Column (name="plantit_cum_curinv_transitpipeorders")
	private double plantitCumCurinvTransitpipeorders;
	
	@Column (name="icd_mnstknorm1")
	private double icdMnstknorm1;
	
	@Column (name="icd_mnstknorm2")
	private double icdMnstknorm2;
	
	@Column (name="icd_curinv_transitpipeorders")
	private double icdCurinvTransitpipeorders;
	
	@Column (name="icd_mnstknorm3")
	private double icdMnstknorm3;
	
	@Column (name="icd_mxstknorm3")
	private double icdMxstknorm3;
	
	@Column (name="plantiticd_cum_curinv_transitpipeorders")
	private double plantiticdCumCurinvTransitpipeorders;
	
	@Column(name="hs_mnstknorm1")
	private double hsMnstknorm1;
	
	@Column (name="hs_mnstknorm2")
	private double hs_mnstknorm2;
	
	@Column (name="hs_curinv_transitpipeorders")
	private double hsCurinvTransitpipeorders;
	
	@Column(name="hs_mnstknorm3")
	private double hsMnstknorm3;
	
	@Column(name="hs_mxstknorm3")
	private double hs_mxstknorm3;
	
	@Column(name="plantiticdhs_cum_curinv_transitpipeorders")
	private double plantiticdhsCumCurinvTransitpipeorders;
	
	@Column(name="opcd_mnstknorm1")
	private double opcdMnstknorm1;
	
	@Column(name="opcd_mnstknorm2")
	private double opcdMnstknorm2;
	
	@Column(name="opcd_curinv_transitpipeorders")
	private double opcdCurinvTransitpipeorders;
	
	@Column(name="opcd_mnstknorm3")
	private double opcdMnstknorm3;
	
	@Column(name="opcd_mxstknorm3")
	private double opcdMxstknorm3;
	
	@Column(name = "plantiticdhsopcd_cum_curinv_transitpipeorders")
	private double plantiticdhsopcdCumCurinvTransitpipeorders;
	
	@Column(name="plant_suff")
	private String plantSuff;
	
	@Column(name="inland_suff")
	private String inlandSuff;
	
	@Column(name="icd_suff")
	private String icdSuff;
	
	@Column(name="hs_suff")
	private String hsSuff;
	
	@Column(name="opcd_suff")
	private String opcdSuff;
	
	@Column(name="report_date")
	private String reportDate;
	
	

}
