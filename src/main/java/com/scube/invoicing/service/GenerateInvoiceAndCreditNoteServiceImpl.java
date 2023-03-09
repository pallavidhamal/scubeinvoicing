package com.scube.invoicing.service;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.entity.CheckCreditNoteMailStatusEntity;
import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.CheckCreditNoteMailStatusRepository;
import com.scube.invoicing.repository.CheckInvoiceMailStatusRepository;
import com.scube.invoicing.util.ReceiptPdfExporter;

@Service
public class GenerateInvoiceAndCreditNoteServiceImpl implements GenerateInvoiceAndCreditNoteService {

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
	
	@Autowired
	CheckInvoiceMailStatusRepository checkInvoiceMailStatusRepository;
	
	@Autowired
	CheckCreditNoteMailStatusRepository checkCreditNoteMailStatusRepository;
	
	@Value("${company.name}")
	private String companyName;
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateInvoiceAndCreditNoteServiceImpl.class);
	
	@Override
	public boolean generateInvoiceAndSendMailToCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto, 
			CustomerInvoiceEntity customerInvoiceEntity, CustomerMasterEntity customerMasterEntity, 
			List<CheckInvoiceMailStatusEntity> checkMailStatusEntityList) {
		// TODO Auto-generated method stub
		
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl generateInvoiceAndSendMailToCustomer -------");
/*		
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
*/		
		logger.info(companyName);
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : No Company Details Found");
		}
/*		
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
*/		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceList = customerInvoiceService.
				getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails(customerMasterEntity, customerInvoiceEntity);
		
		if(customerInvoiceServiceList.size() == 0) {
			throw BRSException.throwException(EntityType.INVOICE, ExceptionType.ENTITY_NOT_FOUND, createInvoiceIncomingDto.getInvoiceNo());
		}
		
		// Generate Invoice
		File attachedFile = null;
		try {
			attachedFile = receiptPdfExporter.generateInvoice(customerInvoiceServiceList, companyMasterEntity, customerInvoiceEntity);
			logger.info("File Path for Invoice :--- " + attachedFile);
			
			// Send Mail with Attachment Invoice
			emailService.sendInvoiceMailToCustomer(createInvoiceIncomingDto, attachedFile, checkMailStatusEntityList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean generateCreditNoteForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto, 
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity,
			List<CheckCreditNoteMailStatusEntity> checkCreditNoteMailStatusEntityList) {
		// TODO Auto-generated method stub
/*		
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl generateCreditNoteForCustomer -------");
		
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
*/		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
/*		
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
*/		
		List<CustomerCreditNoteDetailsEntity> creditNoteDetailsEntities = 
				creditNoteService.getCreditNoteDetailsListByCustomerIDAndCreditNoteNo(customerMasterEntity, customerCreditNoteEntity);
		
		if(creditNoteDetailsEntities.size() == 0) {
			throw BRSException.throwException("Error : NO Credit Note Service Details Found");
		}
		
		File creditNoteFile = null;
		
		try {
			creditNoteFile = receiptPdfExporter.generateCreditNote(creditNoteDetailsEntities, customerCreditNoteEntity, 
					companyMasterEntity);
			emailService.sendCreditNoteMail(createInvoiceIncomingDto, creditNoteFile, checkCreditNoteMailStatusEntityList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean sendGeneratedInvoiceMail() {
		// TODO Auto-generated method stub
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl sendGeneratedInvoiceMail -------");
		
		List<CheckInvoiceMailStatusEntity> checkMailStatusEntityList = checkInvoiceMailStatusRepository.getInvoiceMailStatusEntityByMailStatus();
		logger.info("--- Size :-- " + checkMailStatusEntityList.size());
		
		CreateInvoiceIncomingDto createInvoiceIncomingDto = new CreateInvoiceIncomingDto();
		
		for(int i=0; i<checkMailStatusEntityList.size(); i++) {
			try {
				createInvoiceIncomingDto.setCustomerID(checkMailStatusEntityList.get(i).getCustomerMasterEntity().getId());
				createInvoiceIncomingDto.setInvoiceNo(checkMailStatusEntityList.get(i).getCustomerInvoiceEntity().getInvoiceNo());
				createInvoiceIncomingDto.setToEmailID(checkMailStatusEntityList.get(i).getToMailID());
				createInvoiceIncomingDto.setFromEmailID(checkMailStatusEntityList.get(i).getFromMailID());
				createInvoiceIncomingDto.setBccEmailID(checkMailStatusEntityList.get(i).getBccMailID());
				createInvoiceIncomingDto.setSubject("# " + checkMailStatusEntityList.get(i).getCustomerInvoiceEntity().
						getInvoiceNo() + " from " + companyName);
				createInvoiceIncomingDto.setMailBody("Please find the Invoice. "
						+ "If you have any clarification kindly contact.\" \r\n"
						+ "	\"Thanks for your Business!,\"\r\n"
						+ 		companyName);
				logger.info("---- Dto required :-- " + createInvoiceIncomingDto);		
				generateInvoiceAndSendMailToCustomer(createInvoiceIncomingDto, 
						checkMailStatusEntityList.get(i).getCustomerInvoiceEntity(), 
						checkMailStatusEntityList.get(i).getCustomerMasterEntity(),
						checkMailStatusEntityList);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return false;
	}

	@Override
	public boolean saveInvoiceMailStatusForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl saveInvoiceAndMailStatusForCustomer -------");
		
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
		
		logger.info(companyName);
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : No Company Details Found");
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
		
		CheckInvoiceMailStatusEntity checkMailStatusEntity = new CheckInvoiceMailStatusEntity();
		checkMailStatusEntity.setIsdeleted("N");
		checkMailStatusEntity.setMailStatus("N");
		checkMailStatusEntity.setFromMailID(createInvoiceIncomingDto.getFromEmailID());
		checkMailStatusEntity.setToMailID(createInvoiceIncomingDto.getToEmailID());
		checkMailStatusEntity.setBccMailID(createInvoiceIncomingDto.getBccEmailID());
		checkMailStatusEntity.setCustomerMasterEntity(customerMasterEntity);
		checkMailStatusEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
		
		checkInvoiceMailStatusRepository.save(checkMailStatusEntity);
		
		return true;
	}

	@Override
	public boolean saveCreditNoteMailStatusForCustomer(@Valid CreateInvoiceIncomingDto createInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl saveCreditNoteMailStatusForCustomer -------");
		
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
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
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
		
		CheckCreditNoteMailStatusEntity checkCreditNoteMailStatusEntity = new CheckCreditNoteMailStatusEntity();
		checkCreditNoteMailStatusEntity.setIsdeleted("N");
		checkCreditNoteMailStatusEntity.setMailStatus("N");
		checkCreditNoteMailStatusEntity.setFromMailID(createInvoiceIncomingDto.getFromEmailID());
		checkCreditNoteMailStatusEntity.setToMailID(createInvoiceIncomingDto.getToEmailID());
		checkCreditNoteMailStatusEntity.setBccMailID(createInvoiceIncomingDto.getBccEmailID());
		checkCreditNoteMailStatusEntity.setCustomerMasterEntity(customerMasterEntity);
		checkCreditNoteMailStatusEntity.setCustomerCreditNoteEntity(customerCreditNoteEntity);
		
		checkCreditNoteMailStatusRepository.save(checkCreditNoteMailStatusEntity);
		
		return true;
	}

	@Override
	public boolean sendGeneratedCreditNoteMail() {
		// TODO Auto-generated method stub
		logger.info("------ GenerateInvoiceAndCreditNoteServiceImpl sendGeneratedCreditNoteMail -------");
		
		List<CheckCreditNoteMailStatusEntity> checkCreditNoteMailStatusEntityList = checkCreditNoteMailStatusRepository.getCreditNoteMailStatusEntityByMailStatus();
		
		CreateInvoiceIncomingDto createInvoiceIncomingDto = new CreateInvoiceIncomingDto();
		for(int i=0; i<checkCreditNoteMailStatusEntityList.size(); i++) {
			try {
				createInvoiceIncomingDto.setCustomerID(checkCreditNoteMailStatusEntityList.get(i).getCustomerMasterEntity().getId());
				createInvoiceIncomingDto.setCreditNoteNo(checkCreditNoteMailStatusEntityList.get(i).getCustomerCreditNoteEntity().getCreditNoteNo());
				createInvoiceIncomingDto.setToEmailID(checkCreditNoteMailStatusEntityList.get(i).getToMailID());
				createInvoiceIncomingDto.setFromEmailID(checkCreditNoteMailStatusEntityList.get(i).getFromMailID());
				createInvoiceIncomingDto.setBccEmailID(checkCreditNoteMailStatusEntityList.get(i).getBccMailID());
				createInvoiceIncomingDto.setSubject("# " + checkCreditNoteMailStatusEntityList.get(i).getCustomerCreditNoteEntity().
						getCreditNoteNo() + " from " + companyName);
				createInvoiceIncomingDto.setMailBody("Please find the Invoice. "
						+ "If you have any clarification kindly contact.\" \r\n"
						+ "	\"Thanks for your Business!,\"\r\n"
						+ 		companyName);
				logger.info("---- DTO required :-- " + createInvoiceIncomingDto);		
				generateCreditNoteForCustomer(createInvoiceIncomingDto, 
						checkCreditNoteMailStatusEntityList.get(i).getCustomerMasterEntity(),
						checkCreditNoteMailStatusEntityList.get(i).getCustomerCreditNoteEntity(),
						checkCreditNoteMailStatusEntityList);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return false;
	}

}
