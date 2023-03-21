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
import com.scube.invoicing.dto.incoming.ReportsIncomingDto;
import com.scube.invoicing.dto.mapper.GSTReportResponseMapper;
import com.scube.invoicing.dto.mapper.InvoiceCreditNoteResponseMapper;
import com.scube.invoicing.dto.mapper.TDSReportResponseMapper;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CreditNoteRepository;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class ReportsServiceImpl implements ReportsService {
	
	@Autowired
	CustomerInvoiceRepository customerInvoiceRepository;
	
	@Autowired
	CreditNoteRepository creditNoteRepository;
	
	Base64.Decoder decoder = Base64.getDecoder();
	
	private static final Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);
	
	// Report for Invoice by Customer and Date Range (All Customer/ Single Customer)
	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForInvoiceByCustomerIDAndDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForInvoiceByCustomerIDAndDateRange ------");
		
		if(reportsIncomingDto.getCustomerID() == "" || reportsIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Customer ID :- " + reportsIncomingDto.getCustomerID() + "--- Start Date :- " + reportsIncomingDto.getStartDate() + 
		"--- End Date :- " + reportsIncomingDto.getEndDate() + "--- Converted End Date :- " + DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = new ArrayList<CustomerInvoiceEntity>();
		if(reportsIncomingDto.getCustomerID().equals("All")) {
			customerInvoiceEntitiesList = customerInvoiceRepository.getAllCustomerInvoiceListByDateRange(
					reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		else {
			customerInvoiceEntitiesList = customerInvoiceRepository.getCustomerInvoiceListByCustomerIDAndDateRange(reportsIncomingDto.getCustomerID(), 
					reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		return InvoiceCreditNoteResponseMapper.toInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	
	// Report for Credit Note by Customer and Date Range (All Customer/ Single Customer)
	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForCreditNoteByCustomerIDAndDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForCreditNoteByCustomerIDAndDateRange ------");
		
		if(reportsIncomingDto.getCustomerID() == "" || reportsIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Customer ID :- " + reportsIncomingDto.getCustomerID() 
		+ "--- Start Date :- " + reportsIncomingDto.getStartDate() + 
		"--- End Date :- " + reportsIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList = new ArrayList<CustomerCreditNoteEntity>();
		
		if(reportsIncomingDto.getCustomerID().equals("All")) {
			customerCreditNoteEntitiesList = creditNoteRepository.getAllCustomerCreditNoteListByDateRange( 
					reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		else {
			customerCreditNoteEntitiesList = creditNoteRepository.getCustomerCreditNoteListByCustomerIDAndDateRange(reportsIncomingDto.getCustomerID(),
					reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		
		return InvoiceCreditNoteResponseMapper.toCreditNoteResponseDtosList(customerCreditNoteEntitiesList);
	}
	
	// Report for Invoice Pending/Paid by Customer and Date Range (All Customer/ Single Customer)
	@Override
	public List<InvoiceCreditNoteResponseDto> generateReportForInvoicePendingAndPaidAmountByDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateReportForInvoicePendingAndPaidAmountByDateRange ------");
		
		if(reportsIncomingDto.getCustomerID() == "" || reportsIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getAmountPendingFlag() == "" || reportsIncomingDto.getAmountPendingFlag().trim().isEmpty()) {
			throw BRSException.throwException("Error : Select if Amount is pending or paid cannot be blank or empty.");
		}
		
		logger.info("--- Customer ID :- " + reportsIncomingDto.getCustomerID() 
		+ "--- Start Date :- " + reportsIncomingDto.getStartDate() + 
		"--- End Date :- " + reportsIncomingDto.getEndDate() + "--- Converted End Date :- " 
		+ DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = new ArrayList<CustomerInvoiceEntity>();
		
		if(reportsIncomingDto.getCustomerID().equals("All")) {
			
			if(reportsIncomingDto.getAmountPendingFlag().equals("All")) {
				customerInvoiceEntitiesList = customerInvoiceRepository.getAllCustomerInvoiceListByDateRange(reportsIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
			}
			else {
			customerInvoiceEntitiesList = customerInvoiceRepository.getAllCustomerInvoiceListByPaymentStatusAndDateRange(
					reportsIncomingDto.getAmountPendingFlag(),reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
			}
		}
		else {
			if(reportsIncomingDto.getAmountPendingFlag().equals("All")) {
				customerInvoiceEntitiesList = customerInvoiceRepository.getCustomerInvoiceListByCustomerIDAndDateRange(reportsIncomingDto.getCustomerID(),
						reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
			}
			else {
				customerInvoiceEntitiesList = customerInvoiceRepository.getCustomerInvoiceListByCustomerIDAndPaymentStatusAndDateRange(reportsIncomingDto.getCustomerID(),
					reportsIncomingDto.getAmountPendingFlag(),reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
			}
		}
		
		return InvoiceCreditNoteResponseMapper.toInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	// Report for GST - Invoice by Customer and Date Range (All Customer/ Single Customer)
	@Override
	public List<GSTReportResponseDto> generateGSTReportForPaidInvoiceByDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateGSTReportForInvoiceByDateRange ------");
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + reportsIncomingDto.getStartDate() + "--- End Date :- " + reportsIncomingDto.getEndDate() + 
				"--- Converted End Date :- " + DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = new ArrayList<CustomerInvoiceEntity>();
		if(reportsIncomingDto.getCustomerID().equals("All")) {
			customerInvoiceEntitiesList =  customerInvoiceRepository.getAllCustomerInvoiceListForPaymentCompletedInvoiceByDateRange(reportsIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		else {
			customerInvoiceEntitiesList =  customerInvoiceRepository.getInvoiceListForPaymentCompletedInvoiceByCustomerIDAndDateRange(
					reportsIncomingDto.getCustomerID(), reportsIncomingDto.getStartDate(), DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		
		return GSTReportResponseMapper.toGSTReportForInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	// Report for GST - Credit Note by Customer and Date Range (All Customer/ Single Customer)
	@Override
	public List<GSTReportResponseDto> generateGSTReportForCreditNoteByDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateGSTReportForCreditNoteByDateRange ------");
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + reportsIncomingDto.getStartDate() + "--- End Date :- " + reportsIncomingDto.getEndDate() + 
				"--- Converted End Date :- " + DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList =  creditNoteRepository.
				getCustomerCreditNoteEntityByDateRange(reportsIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		return GSTReportResponseMapper.toGSTReportForCreditNoteResponseDtosList(customerCreditNoteEntitiesList);
	}

	// Total Amount Calculation CGST/SGST/IGST/GST_4 - Credit Note Report
	@Override
	public GSTReportResponseDto calculateTotalAmountForGSTReportForCreditNote(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl calculateTotalAmountForGSTReportForCreditNote ------");
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList =  creditNoteRepository.
				getCustomerCreditNoteEntityByDateRange(reportsIncomingDto.getStartDate(), 
						DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<Double> cgstValues = new ArrayList<Double>();
		List<Double> sgstValues = new ArrayList<Double>();
		List<Double> igstValues = new ArrayList<Double>();
//		List<Double> totalCreditsValues = new ArrayList<Double>();
		
		for(int i=0; i<customerCreditNoteEntitiesList.size(); i++) {
			cgstValues.add(customerCreditNoteEntitiesList.get(i).getCgstAmount() != null ?
					Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getCgstAmount()))) : null);
			sgstValues.add(customerCreditNoteEntitiesList.get(i).getSgstAmount() != null ?
					Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getSgstAmount()))) : null);
			igstValues.add(customerCreditNoteEntitiesList.get(i).getIgstAmount() != null ?
					Double.valueOf(new String(decoder.decode(customerCreditNoteEntitiesList.get(i).getIgstAmount()))) : null);
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

	
	// Total Amount Calculation CGST/SGST/IGST/GST_4 - Invoice Report
	@Override
	public GSTReportResponseDto calculateTotalAmountForGSTReportForInvoice(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl calculateTotalAmountForGSTReportForInvoice ------");
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
				getAllCustomerInvoiceListForPaymentCompletedInvoiceByDateRange(reportsIncomingDto.getStartDate(), 
					DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<Double> cgstValues = new ArrayList<Double>();
		List<Double> sgstValues = new ArrayList<Double>();
		List<Double> igstValues = new ArrayList<Double>();
		
		for(int i=0; i<customerInvoiceEntitiesList.size(); i++) {
			cgstValues.add(customerInvoiceEntitiesList.get(i).getCgstAmount() != null ?
					Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getCgstAmount()))) : null);
			sgstValues.add(customerInvoiceEntitiesList.get(i).getSgstAmount() != null ?
				Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getSgstAmount()))) : null);
			igstValues.add(customerInvoiceEntitiesList.get(i).getIgstAmount() != null ?
					Double.valueOf(new String(decoder.decode(customerInvoiceEntitiesList.get(i).getIgstAmount()))) : null);
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
	
	// TDS Report for Invoice - For All Customer/Single Customer By Date Range
	@Override
	public List<GSTReportResponseDto> generateTDSReportForInvoiceByDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateTDSReportForInvoiceByDateRange ------");
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + reportsIncomingDto.getStartDate() + 
				"--- End Date :- " + reportsIncomingDto.getEndDate() + "--- Converted End Date :- " 
				+ DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = new ArrayList<CustomerInvoiceEntity>();
		
		if(reportsIncomingDto.getCustomerID().equals("All")) {
			customerInvoiceEntitiesList = customerInvoiceRepository.getAllCustomerInvoiceListForPaymentCompletedInvoiceByDateRange(reportsIncomingDto.getStartDate(),
					DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		else {
			customerInvoiceEntitiesList = customerInvoiceRepository.
					getInvoiceListForPaymentCompletedInvoiceByCustomerIDAndDateRange(reportsIncomingDto.getCustomerID(), reportsIncomingDto.getStartDate(),
						DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		}
		
		return TDSReportResponseMapper.toTDSReportForInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}
	
	// TDS Report for Credit Note - For All Customer/Single Customer By Date Range
	@Override
	public List<GSTReportResponseDto> generateTDSReportForCreditNoteByDateRange(@Valid ReportsIncomingDto reportsIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- InvoiceAndCreditNoteReportServiceImpl generateTDSReportForCreditNoteByDateRange ------");
		
		if(reportsIncomingDto.getStartDate() == "" || reportsIncomingDto.getStartDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : Start Date cannot be blank or empty.");
		}
		
		if(reportsIncomingDto.getEndDate() == "" || reportsIncomingDto.getEndDate().trim().isEmpty()) {
			throw BRSException.throwException("Error : End Date cannot be blank or empty.");
		}
		
		logger.info("--- Start Date :- " + reportsIncomingDto.getStartDate() + "--- End Date :- " + reportsIncomingDto.getEndDate() + 
				"--- Converted End Date :- " + DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList = creditNoteRepository.getCustomerCreditNoteEntityByDateRange
			(reportsIncomingDto.getStartDate(),DateUtils.add1DayToInputDate(reportsIncomingDto.getEndDate()));
		
		return TDSReportResponseMapper.toTDSReportForCreditNoteResponseDtosList(customerCreditNoteEntitiesList);
	}

}
