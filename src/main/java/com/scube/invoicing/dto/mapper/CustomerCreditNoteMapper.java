package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.CreditNoteResponseDto;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.util.DateUtils;

public class CustomerCreditNoteMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	public static CreditNoteResponseDto toCustomerCreditNoteAndCompanyResponseMailDto(
			CustomerCreditNoteEntity customerCreditNoteEntity, CompanyMasterEntity companyMasterEntity) {
		
		String mailBody = "Please find the Invoice. If you have any clarification kindly contact." 
				+ "Thanks for your Business!,"
				+ companyMasterEntity.getCompanyName();
		
//		String subTotal = 
		
		return new CreditNoteResponseDto()
				// Company Info and Mail Info
				.setCompanyName(companyMasterEntity.getCompanyName())
				.setToEmailID(customerCreditNoteEntity.getCustomerMasterEntity().getEmailId() != null 
					? customerCreditNoteEntity.getCustomerMasterEntity().getEmailId() : 
						customerCreditNoteEntity.getCustomerMasterEntity().getEmailId())
				.setFromEmailID(companyMasterEntity.getEmailId())
				.setBccEmailID(companyMasterEntity.getCompanyFinancialAccountant())
				.setSubject(customerCreditNoteEntity.getCreditNoteNo() + " from " + 
						companyMasterEntity.getCompanyName())
				.setMailBody(mailBody)
				
				// Customer Info
				.setCustomerID(customerCreditNoteEntity.getCustomerMasterEntity().getId())
				.setCustomerEmailID(customerCreditNoteEntity.getCustomerMasterEntity().getEmailId())
				
				// Invoice No and Invoice Date
				.setCreditNoteID(customerCreditNoteEntity.getId())
				.setCustomerCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCustomerCreditNoteDate(DateUtils.formattedDate(customerCreditNoteEntity.getCreditNoteDate()))
				
				// Sub-total/ Total/ Balance/ Deposit/ Discounts
				.setSubTotal(new String(decoder.decode(customerCreditNoteEntity.getSubTotal())))
				.setTotalAmount(new String(decoder.decode(customerCreditNoteEntity.getTotalAmount())))
				
				// CGST/ SGST/ IGST
				.setCgstAmount(customerCreditNoteEntity.getCgstAmount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())) : null)
				.setSgstAmount(customerCreditNoteEntity.getSgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getSgstAmount())) : null)
				.setIgstAmount(customerCreditNoteEntity.getIgstAmount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getIgstAmount())) : null)
				.setGst4Amount(customerCreditNoteEntity.getGst4Amount() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getGst4Amount())) : null)
				
				// Decalred and Actual Tds
				.setDeclaredTds(customerCreditNoteEntity.getDeclaredTds() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getDeclaredTds())) : null)
				.setActualTds(customerCreditNoteEntity.getActualTds() != null ?
						new String(decoder.decode(customerCreditNoteEntity.getActualTds())) : null)
				
				.setCreditsRemaining(customerCreditNoteEntity.getCreditsRemaining());
		
	}

	public static CreditNoteResponseDto toCreditNoteResponseDto(CustomerCreditNoteEntity customerCreditNoteEntity) {
		return new CreditNoteResponseDto()
				// Customer Info Details
				.setCustomerID(customerCreditNoteEntity.getCustomerMasterEntity().getId())
				.setCustomerName(customerCreditNoteEntity.getCustomerMasterEntity().getTitle() + " " + 
						customerCreditNoteEntity.getCustomerMasterEntity().getFirstName() + 
						" " + customerCreditNoteEntity.getCustomerMasterEntity().getLastName())
				.setCustomerCompanyName(customerCreditNoteEntity.getCustomerMasterEntity().getCompanyName())
				
				// Credit Note ID
				.setCreditNoteID(customerCreditNoteEntity.getId())
				
				// Credit Note No and Credit Note Date
				.setCustomerCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCustomerCreditNoteDate(DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate()))
				
				// Sub-total/ Credits Remaining/ Total Amount
				.setSubTotal(new String(decoder.decode(customerCreditNoteEntity.getSubTotal())))
				.setCreditsRemaining(new String(decoder.decode(customerCreditNoteEntity.getCreditsRemaining())))
				.setTotalAmount(new String(decoder.decode(customerCreditNoteEntity.getTotalAmount())))
				
				// CGST/ SGST/ IGST/ GST_4
				.setCgstAmount(customerCreditNoteEntity.getCgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())) : null)
				.setSgstAmount(customerCreditNoteEntity.getSgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getSgstAmount())) : null)
				.setIgstAmount(customerCreditNoteEntity.getIgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getIgstAmount())) : null)
				.setGst4Amount(customerCreditNoteEntity.getGst4Amount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getGst4Amount())) : null)
				
				// Declared TDS/ Actual TDS
				.setDeclaredTds(customerCreditNoteEntity.getDeclaredTds() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getDeclaredTds())) : null)
				.setActualTds(customerCreditNoteEntity.getActualTds() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getActualTds())) : null);
	}
	
	
	public static CreditNoteResponseDto toCreditNoteAndServiceResponseDto(CustomerCreditNoteEntity customerCreditNoteEntity,
			List<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntity) {
		return new CreditNoteResponseDto()
				//Credit Note ID
				.setCreditNoteID(customerCreditNoteEntity.getId())
				
				// Customer Info Details
				.setCustomerID(customerCreditNoteEntity.getCustomerMasterEntity().getId())
				.setCustomerName(customerCreditNoteEntity.getCustomerMasterEntity().getTitle() + " " + 
						customerCreditNoteEntity.getCustomerMasterEntity().getFirstName() + 
						" " + customerCreditNoteEntity.getCustomerMasterEntity().getLastName())
				.setCustomerCompanyName(customerCreditNoteEntity.getCustomerMasterEntity().getCompanyName())
				
				// Credit Note No and Credit Note Date
				.setCustomerCreditNoteNo(customerCreditNoteEntity.getCreditNoteNo())
				.setCustomerCreditNoteDate(DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate()))
				
				// Sub-total/ Credits Remaining/ Total Amount
				.setSubTotal(new String(decoder.decode(customerCreditNoteEntity.getSubTotal())))
				.setCreditsRemaining(new String(decoder.decode(customerCreditNoteEntity.getCreditsRemaining())))
				.setTotalAmount(new String(decoder.decode(customerCreditNoteEntity.getTotalAmount())))
				
				// CGST/ SGST/ IGST
				.setCgstAmount(customerCreditNoteEntity.getCgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getCgstAmount())) : null)
				.setSgstAmount(customerCreditNoteEntity.getSgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getSgstAmount())) : null)
				.setIgstAmount(customerCreditNoteEntity.getIgstAmount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getIgstAmount())) : null)
				.setGst4Amount(customerCreditNoteEntity.getGst4Amount() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getGst4Amount())) : null)
				
				// Declared TDS/ Actual TDS
				.setDeclaredTds(customerCreditNoteEntity.getDeclaredTds() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getDeclaredTds())) : null)
				.setActualTds(customerCreditNoteEntity.getActualTds() != null ? 
						new String(decoder.decode(customerCreditNoteEntity.getActualTds())) : null)
				
				// Credit Note Service
				.setCreditNoteServiceResponseDtos(CustomerCreditNoteServiceMapper.
						toCustomerCreditNoteServiceResponseDtosSet(customerCreditNoteDetailsEntity));
	}
	
	public static List<CreditNoteResponseDto> toCustomerCreditNoteResponseDtos(List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList) {
		// TODO Auto-generated method stub
		
		List<CreditNoteResponseDto> creditNoteResponseDtos = new ArrayList<CreditNoteResponseDto>();
		for(CustomerCreditNoteEntity customerCreditNoteEntity :customerCreditNoteEntitiesList) {
			creditNoteResponseDtos.add(toCreditNoteResponseDto(customerCreditNoteEntity));			
		}
				
		return creditNoteResponseDtos;
	} 
	
}
