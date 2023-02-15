package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerMasterIncomingDto {
	
	// Customer ID
	private String customerId;
	
	// Customer Info
	private String title;
	private String firstName;
	private String lastName;
	
	// Company Info
	private String companyName;
	private String emailId;
	private String mobileNumber;
	private String fax;
	private String website;
	
	// Billing Info
	private String billingAddress;
	private String billingCity;
	private String billingState;
	private String billingCountry;
	private String billingPinCode;
	
	// Shipping Info
	private String shippingAddress;
	private String shippingCity;
	private String shippingState;
	private String shippingCountry;
	private String shippingPinCode;
	
	// Tax Info
	private String gstRegistrationNo;
	private String gstin;
	private String taxRegistrationNo;
	private String cstRegistrationNo;
	private String panNo;
	
	
	// Payment & Billing Info
	private String prefPaymentMethod;
	private String prefDelieveryMethod;
	private String paymentTerms;
	private double openingBalance;
	private String paymentDate;
	private String paysWith;

}
