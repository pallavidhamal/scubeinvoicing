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
public class GSTReportResponseDto {
	
	// Customer Info Details
	private String customerName;
	private String customerContactNo;
	private String customerEmailID;
	
	// Invoice Details
	private String invoiceNo;
	private String invoiceDate;
	private String invoiceAmount;
	private String invoicePaymentDate;
	
	// Invoice and Credit Note TDS Info
	private String invoiceTds;
	private String actualTds;
	private String declaredTds;
	
	// Credit Note Details
	private String creditNoteNo;
	private String creditNoteDate;
	private String creditsRemaining;
	
	private String cgstAmount;
	private String sgstAmount;
	private String igstAmount;
	private String gst4Amount;
	
	// Total CGST/ SGST/ IGST/ Total GST Fields -- Invoice
	private String invoiceTotalCgstAmount;
	private String invoiceTotalSgstAmount;
	private String invoiceTotalIgstAmount;
	private String invoiceTotalGstAmount;
	
	// Total CGST/ SGST/ IGST/ Total GST Fields  -- Credit Note
	private String creditNoteTotalGstAmount;
	private String creditNoteTotalCgstAmount;
	private String creditNoteTotalSgstAmount;
	private String creditNoteTotalIgstAmount;
	
}
