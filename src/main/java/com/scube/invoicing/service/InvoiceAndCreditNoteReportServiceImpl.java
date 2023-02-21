package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.InvoiceCreditNoteReportIncomingDto;
import com.scube.invoicing.dto.mapper.InvoiceCreditNoteResponseMapper;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CreditNoteRepository;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class InvoiceAndCreditNoteReportServiceImpl implements InvoiceAndCreditNoteReportService {
	
	@Autowired
	CustomerInvoiceRepository customerInvoiceRepository;
	
	@Autowired
	CreditNoteRepository creditNoteRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceAndCreditNoteReportServiceImpl.class);

	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForInvoiceByCustomerIDAndDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getCustomerID() == "" || invoiceCreditNoteReportIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or null.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or null.");
		}
		
		logger.info("--- Customer ID :- " + invoiceCreditNoteReportIncomingDto.getCustomerID() 
		+ "--- Start Date :- " + invoiceCreditNoteReportIncomingDto.getStartDate() + 
		"--- End Date :- " + invoiceCreditNoteReportIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
					getCustomerInvoiceListByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto.getCustomerID(), 
							invoiceCreditNoteReportIncomingDto.getStartDate(), 
							DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		return InvoiceCreditNoteResponseMapper.toInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForCreditNoteByCustomerIDAndDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForCreditNoteByCustomerIDAndDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getCustomerID() == "" || invoiceCreditNoteReportIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or null.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or null.");
		}
		
		logger.info("--- Customer ID :- " + invoiceCreditNoteReportIncomingDto.getCustomerID() 
		+ "--- Start Date :- " + invoiceCreditNoteReportIncomingDto.getStartDate() + 
		"--- End Date :- " + invoiceCreditNoteReportIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList = creditNoteRepository.
				getCustomerCreditNoteListByCustomerIDAndDateRange(invoiceCreditNoteReportIncomingDto.getCustomerID(), 
						invoiceCreditNoteReportIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		return InvoiceCreditNoteResponseMapper.toCreditNoteResponseDtosList(customerCreditNoteEntitiesList);
	}

}
