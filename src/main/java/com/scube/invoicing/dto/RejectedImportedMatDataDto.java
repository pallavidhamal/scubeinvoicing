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
public class RejectedImportedMatDataDto {
	
	private String materialCode;
	
	private String materialDescription;
	
	private String reportDate;
	
	private String DomesticOrImportedBasedOnLocationOfSupplier;
	
	private String InlandTransit;
	
	private String MaterialInlandTransit;
	
	private String PortICDclearanceTime;
	
	private String MaterialAtPortIcdUnits;
	
	private String HighSeasTransitDays;
	
	private String MaterialHighSeasUnits;
	
	private String LeadTimeDays;
	
	private String unexecutedOrdersUnits;
	
	private String TotalPurchaseOrdersUnits;
	
	private String rowNo;

}
