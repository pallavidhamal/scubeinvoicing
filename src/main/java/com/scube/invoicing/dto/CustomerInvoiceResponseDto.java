package com.scube.invoicing.dto;

import java.util.List;

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
public class CustomerInvoiceResponseDto {
	
	private String companyName;
	private String toEmailID;
	private String fromEmailID;
	private String subject;
	private String bccEmailID;
	private String mailBody;
	private String cc;
	
	private String customerID;
	private String custEmailId;
	private String customerBillingAddress;
	private String terms;
	private String shippingTo;
	private String shippingVia;
	private String shippingDate;
	
	private String customerCompanyName;
	
	private String invoiceID;
	private String invoiceNo;
	private String invoiceDate;
	
	private String subTotal;
	private String totalAmount;
	private String discounts;
	private String deposit;
	private String balance;
	
	private String actualTds;
	private String invoiceTds;
	
	private String cgstAmount;
	private String sgstAmount;
	private String igstAmount;
	private String gst4Amount;
	
	private String messageInvoice;
	private String messageStatement;
	
	private String dueDate;
	private String trackingNo;
	
	private String paymentStatus;
	
	private List<CustomerInvoiceServiceResponseDto> customerInvoiceServiceResponseDtos;

}
