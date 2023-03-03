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
public class CreditNoteResponseDto {
	
	private String customerID;
	private String customerName;
	
	private String customerCompanyName;
	
	private String customerCreditNoteNo;
	private String customerCreditNoteDate;
	
	private String subTotal;
	private String totalAmount;
	private String creditsRemaining;
	
	private String cgstAmount;
	private String sgstAmount;
	private String igstAmount;
	
	private String declaredTds;
	private String actualTds;

}
