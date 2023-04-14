package com.scube.invoicing.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CreditNoteResponseDto;
import com.scube.invoicing.dto.incoming.CreditNoteDetailsIncomingDto;
import com.scube.invoicing.dto.incoming.CreditNoteIncomingDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.dto.mapper.CustomerCreditNoteMapper;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.CreditNoteDetailsRepository;
import com.scube.invoicing.repository.CreditNoteRepository;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.RandomUtils;

@Service
public class CreditNoteServiceImpl implements CreditNoteService{
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@Autowired
	GSTMasterService gstMasterService;
	
	@Autowired
	ServiceMasterDetailsService serviceMasterDetailsService;
	
	@Autowired
	CustomerMasterService customerMasterService;
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;
	
	@Autowired
	CustomerInvoiceRepository customerInvoiceRepository;
	
	@Autowired
	CreditNoteRepository creditNoteRepository;
	
	@Autowired
	CreditNoteDetailsRepository creditNoteDetailsRepository;
	
	@Value("${company.name}")
	private String companyName;
	
	Base64.Encoder encoder = Base64.getEncoder();
	Base64.Decoder decoder = Base64.getDecoder();
	
	private static final Logger logger = LoggerFactory.getLogger(CreditNoteServiceImpl.class);

	@Override
	public CreditNoteResponseDto addCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("------ CreditNoteServiceImpl addCreditNoteAndService -------");
		
		if(creditNoteIncomingDto.getCustomerID() == "" || creditNoteIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(creditNoteIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Details Found");
		}
		
		Set<CustomerInvoiceEntity> customerInvoiceSet = new HashSet<CustomerInvoiceEntity>(); 
		
		if(creditNoteIncomingDto.getCustomerInvoiceIncomingDtos() != null) {
			for(CustomerInvoiceIncomingDto customerInvoiceIncomingDto :
				creditNoteIncomingDto.getCustomerInvoiceIncomingDtos()) {
				CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(customerInvoiceIncomingDto.getInvoiceID()).get();
				customerInvoiceSet.add(customerInvoiceEntity);
			}
		}
		
		CustomerCreditNoteEntity customerCreditNoteEntity = new CustomerCreditNoteEntity();
		
		customerCreditNoteEntity.setIsdeleted("N");
		
		// Set Customer Entity
		customerCreditNoteEntity.setCustomerMasterEntity(customerMasterEntity);
		
		// CGST/SGST/IGST/GST4 Amount values
		customerCreditNoteEntity.setCgstAmount(creditNoteIncomingDto.getCgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getCgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setSgstAmount(creditNoteIncomingDto.getSgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getSgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setIgstAmount(creditNoteIncomingDto.getIgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getIgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setGst4Amount(creditNoteIncomingDto.getGst4Amount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getGst4Amount().getBytes(StandardCharsets.UTF_8)) : null);
		
		customerCreditNoteEntity.setDeclaredTds(creditNoteIncomingDto.getDeclaredTds() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getDeclaredTds().getBytes(StandardCharsets.UTF_8)) : null);
/*		customerCreditNoteEntity.setActualTds(creditNoteIncomingDto.getActualTds() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getActualTds().getBytes(StandardCharsets.UTF_8)) : null); */
		
		// Total Amount/ Sub total/ Credits Remaining
		customerCreditNoteEntity.setTotalAmount(encoder.encodeToString
				(creditNoteIncomingDto.getTotalAmount().getBytes(StandardCharsets.UTF_8)));
		customerCreditNoteEntity.setSubTotal(encoder.encodeToString
				(creditNoteIncomingDto.getSubTotal().getBytes(StandardCharsets.UTF_8)));
		customerCreditNoteEntity.setCreditsRemaining(encoder.encodeToString
				(creditNoteIncomingDto.getCreditsRemaining().getBytes(StandardCharsets.UTF_8)));
		
		// Credit Note No and Credit Note Date
		customerCreditNoteEntity.setCreditNoteNo("CN-000"+RandomUtils.generateRandomNumber());
		customerCreditNoteEntity.setCreditNoteDate(DateUtils.stringToDateConvert(creditNoteIncomingDto.getCreditNoteDate()));
		customerCreditNoteEntity.setCustomerInvoiceEntity(customerInvoiceSet);
		
		creditNoteRepository.save(customerCreditNoteEntity);
		
		Set<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntities = new HashSet<CustomerCreditNoteDetailsEntity>();
		
		for(CreditNoteDetailsIncomingDto creditNoteDetailsIncomingDto : 
			creditNoteIncomingDto.getCreditNoteDetailsIncomingDto()) {
			
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(creditNoteDetailsIncomingDto.getTax());
			ServiceMasterEntity serviceMasterEntity = serviceMasterDetailsService.getServiceMasterEntityByServiceID(
					creditNoteDetailsIncomingDto.getService());
		
			CustomerCreditNoteDetailsEntity customerCreditNoteDetailsEntity = new CustomerCreditNoteDetailsEntity();
			
			customerCreditNoteDetailsEntity.setIsdeleted("N");
			
			// GST/Customer/Credit Note/ Service Entity
			customerCreditNoteDetailsEntity.setGstMasterEntity(gstMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerMasterEntity(customerMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerCreditNoteEntity(customerCreditNoteEntity);
			customerCreditNoteDetailsEntity.setServiceMasterEntity(serviceMasterEntity);
			
			// Credit Note Service Details
			customerCreditNoteDetailsEntity.setDescription(creditNoteDetailsIncomingDto.getDescription());
			customerCreditNoteDetailsEntity.setRate(creditNoteDetailsIncomingDto.getRate());
			customerCreditNoteDetailsEntity.setQuantity(creditNoteDetailsIncomingDto.getQuantity());
			customerCreditNoteDetailsEntity.setAmount(encoder.encodeToString
					(creditNoteDetailsIncomingDto.getAmount().getBytes(StandardCharsets.UTF_8)));
			customerCreditNoteDetailsEntity.setServiceAmountWithGst(encoder.encodeToString
					(creditNoteDetailsIncomingDto.getServiceAmountWithGst().getBytes(StandardCharsets.UTF_8)));
			customerCreditNoteDetailsEntity.setGstAmount(encoder.encodeToString(creditNoteDetailsIncomingDto.getGstAmount().getBytes(StandardCharsets.UTF_8)));
			
			customerCreditNoteDetailsEntities.add(customerCreditNoteDetailsEntity);
		}
		creditNoteDetailsRepository.saveAll(customerCreditNoteDetailsEntities);
		
		return CustomerCreditNoteMapper.toCustomerCreditNoteAndCompanyResponseMailDto(customerCreditNoteEntity, companyMasterEntity);
	}

	@Override
	public CreditNoteResponseDto updateCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl updateCreditNoteAndService -------");
		
		if(creditNoteIncomingDto.getCustomerID() == "" || creditNoteIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(creditNoteIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Details Found");
		}
		
		Set<CustomerInvoiceEntity> customerInvoiceSet = new HashSet<CustomerInvoiceEntity>(); 
		
		if(creditNoteIncomingDto.getCustomerInvoiceIncomingDtos() != null) {
			for(CustomerInvoiceIncomingDto customerInvoiceIncomingDto :
				creditNoteIncomingDto.getCustomerInvoiceIncomingDtos()) {
				CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(customerInvoiceIncomingDto.getInvoiceID()).get();
				customerInvoiceSet.add(customerInvoiceEntity);
			}
		}
		
		CustomerCreditNoteEntity customerCreditNoteEntity = creditNoteRepository.findById(creditNoteIncomingDto.getCreditNoteID()).get();
		
		customerCreditNoteEntity.setIsdeleted("N");
		
		// Set Customer Entity
		customerCreditNoteEntity.setCustomerMasterEntity(customerMasterEntity);
		
		// CGST/SGST/IGST/GST4 Amount values
		customerCreditNoteEntity.setCgstAmount(creditNoteIncomingDto.getCgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getCgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setSgstAmount(creditNoteIncomingDto.getSgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getSgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setIgstAmount(creditNoteIncomingDto.getIgstAmount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getIgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerCreditNoteEntity.setGst4Amount(creditNoteIncomingDto.getGst4Amount() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getGst4Amount().getBytes(StandardCharsets.UTF_8)) : null);
		
		customerCreditNoteEntity.setDeclaredTds(creditNoteIncomingDto.getDeclaredTds() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getDeclaredTds().getBytes(StandardCharsets.UTF_8)) : null);
/*		customerCreditNoteEntity.setActualTds(creditNoteIncomingDto.getActualTds() != null ? 
				encoder.encodeToString(creditNoteIncomingDto.getActualTds().getBytes(StandardCharsets.UTF_8)) : null); */
		
		// Total Amount/ Sub total/ Credits Remaining
		customerCreditNoteEntity.setTotalAmount(encoder.encodeToString
				(creditNoteIncomingDto.getTotalAmount().getBytes(StandardCharsets.UTF_8)));
		customerCreditNoteEntity.setSubTotal(encoder.encodeToString
				(creditNoteIncomingDto.getSubTotal().getBytes(StandardCharsets.UTF_8)));
		customerCreditNoteEntity.setCreditsRemaining(encoder.encodeToString
				(creditNoteIncomingDto.getCreditsRemaining().getBytes(StandardCharsets.UTF_8)));
		
		// Credit Note No and Credit Note Date
/*		customerCreditNoteEntity.setCreditNoteNo("CN-00"+RandomUtils.generateRandomNumber()); */
		customerCreditNoteEntity.setCreditNoteDate(DateUtils.stringToDateConvert(creditNoteIncomingDto.getCreditNoteDate()));
		customerCreditNoteEntity.setCustomerInvoiceEntity(customerInvoiceSet);
		
		creditNoteRepository.save(customerCreditNoteEntity);
		
		List<CustomerCreditNoteDetailsEntity> creditNoteDetailsEntityList = creditNoteDetailsRepository.
				findByCustomerMasterEntityAndCustomerCreditNoteEntity(customerMasterEntity, customerCreditNoteEntity);
		
		creditNoteDetailsRepository.deleteAll(creditNoteDetailsEntityList);
		
		Set<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntities = new HashSet<CustomerCreditNoteDetailsEntity>();
		
		for(CreditNoteDetailsIncomingDto creditNoteDetailsIncomingDto : 
			creditNoteIncomingDto.getCreditNoteDetailsIncomingDto()) {
			
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(creditNoteDetailsIncomingDto.getTax());
			ServiceMasterEntity serviceMasterEntity = serviceMasterDetailsService.getServiceMasterEntityByServiceID(
					creditNoteDetailsIncomingDto.getService());
		
			CustomerCreditNoteDetailsEntity customerCreditNoteDetailsEntity = new CustomerCreditNoteDetailsEntity();
			
			customerCreditNoteDetailsEntity.setIsdeleted("N");
			
			// GST/Customer/Credit Note/ Service Entity
			customerCreditNoteDetailsEntity.setGstMasterEntity(gstMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerMasterEntity(customerMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerCreditNoteEntity(customerCreditNoteEntity);
			customerCreditNoteDetailsEntity.setServiceMasterEntity(serviceMasterEntity);
			
			// Credit Note Service Details
			customerCreditNoteDetailsEntity.setDescription(creditNoteDetailsIncomingDto.getDescription());
			customerCreditNoteDetailsEntity.setRate(creditNoteDetailsIncomingDto.getRate());
			customerCreditNoteDetailsEntity.setQuantity(creditNoteDetailsIncomingDto.getQuantity());
			customerCreditNoteDetailsEntity.setAmount(encoder.encodeToString
					(creditNoteDetailsIncomingDto.getAmount().getBytes(StandardCharsets.UTF_8)));
			customerCreditNoteDetailsEntity.setServiceAmountWithGst(encoder.encodeToString
					(creditNoteDetailsIncomingDto.getServiceAmountWithGst().getBytes(StandardCharsets.UTF_8)));
			
			customerCreditNoteDetailsEntity.setGstAmount(encoder.encodeToString
					(creditNoteDetailsIncomingDto.getGstAmount().getBytes(StandardCharsets.UTF_8)));
			
			customerCreditNoteDetailsEntities.add(customerCreditNoteDetailsEntity);
		}
		creditNoteDetailsRepository.saveAll(customerCreditNoteDetailsEntities);
		
		return CustomerCreditNoteMapper.toCustomerCreditNoteAndCompanyResponseMailDto(customerCreditNoteEntity, companyMasterEntity);
	}

	@Override
	public List<CustomerCreditNoteDetailsEntity> getCreditNoteDetailsListByCustomerIDAndCreditNoteNo(
			CustomerMasterEntity customerMasterEntity, CustomerCreditNoteEntity customerCreditNoteEntity) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl getCustomerCreditNoteDetailsList -------");
		
		return creditNoteDetailsRepository.findByCustomerMasterEntityAndCustomerCreditNoteEntity(customerMasterEntity, customerCreditNoteEntity);
	}

	@Override
	public CustomerCreditNoteEntity getCustomerCreditNoteEntityByCustomerIDAndCreditNoteNo(
			CustomerMasterEntity customerMasterEntity, String creditNoteNo) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl getCustomerCreditNoteEntityByCustomerIDAndCreditNoteNo -------");
		
		return creditNoteRepository.findByCustomerMasterEntityAndCreditNoteNo(customerMasterEntity, creditNoteNo);
	}

	@Override
	public List<CreditNoteResponseDto> getAllCreditNoteList() {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl getAllCreditNoteList -------");
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList = creditNoteRepository.getAllCreditNoteListByStatus();
		
		return CustomerCreditNoteMapper.toCustomerCreditNoteResponseDtos(customerCreditNoteEntitiesList);
	}

	@Override
	public boolean deleteCreditNoteByCustomerIDAndCreditNoteNo(String customerID, String creditNoteNo) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl deleteCreditNoteByCustomerIDAndCreditNoteNo -------");
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerID);
		
		CustomerCreditNoteEntity customerCreditNoteEntity = creditNoteRepository.findByCustomerMasterEntityAndCreditNoteNo
				(customerMasterEntity, creditNoteNo);
		
		if(customerCreditNoteEntity == null) {
			throw BRSException.throwException(EntityType.CREDITNOTE, ExceptionType.ENTITY_NOT_FOUND, customerMasterEntity.getCompanyName());
		}
		
		List<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntity = creditNoteDetailsRepository.
				findByCustomerMasterEntityAndCustomerCreditNoteEntity(customerMasterEntity, customerCreditNoteEntity);
		
		if(customerCreditNoteDetailsEntity.size() == 0) {
			throw BRSException.throwException(EntityType.CREDITNOTESERVICE, ExceptionType.ENTITY_NOT_FOUND, customerCreditNoteEntity.getCreditNoteNo());
		}
		
		for(int i=0; i<customerCreditNoteDetailsEntity.size(); i++) {
			
			customerCreditNoteDetailsEntity.get(i).setIsdeleted("Y");
			creditNoteDetailsRepository.save(customerCreditNoteDetailsEntity.get(i));
		}
		
		customerCreditNoteEntity.setIsdeleted("Y");
		creditNoteRepository.save(customerCreditNoteEntity);
		//creditNoteDetailsRepository.deleteAll(customerCreditNoteDetailsEntity);
		//creditNoteRepository.delete(customerCreditNoteEntity);
		
		return true;
	}

	@Override
	public CreditNoteResponseDto getCreditNoteDetailsByCreditNoteID(String creditNoteID) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl getCreditNoteDetailsByCreditNoteID -------");
		
		CustomerCreditNoteEntity customerCreditNoteEntity = creditNoteRepository.findById(creditNoteID).get();
		List<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntityList = creditNoteDetailsRepository.
				findByCustomerCreditNoteEntity(customerCreditNoteEntity);
		
		return CustomerCreditNoteMapper.toCreditNoteAndServiceResponseDto(customerCreditNoteEntity, customerCreditNoteDetailsEntityList);
	}

	@Override
	public CustomerCreditNoteEntity getCreditNoteEntityByCreditNoteID(String creditNoteID) {
		// TODO Auto-generated method stub
		logger.info("------ CreditNoteServiceImpl getCreditNoteEntityByCreditNoteID -------");
		
		return creditNoteRepository.findById(creditNoteID).get();
	}

}
