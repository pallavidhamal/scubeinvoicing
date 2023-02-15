package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class CompanyMasterIncomingDto {
	
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
