package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "mst_company_details")
@Getter @Setter
public class CompanyMasterEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "contact_no")
	private String contactNo;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "company_website")
	private String companyWebsite;
	
	@Column(name = "company_gstin")
	private String companyGstin;
	
	@Column(name = "company_pan_no")
	private String companyPanNo;
	
	@Column(name = "company_cin_no")
	private String companyCinNo;
	
	@Column(name = "company_financial_accountant")
	private String companyFinancialAccountant;
	
	@Column(name = "company_bank_acc_no")
	private String companyBankAccNo;
	
	@Column(name = "beneficiary")
	private String beneficiary;
	
	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "swift_code")
	private String swiftCode;
	
	@Column(name = "bank_address")
	private String bankAddress;
	
	@Column(name = "cc")
	private String cc;
	
}
