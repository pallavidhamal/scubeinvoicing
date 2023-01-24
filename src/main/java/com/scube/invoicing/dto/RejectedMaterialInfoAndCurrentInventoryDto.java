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
public class RejectedMaterialInfoAndCurrentInventoryDto {

	private String materialCode;
	
	private String materialDescription;
	private String uOM;
	
	private String unitPrice;
	private String currentInventoryInUnits;
	
	private String reportDate;
	
	private String itemPurchaseCategory;
	
	private String row;
	
}
