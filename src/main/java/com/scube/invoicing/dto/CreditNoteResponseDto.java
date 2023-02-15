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
	
	private double subTotal;
	private double totalAmount;
	private double creditsRemaining;

}
