package com.scube.invoicing.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseItemListResponseDto {
	
	private String expenseItemID;
	
	private String customer;
	
	private String categoryID;
	private String category;
	private String description;
	
	private String amount;
	
	private String gstID;
	private String gstTag;
	private double gstValue;
	
	private String serviceAmountWithGst;
	
	private CustomerMasterResponseDto customerMasterResponseDto;

}
