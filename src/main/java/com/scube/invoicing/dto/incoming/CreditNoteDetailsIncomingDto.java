package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CreditNoteDetailsIncomingDto {
	
	private String service; 
	private String description;
	
	private double quantity;
	private double rate;
	private String amount;
	private String tax;
	
	private String serviceAmountWithGst;
	private String gstAmount;

}
