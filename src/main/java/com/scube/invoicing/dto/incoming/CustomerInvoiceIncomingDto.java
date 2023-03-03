package com.scube.invoicing.dto.incoming;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerInvoiceIncomingDto {
	
	private String customerID;
	private String custEmailId;
	private String customerBillingAddress;
	private String terms;
	private String shippingTo;
	private String shippingVia;
	private String shippingDate;
	
	private String customerServiceID;
	
	private String invoiceID;
	private String invoiceNo;
	private String invoiceDate;
	
	private String subTotal;
	private String totalAmount;
	private String discounts;
	private String deposit;
	private String balance;
	
	private String cgstAmount;
	private String sgstAmount;
	private String igstAmount;
	
	private String invoiceTds;
	private String actualTds;
	
	private String messageInvoice;
	private String messageStatement;
	
	private String dueDate;
	
	private String trackingNo;
	
	private Set<CustomerInvoiceServiceIncomingDto> customerInvoiceServiceDtos;
	
}
