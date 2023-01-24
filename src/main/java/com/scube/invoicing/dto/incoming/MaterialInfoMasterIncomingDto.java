package com.scube.invoicing.dto.incoming;

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
public class MaterialInfoMasterIncomingDto {
	
	private long materialInfoId;
		
	private String materialCode;
	private String regularIntermediate;
	
	private String materialDescription;
	
	private String unitOfMeasurement;
	private String unitId;
	
	private String keptInStock;
	private String readilyAvailable;
	
	private double averagePerDayUnits;
	
	private String leadTime;
	private String transTime;
	
	private double supplierMOQ;
	private double eoq;
	
	private double safetyFactory;
	
	private double ssNormUnits;

	private String fsDays;
	
	private String itemCategory;

}
