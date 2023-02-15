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
public class CompanyMasterResponseDto {
	
	private String companyID;
	
	private String name;
	private String emailId;
	private String contactNo;
	
	private String companyName;
	private String companyAddress;
	private String companyWebsite;
	
	private String companyGstin;
	private String companyPanNo;
	private String companyCinNo;
	
	private String companyAccountantEmailId;
	
	private String beneficiary;
	private String bankName;
	private String bankAccountNo;
	private String swiftCode;
	private String bankAddress;
	
}
