package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class ReportsIncomingDto {
	
	private String customerID;
	
	private String startDate;
	private String endDate;
	
	private String amountPendingFlag;
	
	private String invoiceID;
	
	private String vendorID;

}
