package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GSTMasterIncomingDto {
	
	// GST Record ID
	private String gstId;
	
	// GST Record Value
	private double percentValue;
	private String description;

}
