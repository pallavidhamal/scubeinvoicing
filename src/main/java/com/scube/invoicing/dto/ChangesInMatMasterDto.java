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
public class ChangesInMatMasterDto {

	 private Long id;

	 private String materialCodeOld;
	 
	 private String materialCodeNew;
	 
	 private String regInterOld;
	 
	 private String regInterNew;
	 
	 private String keptInstockOld;
	 
	 private String keptInstockNew;
	 
	 private String readilyAvailableOld;
	 
	 private String readilyAvailableNew;
	 
	 private String avgconsPerdayunitsOld;
	 
	 private String avgconsPerdayunitsNew;
	 
	 private String leadTimeOld;
	 
	 private String leadTimeNew;
	 
	 private String transTimeOld;
	 
	 private String transTimeNew;
	 
	 private String supplierMoqOld;
	 
	 private String supplierMoqNew;
	 
	 private String EoqOld;
	 
	 private String EoqNew;
	 
	 private String safetyFactorOld;
	 
	 private String safetyFactorNew;
	 
	 private String uOmOld;
	 
	 private String uOmNew;
	 
	 private String materialDescriptionOld;
	 
	 private String materialDescriptionNew;
	 
	 private String fkUomOld;
	 
	 private String fkUomNew;
	 
	 private String  auditActions;
	
	 private String auditDateAndTime;
	 
	 private String ssNormUnitsOld;
	 
	 private String ssNormUnitsNew;
	 
	 private String fsDaysOld;
		
	 private String fsDaysNew;
}
