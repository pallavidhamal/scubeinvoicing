package com.scube.invoicing.dto.incoming;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class CreditNoteIncomingDto {
	
	private String customerID;
	private String custEmailId;
	
	private String creditNoteID;
	
	private String invoiceNo;
	
	private String companyName;
	private String toEmailID;
	private String fromEmailID;
	private String subject;
	private String bccEmailID;
	private String mailBody;
	
	private String creditNoteNo;
	private String creditNoteDate;
	
	private String subTotal;
	private String totalAmount;
	private String creditsRemaining;
	
	private String cgstAmount;
	private String sgstAmount;
	private String igstAmount;
	private String gst4Amount;
	
	private String declaredTds;
	private String actualTds;
	
	private Set<CreditNoteDetailsIncomingDto> creditNoteDetailsIncomingDto;
	
	private Set<CustomerInvoiceIncomingDto> customerInvoiceIncomingDtos;

}
