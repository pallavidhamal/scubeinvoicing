package com.scube.invoicing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_vendor")
@Getter @Setter
public class VendorMasterEntity extends BaseEntity {
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "vendor_first_name")
	private String vendorFirstName;
	
	@Column(name = "vendor_last_name")
	private String vendorLastName;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "billing_address")
	private String billingAddress;
	
	@Column(name = "billing_city")
	private String billingCity;
	
	@Column(name = "billing_state")
	private String billingState;
	
	@Column(name = "billing_country")
	private String billingCountry;
	
	@Column(name = "billing_pin_code")
	private String billingPinCode;
	
	@Column(name = "shipping_address")
	private String shippingAddress;
	
	@Column(name = "shipping_city")
	private String shippingCity;
	
	@Column(name = "shipping_state")
	private String shippingState;
	
	@Column(name = "shipping_country")
	private String shippingCountry;
	
	@Column(name = "shipping_pin_code")
	private String shippingPinCode;
	
	@Column(name = "gst_registration_no")
	private String gstRegistrationNo;
	
	@Column(name = "gstin")
	private String gstin;
	
	@Column(name = "tax_registration_no")
	private String taxRegistrationNo;
	
	@Column(name = "cst_registration_no")
	private String cstRegistrationNo;
	
	@Column(name = "pan_no")
	private String panNo;
	
	@OneToOne
	@JoinColumn(name = "fk_payment_method")
	private PaymentMethodEntity paymentMethodEntity;
	
	@Column(name = "pref_delievery_method")
	private String prefDelieveryMethod;
	
	@Column(name = "payment_terms")
	private String paymentTerms;
	
	@Column(name = "opening_balance")
	private double openingBalance;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "pays_with")
	private String paysWith;
	
	@OneToOne
	@JoinColumn(name = "fk_currency")
	private CurrencyMasterEntity currencyMasterEntity;

}
