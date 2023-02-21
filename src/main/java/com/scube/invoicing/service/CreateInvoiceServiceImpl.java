package com.scube.invoicing.service;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.util.ReceiptPdfExporter;

@Service
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

	@Autowired
	ReceiptPdfExporter receiptPdfExporter;
	
	@Autowired
	CustomerMasterService customerMasterService;
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@Autowired
	CreditNoteService creditNoteService;
	
	@Autowired
	EmailService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(CreateInvoiceServiceImpl.class);
	
	@Override
	public boolean generateInvoiceAndSendMailToCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("------ CreateInvoiceServiceImpl generateInvoiceAndSendMailToCustomer -------");
		
		if(createInvoiceIncomingDto.getCustomerID() == "" || createInvoiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getToEmailID() == "" || createInvoiceIncomingDto.getToEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getFromEmailID() == "" || createInvoiceIncomingDto.getFromEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getBccEmailID() == "" || createInvoiceIncomingDto.getBccEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Accountant Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getInvoiceNo() == "" || createInvoiceIncomingDto.getInvoiceNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Invoice No cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getSubject() == "" || createInvoiceIncomingDto.getSubject().trim().isEmpty()) {
			throw BRSException.throwException("Error : Mail subject cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getMailBody() == "" || createInvoiceIncomingDto.getMailBody().trim().isEmpty()) {
			throw BRSException.throwException("Error : Mail body cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyID("a0976ca4c2");
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(
				createInvoiceIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND);
		}
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceService.getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(
				customerMasterEntity, createInvoiceIncomingDto.getInvoiceNo());
		
		if(customerInvoiceEntity == null) {
			throw BRSException.throwException(EntityType.INVOICE, ExceptionType.ENTITY_NOT_FOUND, createInvoiceIncomingDto.getInvoiceNo());
		}
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceList = customerInvoiceService.
				getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(customerMasterEntity, customerInvoiceEntity);
		
		if(customerInvoiceServiceList.size() == 0) {
			throw BRSException.throwException(EntityType.INVOICE, ExceptionType.ENTITY_NOT_FOUND, createInvoiceIncomingDto.getInvoiceNo());
		}
		
		// Generate Invoice
		File attachedFile = null;
		try {
			attachedFile = receiptPdfExporter.generateInvoice(customerInvoiceServiceList, companyMasterEntity, 
					customerInvoiceEntity, createInvoiceIncomingDto, customerMasterEntity);
			logger.info("File Path for Invoice :--- " + attachedFile);
			
			// Send Mail with Attachment Invoice
	//		emailService.sendInvoiceMailToCustomer(createInvoiceIncomingDto, attachedFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean generateCreditNoteForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("------ CreateInvoiceServiceImpl generateCreditNoteForCustomer -------");
		
		if(createInvoiceIncomingDto.getCustomerID() == "" || createInvoiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getToEmailID() == "" || createInvoiceIncomingDto.getToEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getFromEmailID() == "" || createInvoiceIncomingDto.getFromEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getBccEmailID() == "" || createInvoiceIncomingDto.getBccEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Accountant Email ID cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getCreditNoteNo() == "" || createInvoiceIncomingDto.getCreditNoteNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Invoice No cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getSubject() == "" || createInvoiceIncomingDto.getSubject().trim().isEmpty()) {
			throw BRSException.throwException("Error : Mail subject cannot be blank or null");
		}
		
		if(createInvoiceIncomingDto.getMailBody() == "" || createInvoiceIncomingDto.getMailBody().trim().isEmpty()) {
			throw BRSException.throwException("Error : Mail body cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyID("a0976ca4c2");
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(createInvoiceIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Details Found");
		}
		
		CustomerCreditNoteEntity customerCreditNoteEntity = creditNoteService.
				getCustomerCreditNoteEntityByCustomerIDAndCreditNoteNo(customerMasterEntity, createInvoiceIncomingDto.getCreditNoteNo());
		
		if(customerCreditNoteEntity == null) {
			throw BRSException.throwException("Error : NO Credit Note Found");
		}
		
		List<CustomerCreditNoteDetailsEntity> creditNoteDetailsEntities = 
				creditNoteService.getCreditNoteDetailsListByCustomerIDAndCreditNoteNo(customerMasterEntity, customerCreditNoteEntity);
		
		if(creditNoteDetailsEntities.size() == 0) {
			throw BRSException.throwException("Error : NO Credit Note Service Details Found");
		}
		
		File creditNoteFile = null;
		
		try {
			creditNoteFile = receiptPdfExporter.generateCreditNote(creditNoteDetailsEntities, customerCreditNoteEntity, 
					companyMasterEntity, customerMasterEntity, createInvoiceIncomingDto);
			emailService.sendInvoiceMailToCustomer(createInvoiceIncomingDto, creditNoteFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
