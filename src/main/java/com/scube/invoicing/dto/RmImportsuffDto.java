package com.scube.invoicing.dto;

import org.springframework.stereotype.Service;

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

@Service
public class RmImportsuffDto {
	
	private String mat_code;
	private String material_description;
	
	
	
	//
	//String to string
	
	private String plant_mnstknorm1;
	private String plant_mxstknorm1;
	private String plant_mnstknorm2;
	private String plant_mxstknorm2;
	private String plant_curinv_transitpipeorders;
	private String plant_mnstknorm3;
	private String plant_mxstknorm3;
	private String plant_cum_curinv_transitpipeorders;
	private String inland_mnstknorm1;
	private String inland_mnstknorm2;
	private String inland_curinv_transitpipeorders;
	private String inland_mnstknorm3;
	private String inland_mxstknorm3;
	private String plantit_cum_curinv_transitpipeorders;
	private String icd_mnstknorm1;
	private String icd_mnstknorm2;
	private String icd_curinv_transitpipeorders;
	private String icd_mnstknorm3;
	private String icd_mxstknorm3;
	private String plantiticd_cum_curinv_transitpipeorders;
	private String hs_mnstknorm1;
	private String hs_mnstknorm2;
	private String hs_curinv_transitpipeorders;
	private String hs_mnstknorm3;
	private String hs_mxstknorm3;
	private String plantiticdhs_cum_curinv_transitpipeorders;
	private String opcd_mnstknorm1;
	private String opcd_mnstknorm2;
	private String opcd_curinv_transitpipeorders;
	private String opcd_mnstknorm3;
	private String opcd_mxstknorm3;
	private String plantiticdhsopcd_cum_curinv_transitpipeorders;
	
	
	
	///
	private String plant_suff;
	private String inland_suff;
	private String icd_suff;
	private String hs_suff;
	private String opcd_suff;
	private String report_date;

}
