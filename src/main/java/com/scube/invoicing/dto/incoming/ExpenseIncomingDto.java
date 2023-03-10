package com.scube.invoicing.dto.incoming;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ExpenseIncomingDto {
	
	private String expenseID;

	private String vendorName;
	private String paymentDate;
	private String paymentMethod;
	private String referenceNo;
	
	private String roundOffAmount;
	private String subTotal;
	private String totalAmount;
	
	private String memo;
	
	private String paymentAccount;
	
	private Set<ExpenseItemListIncomingDto> expenseItemListIncomingDtos;
	
}
