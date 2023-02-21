package com.scube.invoicing.dto.incoming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerInvoiceServiceIncomingDto {
	
	private String productService;
	private String hsnOrSac;
	private String sku;
	private String description;
	private String quantity;
	private double rate;
	private double amount;
	private String gstID;

}
