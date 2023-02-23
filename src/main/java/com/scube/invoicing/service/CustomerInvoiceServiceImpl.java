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

import com.scube.invoicing.dto.CustomerServiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceServiceIncomingDto;
import com.scube.invoicing.dto.incoming.CustomerServiceIncomingDto;
import com.scube.invoicing.dto.mapper.CustomerInvoiceMapper;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.repository.CustomerInvoiceServiceRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {
	
	@Autowired
	CustomerMasterService customerMasterService;
	
	@Autowired
	GSTMasterService gstMasterService;
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@Autowired
	CustomerInvoiceServiceRepository customerInvoiceServiceRepository;
	
	@Autowired
	CustomerInvoiceRepository customerInvoiceRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerInvoiceServiceImpl.class);

	@Override
	public CustomerServiceResponseDto addCustomerInvoiceAndServiceData(@Valid CustomerServiceIncomingDto customerServiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl addCustomerInvoiceAndServiceData ----");
		
		if(customerServiceIncomingDto.getCustomerID() == "" || 
				customerServiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyID("a0976ca4c2");
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : NO Company Details Found");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(customerServiceIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Details Found");
		}
		
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(1000);
		
		CustomerInvoiceEntity customerInvoiceEntity = new CustomerInvoiceEntity();
		
		customerInvoiceEntity.setIsdeleted("N");
		
		customerInvoiceEntity.setCustomerMasterEntity(customerMasterEntity);
		
		customerInvoiceEntity.setCustEmailId(customerServiceIncomingDto.getCustEmailId());
		customerInvoiceEntity.setCustomerBillingAddress(customerServiceIncomingDto.getCustomerBillingAddress());
		customerInvoiceEntity.setShippingDate(DateUtils.stringToDateConvert(customerServiceIncomingDto.getShippingDate()));
		customerInvoiceEntity.setShippingTo(customerServiceIncomingDto.getShippingTo());
		customerInvoiceEntity.setShippingVia(customerServiceIncomingDto.getShippingVia());
		customerInvoiceEntity.setTerms(customerServiceIncomingDto.getTerms());
		
		customerInvoiceEntity.setBalance(customerServiceIncomingDto.getBalance());
		customerInvoiceEntity.setCgstAmount(customerServiceIncomingDto.getCgstAmount());
		customerInvoiceEntity.setSgstAmount(customerServiceIncomingDto.getSgstAmount());
		customerInvoiceEntity.setDeposit(customerServiceIncomingDto.getDeposit());
		customerInvoiceEntity.setDiscounts(customerServiceIncomingDto.getDiscounts());
		customerInvoiceEntity.setTotalAmount(customerServiceIncomingDto.getTotalAmount());
		customerInvoiceEntity.setSubTotal(customerServiceIncomingDto.getSubTotal());
		
		customerInvoiceEntity.setMessageInvoice(customerServiceIncomingDto.getMessageInvoice());
		customerInvoiceEntity.setMessageStatement(customerServiceIncomingDto.getMessageStatement());
		
		customerInvoiceEntity.setDueDate(DateUtils.stringToDateConvert(customerServiceIncomingDto.getDueDate()));
		customerInvoiceEntity.setTrackingNo(customerServiceIncomingDto.getTrackingNo());
		
		customerInvoiceEntity.setInvoiceNo("INVOICE-000"+number);
		customerInvoiceEntity.setInvoiceDate(DateUtils.stringToDateConvert(customerServiceIncomingDto.getInvoiceDate()));
		
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		Set<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = new HashSet<CustomerInvoiceServiceEntity>();
		
		for(CustomerInvoiceServiceIncomingDto customerInvoiceServiceIncomingDto : 
			customerServiceIncomingDto.getCustomerInvoiceServiceDtos()) {
			
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(customerInvoiceServiceIncomingDto.getGstID());
			
			if(gstMasterEntity == null) {
				throw BRSException.throwException("Error : NO GST Tax Details Found");
			}
			
			CustomerInvoiceServiceEntity customerInvoiceServiceEntity = new CustomerInvoiceServiceEntity();
			
			customerInvoiceServiceEntity.setIsdeleted("N");
			
			customerInvoiceServiceEntity.setCustomerMasterEntity(customerMasterEntity);
			customerInvoiceServiceEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
			customerInvoiceServiceEntity.setGstMasterEntity(gstMasterEntity);
			
			customerInvoiceServiceEntity.setAmount(customerInvoiceServiceIncomingDto.getAmount());
			customerInvoiceServiceEntity.setDescription(customerInvoiceServiceIncomingDto.getDescription());
			customerInvoiceServiceEntity.setHsnorSac(customerInvoiceServiceIncomingDto.getHsnOrSac());
			customerInvoiceServiceEntity.setProductService(customerInvoiceServiceIncomingDto.getProductService());
			customerInvoiceServiceEntity.setQuantity(customerInvoiceServiceIncomingDto.getQuantity());
			customerInvoiceServiceEntity.setRate(customerInvoiceServiceIncomingDto.getRate());
			customerInvoiceServiceEntity.setSku(customerInvoiceServiceIncomingDto.getSku());
			
			customerInvoiceServiceEntityList.add(customerInvoiceServiceEntity);
			
		}
		customerInvoiceServiceRepository.saveAll(customerInvoiceServiceEntityList);
		
		return CustomerInvoiceMapper.toCustomerServiceAndCompanyResponseDto(customerInvoiceEntity, companyMasterEntity);
	}

	@Override
	public boolean updateCustomerServiceInfo(@Valid CustomerServiceIncomingDto customerServiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl updateCustomerServiceInfo ----");
		
		return false;
	}

	@Override
	public boolean removeCustomerInvoiceAndServiceData(String customerID, String invoiceNo) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl removeCustomerInvoiceAndServiceData ----");
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerID);
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : No Customer details found.");
		}
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findByCustomerMasterEntityAndInvoiceNo(
				customerMasterEntity, invoiceNo);
		
		if(customerInvoiceEntity == null) {
			throw BRSException.throwException("Error : No Invoice details found for " + customerMasterEntity.getCompanyName());
		}
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceList = customerInvoiceServiceRepository.
				findByCustomerInvoiceEntityAndCustomerMasterEntity(customerInvoiceEntity, customerMasterEntity);
		
		if(customerInvoiceServiceList.size() == 0) {
			throw BRSException.throwException("Error : No Customer Service details found for " + customerInvoiceEntity.getInvoiceNo());
		}
		
		customerInvoiceServiceRepository.deleteAll(customerInvoiceServiceList);
		customerInvoiceRepository.delete(customerInvoiceEntity);
		
		return true;
	}

	@Override
	public CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, 
			String invoiceNo) {
		// TODO Auto-generated method stub
		return customerInvoiceRepository.findByCustomerMasterEntityAndInvoiceNo(customerMasterEntity, invoiceNo);
	}

	@Override
	public List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails (
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl removeCustomerInvoiceAndServiceData ----");
		
		return customerInvoiceServiceRepository.findByCustomerInvoiceEntityAndCustomerMasterEntity(customerInvoiceEntity, customerMasterEntity);
	}

	@Override
	public List<CustomerServiceResponseDto> getAllCustomerInvoiceAndServiceList() {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl getAllCustomerInvoiceAndServiceList ----");
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.findAll();
		
		return CustomerInvoiceMapper.toCustomerInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	@Override
	public boolean updateCustomerInvoicePaymentStatus(@Valid CustomerServiceIncomingDto customerServiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl updateCustomerInvoicePaymentStatus ----");
		
		if(customerServiceIncomingDto.getCustomerID() == "" || customerServiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be empty or blank");
		}
		
		if(customerServiceIncomingDto.getInvoiceNo() == "" || customerServiceIncomingDto.getInvoiceNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer Invoice No cannot be empty or blank");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerServiceIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Records found.");
		}
		
		CustomerInvoiceEntity customerInvoiceEntity = getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(customerMasterEntity, 
				customerServiceIncomingDto.getInvoiceNo());
		
		if(customerInvoiceEntity == null)  {
			throw BRSException.throwException(EntityType.INVOICE, ExceptionType.ENTITY_NOT_FOUND, customerServiceIncomingDto.getInvoiceNo());
		}
		
		customerInvoiceEntity.setPaymentStatus("Payment Completed");
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		return true;
	}

	@Override
	public List<CustomerServiceResponseDto> getCustomerInvoiceListByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl getCustomerInvoiceListByCustomerID ----");
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerID);
		
		List<CustomerInvoiceEntity> customerInvoiceEntityList = customerInvoiceRepository.findByCustomerMasterEntity(customerMasterEntity);
		
		return CustomerInvoiceMapper.toCustomerInvoiceResponseDtosList(customerInvoiceEntityList);
	}

}
