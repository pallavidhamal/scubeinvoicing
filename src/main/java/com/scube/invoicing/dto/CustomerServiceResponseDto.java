package com.scube.invoicing.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.scube.invoicing.dto.incoming.CustomerInvoiceServiceIncomingDto;

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
public class CustomerServiceResponseDto {
	
	private String companyName;
	private String toEmailID;
	private String fromEmailID;
	private String subject;
	private String bccEmailID;
	private String mailBody;
	
	private String customerID;
	private String custEmailId;
	private String customerBillingAddress;
	private String terms;
	private String shippingTo;
	private String shippingVia;
	private String shippingDate;
	
	private String customerCompanyName;
	
	private String invoiceNo;
	private String invoiceDate;
	
	private double subTotal;
	private double totalAmount;
	private double discounts;
	private double deposit;
	private double balance;
	
	private double cgstAmount;
	private double sgstAmount;
	
	private String messageInvoice;
	private String messageStatement;
	
	private String dueDate;
	private String trackingNo;
	
	private String paymentStatus;
	
	private Set<CustomerInvoiceServiceIncomingDto> customerInvoiceServiceDtos;

}
