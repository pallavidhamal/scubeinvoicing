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
public class VendorMasterResponseDto {
	
	private String vendorID;
	
	// Vendor Name and Contact Details
	private String title;
	private String vendorFirstName;
	private String vendorLastName;
	
	private String vendorEmailID;
	private String vendorContactNo;
	
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
	private String paymentMethodID;
	private String prefPaymentMethod;
	private String prefDelieveryMethod;
	private String paymentTerms;
	private double openingBalance;
	private String paymentDate;
	private String paysWith;
	
	private String currencyID;
	private String currencyName;

}
