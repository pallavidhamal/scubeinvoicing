package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportIncomingDto {

	private String fromdt;
	private String todt;
	
	private String materialCode;

}