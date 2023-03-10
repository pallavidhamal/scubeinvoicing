package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ExpenseItemListIncomingDto {
	
	private String expenseItemID;
	
	private String customer;
	
	private String category;
	private String description;
	
	private String amount;
	private String tax;
	
	private String serviceAmountWithGst;
	
}
