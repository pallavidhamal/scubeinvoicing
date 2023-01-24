package com.scube.invoicing.dto;

import java.io.File;

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
public class ImportedmatDataDto {
	
	private String material_code;
	
	private String mat_desc;
	
	private String report_date;
	
	private String domestic_imported;
	
	private String inland_transit_days;
	
	private String material_in_inland_transit_units;
	
	private String port_ICD_clrnc_days;
	
	private String material_port_ICD_units;
	
	private String high_seas_transit_days;
	
	private String material_high_seas_units;
	
	private String leadtime_days;
	
	private String unexecuted_orders_units;
	
	private String total_purchase_odrs_units;
	
}
