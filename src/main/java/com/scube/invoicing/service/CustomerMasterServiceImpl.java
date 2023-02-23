package com.scube.invoicing.service;

import static com.scube.invoicing.exception.ExceptionType.ALREADY_EXIST;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CustomerMasterResponseDto;
import com.scube.invoicing.dto.incoming.CustomerMasterIncomingDto;
import com.scube.invoicing.dto.mapper.CustomerMasterMapper;
import com.scube.invoicing.entity.CurrencyMasterEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.repository.CustomerMasterRepository;
import com.scube.invoicing.util.DateUtils;


@Service
public class CustomerMasterServiceImpl implements CustomerMasterService {
	
	@Autowired
	CustomerMasterRepository customerMasterRepository;
	
	@Autowired
	CurrencyMasterService currencyMasterService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerMasterServiceImpl.class);

	@Override
	public boolean addCustomerInfoDetails(@Valid CustomerMasterIncomingDto customerMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("-------- CustomerMasterServiceImpl addCustomerInfoDetails ------");
		
		if(customerMasterIncomingDto.getFirstName() == "" || customerMasterIncomingDto.getFirstName().trim().isEmpty()) {
			throw BRSException.throwException("Error : First Name cannot be empty");
		}
		
		if(customerMasterIncomingDto.getLastName() == "" || customerMasterIncomingDto.getLastName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Last Name cannot be empty");
		}
		
		CustomerMasterEntity duplicateCompanyNameCheckEntity = customerMasterRepository.findByCompanyName(customerMasterIncomingDto.getCompanyName());
		
		if(duplicateCompanyNameCheckEntity != null) {
			throw BRSException.throwException(EntityType.COMPANY, ALREADY_EXIST, customerMasterIncomingDto.getCompanyName());
		}
		
		CustomerMasterEntity duplicatePanNoCheckEntity = customerMasterRepository.findByPanNo(customerMasterIncomingDto.getPanNo());
		
		if(duplicatePanNoCheckEntity != null) {
			throw BRSException.throwException(EntityType.PANNO, ALREADY_EXIST, customerMasterIncomingDto.getPanNo());
		}
		
		CustomerMasterEntity checkDuplicateGstNoEntity = customerMasterRepository.findByGstRegistrationNo(customerMasterIncomingDto.getGstRegistrationNo());
		
		if(checkDuplicateGstNoEntity != null) {
			throw BRSException.throwException(EntityType.GSTNO, ALREADY_EXIST, customerMasterIncomingDto.getGstRegistrationNo());
		}
		
		CurrencyMasterEntity currencyMasterEntity = currencyMasterService.getCurrencyMasterEntityByCurrencyId(customerMasterIncomingDto.getCurrencyName());
		
		CustomerMasterEntity customerMasterEntity = new CustomerMasterEntity();
		
		customerMasterEntity.setTitle(customerMasterIncomingDto.getTitle());
		customerMasterEntity.setFirstName(customerMasterIncomingDto.getFirstName());
		customerMasterEntity.setLastName(customerMasterIncomingDto.getLastName());
		
		customerMasterEntity.setCompanyName(customerMasterIncomingDto.getCompanyName());
		customerMasterEntity.setEmailId(customerMasterIncomingDto.getEmailId());
		customerMasterEntity.setMobileNumber(customerMasterIncomingDto.getMobileNumber());
		customerMasterEntity.setFax(customerMasterIncomingDto.getFax());
		customerMasterEntity.setWebsite(customerMasterIncomingDto.getWebsite());
		
		customerMasterEntity.setBillingAddress(customerMasterIncomingDto.getBillingAddress());
		customerMasterEntity.setBillingCity(customerMasterIncomingDto.getBillingCity());
		customerMasterEntity.setBillingState(customerMasterIncomingDto.getBillingState());
		customerMasterEntity.setBillingCountry(customerMasterIncomingDto.getBillingCountry());
		customerMasterEntity.setBillingPinCode(customerMasterIncomingDto.getBillingPinCode());
		
		customerMasterEntity.setShippingAddress(customerMasterIncomingDto.getShippingAddress());
		customerMasterEntity.setShippingCity(customerMasterIncomingDto.getShippingCity());
		customerMasterEntity.setShippingState(customerMasterIncomingDto.getShippingState());
		customerMasterEntity.setShippingCountry(customerMasterIncomingDto.getShippingCountry());
		customerMasterEntity.setShippingPinCode(customerMasterIncomingDto.getShippingPinCode());
		
		customerMasterEntity.setGstRegistrationNo(customerMasterIncomingDto.getGstRegistrationNo());
		customerMasterEntity.setGstin(customerMasterIncomingDto.getGstin());
		customerMasterEntity.setTaxRegistrationNo(customerMasterIncomingDto.getTaxRegistrationNo());
		customerMasterEntity.setCstRegistrationNo(customerMasterIncomingDto.getCstRegistrationNo());
		customerMasterEntity.setPanNo(customerMasterIncomingDto.getPanNo());
		
		customerMasterEntity.setPrefPaymentMethod(customerMasterIncomingDto.getPrefPaymentMethod());
		customerMasterEntity.setPrefDelieveryMethod(customerMasterIncomingDto.getPrefDelieveryMethod());
		customerMasterEntity.setPaymentTerms(customerMasterIncomingDto.getPaymentTerms());
		customerMasterEntity.setOpeningBalance(customerMasterIncomingDto.getOpeningBalance());
		customerMasterEntity.setPaymentDate(DateUtils.stringToDateConvert(customerMasterIncomingDto.getPaymentDate()));
		customerMasterEntity.setPaysWith(customerMasterIncomingDto.getPaysWith());
		
		customerMasterEntity.setIsdeleted("N");
		
		customerMasterEntity.setCurrencyMasterEntity(currencyMasterEntity);
		
		customerMasterRepository.save(customerMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateCustomerInfoDetails(@Valid CustomerMasterIncomingDto customerMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("-------- CustomerMasterServiceImpl updateCustomerInfoDetails ------");
		
		if(customerMasterIncomingDto.getFirstName() == "" || customerMasterIncomingDto.getFirstName().trim().isEmpty()) {
			throw BRSException.throwException("Error : First Name cannot be empty");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterRepository.findById(customerMasterIncomingDto.getCustomerId()).get();
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : No Customer Records Found ");
		}
		
		CurrencyMasterEntity currencyMasterEntity = currencyMasterService.getCurrencyMasterEntityByCurrencyId(customerMasterIncomingDto.getCurrencyName());
		
		customerMasterEntity.setTitle(customerMasterIncomingDto.getTitle());
		customerMasterEntity.setFirstName(customerMasterIncomingDto.getFirstName());
		customerMasterEntity.setLastName(customerMasterIncomingDto.getLastName());
		
		customerMasterEntity.setCompanyName(customerMasterIncomingDto.getCompanyName());
		customerMasterEntity.setEmailId(customerMasterIncomingDto.getEmailId());
		customerMasterEntity.setMobileNumber(customerMasterIncomingDto.getMobileNumber());
		customerMasterEntity.setFax(customerMasterIncomingDto.getFax());
		customerMasterEntity.setWebsite(customerMasterIncomingDto.getWebsite());
		
		customerMasterEntity.setBillingAddress(customerMasterIncomingDto.getBillingAddress());
		customerMasterEntity.setBillingCity(customerMasterIncomingDto.getBillingCity());
		customerMasterEntity.setBillingState(customerMasterIncomingDto.getBillingState());
		customerMasterEntity.setBillingCountry(customerMasterIncomingDto.getBillingCountry());
		customerMasterEntity.setBillingPinCode(customerMasterIncomingDto.getBillingPinCode());
		
		customerMasterEntity.setShippingAddress(customerMasterIncomingDto.getShippingAddress());
		customerMasterEntity.setShippingCity(customerMasterIncomingDto.getShippingCity());
		customerMasterEntity.setShippingState(customerMasterIncomingDto.getShippingState());
		customerMasterEntity.setShippingCountry(customerMasterIncomingDto.getShippingCountry());
		customerMasterEntity.setShippingPinCode(customerMasterIncomingDto.getShippingPinCode());
		
		customerMasterEntity.setGstRegistrationNo(customerMasterIncomingDto.getGstRegistrationNo());
		customerMasterEntity.setGstin(customerMasterIncomingDto.getGstin());
		customerMasterEntity.setTaxRegistrationNo(customerMasterIncomingDto.getTaxRegistrationNo());
		customerMasterEntity.setCstRegistrationNo(customerMasterIncomingDto.getCstRegistrationNo());
		customerMasterEntity.setPanNo(customerMasterIncomingDto.getPanNo());
		
		customerMasterEntity.setPrefPaymentMethod(customerMasterIncomingDto.getPrefPaymentMethod());
		customerMasterEntity.setPrefDelieveryMethod(customerMasterIncomingDto.getPrefDelieveryMethod());
		customerMasterEntity.setPaymentTerms(customerMasterIncomingDto.getPaymentTerms());
		customerMasterEntity.setOpeningBalance(customerMasterIncomingDto.getOpeningBalance());
		customerMasterEntity.setPaymentDate(DateUtils.stringToDateConvert(customerMasterIncomingDto.getPaymentDate()));
		customerMasterEntity.setPaysWith(customerMasterIncomingDto.getPaysWith());
		
		customerMasterEntity.setIsdeleted("N");
		
		customerMasterEntity.setCurrencyMasterEntity(currencyMasterEntity);
		
		customerMasterRepository.save(customerMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteCustomerInfoDetailsByCustomerId(String customerID) {
		// TODO Auto-generated method stub
		
		logger.info("-------- CustomerMasterServiceImpl deleteCustomerInfoDetailsByCustomerId ------");
		
		logger.info("Customer ID to be Deleted :-- " + customerID);
		
		CustomerMasterEntity customerMasterEntity = customerMasterRepository.findById(customerID).get();
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : No Customer Records Found ");
		}
		
		customerMasterRepository.delete(customerMasterEntity);
		
		logger.info("--- Record Deleted Successfully ----");
		
		return true;
	}

	@Override
	public CustomerMasterResponseDto getCustomerInfoDetailsByCustomerId(String customerID) {
		// TODO Auto-generated method stub
		
		logger.info("-------- CustomerMasterServiceImpl getCustomerInfoDetailsByCustomerId ------");
		
		logger.info("Customer ID Records to be shown :-- " + customerID);
		
		CustomerMasterEntity customerMasterEntity = customerMasterRepository.findById(customerID).get();
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : No Customer Records Found ");
		}
		
		return CustomerMasterMapper.toCustomerMasterResponseDto(customerMasterEntity);
	}

	@Override
	public List<CustomerMasterResponseDto> getAllCustomerInfoDetails() {
		// TODO Auto-generated method stub
		
		logger.info("-------- CustomerMasterServiceImpl getAllCustomerInfoDetails ------");
		
		List<CustomerMasterEntity> customerMasterEntityList = customerMasterRepository.findAll();
		
		if(customerMasterEntityList == null) {
			throw BRSException.throwException("Error : No Customer Records Found ");
		}
		
		return CustomerMasterMapper.toAllCustomerDataList(customerMasterEntityList);
	}

	@Override
	public CustomerMasterEntity getCustomerDetailsByCustomerId(String customerID) {
		
		return customerMasterRepository.findById(customerID).get();
	}

}
