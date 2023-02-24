package com.scube.invoicing.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CreditNoteDetailsRepository;
import com.scube.invoicing.repository.CreditNoteRepository;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class CreditNoteServiceImpl implements CreditNoteService{
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@Autowired
	GSTMasterService gstMasterService;
	
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
	
	private static final Logger logger = LoggerFactory.getLogger(CreditNoteServiceImpl.class);

	@Override
	public boolean addCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("------ CreditNoteServiceImpl addCreditNoteAndService -------");
		
		if(creditNoteIncomingDto.getCustomerID() == "" || 
				creditNoteIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyID("a03964deb2");
		
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
		
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(1000);
		
		String generatedCreditNoteNo = "# CN-000"+number;
		logger.info("---- Credit Note No ----- " + generatedCreditNoteNo);
		
		CustomerCreditNoteEntity customerCreditNoteEntity = new CustomerCreditNoteEntity();
		
		customerCreditNoteEntity.setIsdeleted("N");
		customerCreditNoteEntity.setCustomerMasterEntity(customerMasterEntity);
		customerCreditNoteEntity.setCgstAmount(creditNoteIncomingDto.getCgstAmount());
		customerCreditNoteEntity.setSgstAmount(creditNoteIncomingDto.getSgstAmount());
		customerCreditNoteEntity.setTotalAmount(creditNoteIncomingDto.getTotalAmount());
		customerCreditNoteEntity.setSubTotal(creditNoteIncomingDto.getSubTotal());
		customerCreditNoteEntity.setCreditsRemaining(creditNoteIncomingDto.getCreditsRemaining());
		customerCreditNoteEntity.setCreditNoteNo(generatedCreditNoteNo);
		customerCreditNoteEntity.setCreditNoteDate(DateUtils.stringToDateConvert(creditNoteIncomingDto.getCreditNoteDate()));
		customerCreditNoteEntity.setCustomerInvoiceEntity(customerInvoiceSet);
		
		creditNoteRepository.save(customerCreditNoteEntity);
		
		Set<CustomerCreditNoteDetailsEntity> customerCreditNoteDetailsEntities = new HashSet<CustomerCreditNoteDetailsEntity>();
		
		for(CreditNoteDetailsIncomingDto creditNoteDetailsIncomingDto : 
			creditNoteIncomingDto.getCreditNoteDetailsIncomingDto()) {
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(creditNoteDetailsIncomingDto.getTax());
			
			if(gstMasterEntity == null) {
				throw BRSException.throwException("Error : NO GST Tax Details Found");
			}
			
			CustomerCreditNoteDetailsEntity customerCreditNoteDetailsEntity = new CustomerCreditNoteDetailsEntity();
			
			customerCreditNoteDetailsEntity.setIsdeleted("N");
			customerCreditNoteDetailsEntity.setGstMasterEntity(gstMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerMasterEntity(customerMasterEntity);
			customerCreditNoteDetailsEntity.setCustomerCreditNoteEntity(customerCreditNoteEntity);
			customerCreditNoteDetailsEntity.setItemDescription(creditNoteDetailsIncomingDto.getItemsAndDescription());
			customerCreditNoteDetailsEntity.setAmount(creditNoteDetailsIncomingDto.getAmount());
			customerCreditNoteDetailsEntity.setRate(creditNoteDetailsIncomingDto.getRate());
			customerCreditNoteDetailsEntity.setAmount(creditNoteDetailsIncomingDto.getAmount());
			customerCreditNoteDetailsEntity.setQuantity(creditNoteDetailsIncomingDto.getQuantity());
			
			customerCreditNoteDetailsEntities.add(customerCreditNoteDetailsEntity);
		}
		creditNoteDetailsRepository.saveAll(customerCreditNoteDetailsEntities);
		
		return true;
	}

	@Override
	public boolean updateCreditNoteAndService(@Valid CreditNoteIncomingDto creditNoteIncomingDto) {
		// TODO Auto-generated method stub
		return false;
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
		return creditNoteRepository.findByCustomerMasterEntityAndCreditNoteNo(customerMasterEntity, creditNoteNo);
	}

	@Override
	public List<CreditNoteResponseDto> getAllCreditNoteList() {
		// TODO Auto-generated method stub
		
		logger.info("------ CreditNoteServiceImpl getAllCreditNoteList -------");
		
		List<CustomerCreditNoteEntity> customerCreditNoteEntitiesList = creditNoteRepository.findAll();
		
		return CustomerCreditNoteMapper.toCustomerInvoiceResponseDtosList(customerCreditNoteEntitiesList);
	}

}
