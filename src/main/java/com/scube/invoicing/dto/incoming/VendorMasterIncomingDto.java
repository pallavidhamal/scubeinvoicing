package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VendorMasterIncomingDto {
	
	private String vendorID;

	private String vendorName;
	private String vendorEmailID;
	private String vendorContactNo;
	
}
