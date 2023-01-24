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
public class CurrentOrdersInPipelineResponseDto {
	
	private String id;
	
	private String po_type;
	private String po_no;
	private String po_date;
	
	private String line_no;
	
	private String item;
	
	private double current_order_pipeline_units;	
	private double rate;
	
	private String currency;
	
	private String report_date;

}
