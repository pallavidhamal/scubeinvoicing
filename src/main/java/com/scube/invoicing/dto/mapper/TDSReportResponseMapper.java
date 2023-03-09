package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.util.DateUtils;

public class TDSReportResponseMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	// Response Mapper for Invoice TDS Report
	public static GSTReportResponseDto toTDSReportForInvoiceResponseDto(CustomerInvoiceEntity customerInvoiceEntity) {
		
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
				.setCgstAmount(new String(decoder.decode(customerInvoiceEntity.getCgstAmount())))
				.setSgstAmount(new String(decoder.decode(customerInvoiceEntity.getCgstAmount())))
				.setIgstAmount(new String(decoder.decode(customerInvoiceEntity.getIgstAmount())))
				
				.setInvoiceTds(new String(decoder.decode(customerInvoiceEntity.getInvoiceTds())))
				.setActualTds(new String(decoder.decode(customerInvoiceEntity.getActualTds())));
	}
	
	public static List<GSTReportResponseDto> toTDSReportForInvoiceResponseDtosList(List<CustomerInvoiceEntity> customerInvoiceEntitiesList) {
		// TODO Auto-generated method stub
		
		List<GSTReportResponseDto> gstReportResponseDtosList = new ArrayList<GSTReportResponseDto>();
		for(CustomerInvoiceEntity customerInvoiceEntity : customerInvoiceEntitiesList) {
			gstReportResponseDtosList.add(toTDSReportForInvoiceResponseDto(customerInvoiceEntity));	
		}
		
		return gstReportResponseDtosList;
	}
	
	// Response Mapper for Credit Note TDS Report
	public static GSTReportResponseDto toTDSReportForCreditNoteResponseDto(CustomerCreditNoteEntity customerCreditNoteEntity) {
		
		return new GSTReportResponseDto()
				// Credit Note No/Credit Note Date/Credits Remaining
				.setCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCreditNoteDate(DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate()))
				.setCreditsRemaining(new String(decoder.decode(customerCreditNoteEntity.getCreditsRemaining())))
				
				// Customer Info
				.setCustomerName(customerCreditNoteEntity.getCustomerMasterEntity().getCompanyName())
				.setCustomerContactNo(customerCreditNoteEntity.getCustomerMasterEntity().getMobileNumber())
				.setCustomerEmailID(customerCreditNoteEntity.getCustomerMasterEntity().getEmailId())
				
				// CGST/SGST/ IGST Amount values
				.setCgstAmount(new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())))
				.setSgstAmount(new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())))
				.setIgstAmount(new String(decoder.decode(customerCreditNoteEntity.getIgstAmount())))
				
				.setDeclaredTds(new String(decoder.decode(customerCreditNoteEntity.getDeclaredTds())))
				.setActualTds(new String(decoder.decode(customerCreditNoteEntity.getActualTds())));
	}
	
	public static List<GSTReportResponseDto> toTDSReportForCreditNoteResponseDtosList(List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList) {
		// TODO Auto-generated method stub
		
		List<GSTReportResponseDto> gstReportResponseDtosList = new ArrayList<GSTReportResponseDto>();
		for(CustomerCreditNoteEntity customerCreditNoteEntity : customerCreditNoteEntitiesList) {
			gstReportResponseDtosList.add(toTDSReportForCreditNoteResponseDto(customerCreditNoteEntity));	
		}
		
		return gstReportResponseDtosList;
	}

}
