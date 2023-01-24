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
public class RejectedCurrentOrderPipelineDetailsExcelRecordDto {
	
	private String poType;
	private String poNo;
	private String poDate;
	
	private String lineNo;
	
	private String item;
	
	private double currentOrdersPipelineUnits;
	
	private double rate;
	private String currency;
	
	private String reportDate;
	
	private String rowNo;
	
}
