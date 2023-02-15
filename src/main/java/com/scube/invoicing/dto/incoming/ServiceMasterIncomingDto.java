package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceMasterIncomingDto {

	private String serviceID;
	
	private String serviceName;
	private String serviceStatus;
	
}
