package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CreditNoteDetailsIncomingDto {
	
	private String itemsAndDescription;
	private double quantity;
	private double rate;
	private double amount;
	private String tax;

}
