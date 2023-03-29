package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateInvoiceIncomingDto {
	
	private String customerID;
	private String custEmailId;
	
	private String companyName;
	private String toEmailID;
	private String fromEmailID;
	private String subject;
	private String bccEmailID;
	private String mailBody;
	private String cc;
	
	private String invoiceNo;
	private String invoiceDate;
	private String dueDate;
	
	private String creditNoteNo;
	private double creditsRemaining;

}
