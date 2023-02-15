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
	
	private String companyName;
	private String toEmailID;
	private String fromEmailID;
	private String subject;
	private String bccEmailID;
	private String mailBody;
	
	private String creditNoteNo;
	private String creditNoteDate;
	
	private double subTotal;
	private double cgstAmount;
	private double sgstAmount;
	private double totalAmount;
	private double creditsRemaining;
	
	private Set<CreditNoteDetailsIncomingDto> creditNoteDetailsIncomingDto;

}
