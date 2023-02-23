package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CustomerServiceResponseDto;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.StringNullEmpty;

public class CustomerInvoiceMapper {

	public static CustomerServiceResponseDto toCustomerServiceAndCompanyResponseDto(
			CustomerInvoiceEntity customerInvoiceEntity, CompanyMasterEntity companyMasterEntity) {
		
		String mailBody = "Please find the Invoice. If you have any clarification kindly contact." 
				+ "Thanks for your Business!,"
				+ companyMasterEntity.getCompanyName();
		
		return new CustomerServiceResponseDto()
				.setCompanyName(companyMasterEntity.getCompanyName())
				.setToEmailID(customerInvoiceEntity.getCustEmailId() != null 
					? customerInvoiceEntity.getCustEmailId() : customerInvoiceEntity.getCustomerMasterEntity().getEmailId())
				.setFromEmailID(companyMasterEntity.getEmailId())
				.setBccEmailID(companyMasterEntity.getCompanyFinancialAccountant())
				.setSubject(customerInvoiceEntity.getInvoiceNo() + " from " + 
						companyMasterEntity.getCompanyName())
				.setMailBody(mailBody)
				
				.setCustomerID(customerInvoiceEntity.getCustomerMasterEntity().getId())
				.setCustEmailId(customerInvoiceEntity.getCustEmailId())
				.setCustomerBillingAddress(customerInvoiceEntity.getCustomerBillingAddress())
				.setShippingDate(DateUtils.formattedDate(customerInvoiceEntity.getShippingDate()))
				.setShippingTo(customerInvoiceEntity.getShippingTo())
				.setShippingVia(customerInvoiceEntity.getShippingVia())
				.setTerms(customerInvoiceEntity.getTerms())
				
				.setInvoiceNo(customerInvoiceEntity.getInvoiceNo())
				.setInvoiceDate(DateUtils.formattedDate(customerInvoiceEntity.getInvoiceDate()))
				
				.setSubTotal(customerInvoiceEntity.getSubTotal())
				.setCgstAmount(customerInvoiceEntity.getCgstAmount())
				.setSgstAmount(customerInvoiceEntity.getSgstAmount())
				.setBalance(customerInvoiceEntity.getBalance())
				.setDeposit(customerInvoiceEntity.getDeposit())
				.setDiscounts(customerInvoiceEntity.getDiscounts())
				.setTotalAmount(customerInvoiceEntity.getTotalAmount())
				
				.setMessageInvoice(customerInvoiceEntity.getMessageInvoice())
				.setMessageStatement(customerInvoiceEntity.getMessageStatement())
				
				.setTrackingNo(customerInvoiceEntity.getTrackingNo())
				.setDueDate(DateUtils.formattedDate(customerInvoiceEntity.getDueDate()));
		
	}
	
	public static CustomerServiceResponseDto toCustomerInvoiceResponseDto(CustomerInvoiceEntity customerInvoiceEntity) {
		
		return new CustomerServiceResponseDto() 
				
				.setCustomerID(customerInvoiceEntity.getCustomerMasterEntity().getId())
				.setCustomerCompanyName(customerInvoiceEntity.getCustomerMasterEntity().getCompanyName())
				.setCustEmailId(customerInvoiceEntity.getCustEmailId())
				
				.setInvoiceNo(customerInvoiceEntity.getInvoiceNo())
				.setInvoiceDate(DateUtils.formattedDate(customerInvoiceEntity.getInvoiceDate()))
				
				.setSubTotal(customerInvoiceEntity.getSubTotal())
				.setCgstAmount(customerInvoiceEntity.getCgstAmount())
				.setSgstAmount(customerInvoiceEntity.getSgstAmount())
				.setBalance(customerInvoiceEntity.getBalance())
				.setDeposit(customerInvoiceEntity.getDeposit())
				.setDiscounts(customerInvoiceEntity.getDiscounts())
				.setTotalAmount(customerInvoiceEntity.getTotalAmount())
				
				.setDueDate(DateUtils.formattedDate(customerInvoiceEntity.getDueDate()))
				
				.setPaymentStatus(StringNullEmpty.stringNullAndEmptyToBlank(customerInvoiceEntity.getPaymentStatus()));
		
	}
	
	public static List<CustomerServiceResponseDto> toCustomerInvoiceResponseDtosList(List<CustomerInvoiceEntity> customerInvoiceEntitiesList) {
		// TODO Auto-generated method stub
		
		List<CustomerServiceResponseDto> customerServiceResponseDtos = new ArrayList<CustomerServiceResponseDto>();
		for(CustomerInvoiceEntity customerInvoiceEntity : customerInvoiceEntitiesList) {
			customerServiceResponseDtos.add(toCustomerInvoiceResponseDto(customerInvoiceEntity));			
		}
				
		return customerServiceResponseDtos;
	} 
}
