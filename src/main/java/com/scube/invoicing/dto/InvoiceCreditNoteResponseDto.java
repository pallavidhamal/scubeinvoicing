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
public class InvoiceCreditNoteResponseDto {
	
	private String customerInvoiceID;

	private String customerID;
	private String customerCompanyName;
	
	private String customerInvoiceNo;
	private String customerInvoiceDate;
	
	private String customerInvoiceAmount;
	
	private String date;
	
	private String customerCreditNoteNo;
	private String creditNoteDate;
	
	private String customerCreditsRemaining;
	
	private String amountPendingFlag;
	
}
