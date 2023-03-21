package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.util.DateUtils;

public class GSTReportResponseMapper {

	static Base64.Decoder decoder = Base64.getDecoder();
	
	// Response Mapper for Invoice GST Report
	public static GSTReportResponseDto toGSTReportForInvoiceResponseDto(CustomerInvoiceEntity customerInvoiceEntity) {
		
		return new GSTReportResponseDto()
				// Invoice No/Invoice Date/Invoice Amount/Invoice Payment Date
				.setInvoiceNo(customerInvoiceEntity.getInvoiceNo())
				.setInvoiceDate(DateUtils.formatDateToDDMMYYYYFormat(customerInvoiceEntity.getInvoiceDate()))
				.setInvoiceAmount(new String(decoder.decode(customerInvoiceEntity.getTotalAmount())))
				.setInvoicePaymentDate(DateUtils.formatDateToDDMMYYYYFormat(customerInvoiceEntity.getPaymentCompletedDate()))
				
				// Customer Info
				.setCustomerName(customerInvoiceEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerContactNo(customerInvoiceEntity.getCustomerMasterEntity().getMobileNumber())
				.setCustomerEmailID(customerInvoiceEntity.getCustomerMasterEntity().getEmailId())
				
				// CGST/SGST/ IGST Amount values
				.setCgstAmount(customerInvoiceEntity.getCgstAmount() != null ? 
						new String(decoder.decode(customerInvoiceEntity.getCgstAmount())) : "")
				.setSgstAmount(customerInvoiceEntity.getSgstAmount() != null ? 
						new String(decoder.decode(customerInvoiceEntity.getSgstAmount())) : "")
				.setIgstAmount(customerInvoiceEntity.getIgstAmount() != null ? 
						new String(decoder.decode(customerInvoiceEntity.getIgstAmount())) : "")
				.setGst4Amount(customerInvoiceEntity.getGst4Amount() != null ? 
						new String(decoder.decode(customerInvoiceEntity.getGst4Amount())) : "");
	}
	
	public static List<GSTReportResponseDto> toGSTReportForInvoiceResponseDtosList(List<CustomerInvoiceEntity> customerInvoiceEntitiesList) {
		// TODO Auto-generated method stub
		
		List<GSTReportResponseDto> gstReportResponseDtosList = new ArrayList<GSTReportResponseDto>();
		for(CustomerInvoiceEntity customerInvoiceEntity : customerInvoiceEntitiesList) {
			gstReportResponseDtosList.add(toGSTReportForInvoiceResponseDto(customerInvoiceEntity));	
		}
		
		return gstReportResponseDtosList;
	}

	// Response Mapper for Credit Note GST Report
	public static GSTReportResponseDto toGSTReportForCreditNoteResponseDto(CustomerCreditNoteEntity customerCreditNoteEntity) {
		
		return new GSTReportResponseDto()
				// Credit Note No/ Credit Note Date
				.setCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCreditNoteDate(DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate()))
				
				// Credits Remaining
				.setCreditsRemaining(new String(decoder.decode(customerCreditNoteEntity.getCreditsRemaining())))
				
				// Customer Info
				.setCustomerName(customerCreditNoteEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerContactNo(customerCreditNoteEntity.getCustomerMasterEntity().getMobileNumber())
				.setCustomerEmailID(customerCreditNoteEntity.getCustomerMasterEntity().getEmailId())
				
				// CGST/ IGST/ SGST Amount
				.setCgstAmount(customerCreditNoteEntity.getCgstAmount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())) : null)
				.setSgstAmount(customerCreditNoteEntity.getSgstAmount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getSgstAmount())) : null)
				.setIgstAmount(customerCreditNoteEntity.getIgstAmount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getIgstAmount())) : null)
				.setGst4Amount(customerCreditNoteEntity.getGst4Amount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getGst4Amount())) : null);
	}
	
	public static List<GSTReportResponseDto> toGSTReportForCreditNoteResponseDtosList(List<CustomerCreditNoteEntity> customerCreditNoteEntityList) {
		// TODO Auto-generated method stub
		
		List<GSTReportResponseDto> gstReportResponseDtosList = new ArrayList<GSTReportResponseDto>();
		for(CustomerCreditNoteEntity customerCreditNoteEntity : customerCreditNoteEntityList) {
			gstReportResponseDtosList.add(toGSTReportForCreditNoteResponseDto(customerCreditNoteEntity));	
		}
		
		return gstReportResponseDtosList;
	}
}
