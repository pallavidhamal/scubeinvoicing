package com.scube.invoicing.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.GSTReportResponseDto;
import com.scube.invoicing.dto.InvoiceCreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.InvoiceCreditNoteReportIncomingDto;
import com.scube.invoicing.dto.mapper.GSTReportResponseMapper;
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
	
	Base64.Decoder decoder = Base64.getDecoder();
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceAndCreditNoteReportServiceImpl.class);

	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForInvoiceByCustomerIDAndDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getCustomerID() == "" || invoiceCreditNoteReportIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
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
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
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

	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForInvoicePendingAndPaidAmountByDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForInvoicePendingAndPaidAmountByDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getCustomerID() == "" || invoiceCreditNoteReportIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getAmountPendingFlag() == "" || invoiceCreditNoteReportIncomingDto.getAmountPendingFlag().trim().isEmpty()) {
			throw BRSException.throwException("Error : Select if Amount is pending or paid cannot be blank or empty.");
		}
		
		logger.info("--- Customer ID :- " + invoiceCreditNoteReportIncomingDto.getCustomerID() 
		+ "--- Start Date :- " + invoiceCreditNoteReportIncomingDto.getStartDate() + 
		"--- End Date :- " + invoiceCreditNoteReportIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
				getCustomerInvoiceListByCustomerIDAndPaymentStatusAndDateRange(invoiceCreditNoteReportIncomingDto.getCustomerID(),
						invoiceCreditNoteReportIncomingDto.getAmountPendingFlag(),
						invoiceCreditNoteReportIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		return InvoiceCreditNoteResponseMapper.toInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	@Override
	public List<GSTReportResponseDto> generateGSTReportForPaidInvoiceByDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateGSTReportForInvoiceByDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + invoiceCreditNoteReportIncomingDto.getStartDate() + 
		"--- End Date :- " + invoiceCreditNoteReportIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList =  customerInvoiceRepository.
				getCustomerInvoiceEntityForPaymentCompletedInvoiceByDateRange(invoiceCreditNoteReportIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		return GSTReportResponseMapper.toGSTReportForInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	@Override
	public List<GSTReportResponseDto> generateGSTReportForCreditNoteByDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateGSTReportForCreditNoteByDateRange ------");
		
		if(invoiceCreditNoteReportIncomingDto.getStartDate() == "" || invoiceCreditNoteReportIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(invoiceCreditNoteReportIncomingDto.getEndDate() == "" || invoiceCreditNoteReportIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + invoiceCreditNoteReportIncomingDto.getStartDate() + 
		"--- End Date :- " + invoiceCreditNoteReportIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList =  creditNoteRepository.
				getGSTReportForCreditNoteByDateRange(invoiceCreditNoteReportIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		return GSTReportResponseMapper.toGSTReportForCreditNoteResponseDtosList(customerCreditNoteEntitiesList);
	}

	@Override
	public GSTReportResponseDto calculateTotalAmountForGSTReportForCreditNote(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl calculateTotalAmountForGSTReportForCreditNote ------");
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList =  creditNoteRepository.
				getGSTReportForCreditNoteByDateRange(invoiceCreditNoteReportIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<Double> cgstValues = new ArrayList<Double>();
		List<Double> sgstValues = new ArrayList<Double>();
		List<Double> igstValues = new ArrayList<Double>();
//		List<Double> totalCreditsValues = new ArrayList<Double>();
		
		for(int i=0; i<customerCreditNoteEntitiesList.size(); i++) {
			cgstValues.add(Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getCgstAmount()))));
			sgstValues.add(Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getSgstAmount()))));
			sgstValues.add(Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getSgstAmount()))));
//			totalCreditValues.add(Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getCreditsRemaining()))));
		}
		
		double cgstTotal = 0;
		for(Double value : cgstValues) {
			if(value != null) {
				cgstTotal += value;
			}
		}
		logger.info("-- CGST Amount Total -- " + cgstTotal);
		
		double sgstTotal = 0;
		for(Double value : sgstValues) {
			if(value != null) {
				sgstTotal += value;
			}
		}
		logger.info("-- SGST Amount Total -- " + sgstTotal);
		
		double igstTotal = 0;
		for(Double value : igstValues) {
			if(value != null) {
				igstTotal += value;
			}
		}
		logger.info("-- IGST Amount Total -- " + igstTotal);
		logger.info("Total GST Amount :-- " + String.valueOf(cgstTotal + sgstTotal + igstTotal));
		
		GSTReportResponseDto gstReportResponseDto = new GSTReportResponseDto();
		gstReportResponseDto.setCreditNoteTotalCgstAmount(String.valueOf(cgstTotal));
		gstReportResponseDto.setCreditNoteTotalSgstAmount(String.valueOf(sgstTotal));
		gstReportResponseDto.setCreditNoteTotalIgstAmount(String.valueOf(igstTotal));
		gstReportResponseDto.setCreditNoteTotalGstAmount(String.valueOf(cgstTotal + sgstTotal + igstTotal));
		
		return gstReportResponseDto;
	}

	@Override
	public GSTReportResponseDto calculateTotalAmountForGSTReportForInvoice(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl calculateTotalAmountForGSTReportForInvoice ------");
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
				getCustomerInvoiceEntityForPaymentCompletedInvoiceByDateRange(invoiceCreditNoteReportIncomingDto.getStartDate(), 
					DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		List<Double> cgstValues = new ArrayList<Double>();
		List<Double> sgstValues = new ArrayList<Double>();
		List<Double> igstValues = new ArrayList<Double>();
		
		for(int i=0; i<customerInvoiceEntitiesList.size(); i++) {
			cgstValues.add(Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getCgstAmount()))));
			sgstValues.add(customerInvoiceEntitiesList.get(i).getSgstAmount() != null ?
				Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getSgstAmount()))) : null);
			igstValues.add(Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getIgstAmount()))));
		}
		
		double cgstTotal = 0;
		for(Double value : cgstValues) {
			if(value != null) {
				cgstTotal += value;
			}
		}
		logger.info("-- CGST Amount Total -- " + cgstTotal);
		
		double sgstTotal = 0;
		for(Double value : sgstValues) {
			if(value != null) {
				sgstTotal += value;
			}
		}
		logger.info("-- SGST Amount Total -- " + sgstTotal);
		
		double igstTotal = 0;
		for(Double value : igstValues) {
			if(value != null) {
				igstTotal += value;
			}
		}
		logger.info("-- IGST Amount Total -- " + igstTotal);
		logger.info("Total GST Amount :-- " + String.valueOf(cgstTotal + sgstTotal + igstTotal));
		
		GSTReportResponseDto gstReportResponseDto = new GSTReportResponseDto();
		// CGST/ SGST/ IGST/ Total Amount for all GST
		gstReportResponseDto.setInvoiceTotalCgstAmount(String.valueOf(cgstTotal));
		gstReportResponseDto.setInvoiceTotalSgstAmount(String.valueOf(sgstTotal));
		gstReportResponseDto.setInvoiceTotalIgstAmount(String.valueOf(igstTotal));
		gstReportResponseDto.setInvoiceTotalGstAmount(String.valueOf(cgstTotal + igstTotal + sgstTotal));
		
		return gstReportResponseDto;
	}
	
	
	@Override
	public List<GSTReportResponseDto> generateTDSReportForInvoiceByDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateTDSReportForInvoiceByDateRange ------");
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
			getCustomerInvoiceEntityForPaymentCompletedInvoiceByDateRange(invoiceCreditNoteReportIncomingDto.getStartDate(),
					DateUtils.add1DayToInputDate(invoiceCreditNoteReportIncomingDto.getEndDate()));
		
		for(int i=0; i<customerInvoiceEntitiesList.size(); i++) {
			
		}
		
		return null;
	}

	@Override
	public List<GSTReportResponseDto> generateTDSReportForCreditNoteByDateRange(
			@Valid InvoiceCreditNoteReportIncomingDto invoiceCreditNoteReportIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateTDSReportForCreditNoteByDateRange ------");
		
		
		
		return null;
	}

}
