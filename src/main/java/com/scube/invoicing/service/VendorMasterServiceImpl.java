package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.VendorMasterResponseDto;
import com.scube.invoicing.dto.incoming.VendorMasterIncomingDto;
import com.scube.invoicing.dto.mapper.VendorMasterMapper;
import com.scube.invoicing.entity.CurrencyMasterEntity;
import com.scube.invoicing.entity.PaymentMethodEntity;
import com.scube.invoicing.entity.VendorMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.VendorMasterRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class VendorMasterServiceImpl implements VendorMasterService {
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	CurrencyMasterService currencyMasterService;
	
	@Autowired
	VendorMasterRepository vendorMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(VendorMasterServiceImpl.class);

	@Override
	public boolean addNewVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl addNewVendor ------");
		
		if(vendorMasterIncomingDto.getVendorFirstName() == "" || 
				vendorMasterIncomingDto.getVendorFirstName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor First Name cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorLastName() == "" || 
				vendorMasterIncomingDto.getVendorLastName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Last Name cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorEmailID() == "" || 
				vendorMasterIncomingDto.getVendorEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Email ID cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorContactNo() == "" || 
				vendorMasterIncomingDto.getVendorContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Contact Details cannot be empty or blank.");
		}
		
		VendorMasterEntity dupliVendorCheckEntity = vendorMasterRepository.findByCompanyName(vendorMasterIncomingDto.getCompanyName());
		
		if(dupliVendorCheckEntity != null) {
			throw BRSException.throwException(EntityType.VENDOR, ExceptionType.ALREADY_EXIST, vendorMasterIncomingDto.getCompanyName());
		}
		
		CurrencyMasterEntity currencyMasterEntity = currencyMasterService.getCurrencyMasterEntityByCurrencyId(vendorMasterIncomingDto.getCurrencyName());
		PaymentMethodEntity paymentMethodEntity = paymentMethodService.getPaymentMethodEntityByPaymentMethodID(vendorMasterIncomingDto.getPrefPaymentMethod());
		
		VendorMasterEntity vendorMasterEntity = new VendorMasterEntity();
		vendorMasterEntity.setIsdeleted("N");
		vendorMasterEntity.setTitle(vendorMasterIncomingDto.getTitle());
		vendorMasterEntity.setVendorFirstName(vendorMasterIncomingDto.getVendorFirstName());
		vendorMasterEntity.setVendorLastName(vendorMasterIncomingDto.getVendorLastName());
		vendorMasterEntity.setMobileNumber(vendorMasterIncomingDto.getVendorContactNo());
		vendorMasterEntity.setEmailId(vendorMasterIncomingDto.getVendorEmailID());
		
		vendorMasterEntity.setCompanyName(vendorMasterIncomingDto.getCompanyName());
		vendorMasterEntity.setFax(vendorMasterIncomingDto.getFax());
		vendorMasterEntity.setWebsite(vendorMasterIncomingDto.getWebsite());
		
		vendorMasterEntity.setBillingAddress(vendorMasterIncomingDto.getBillingAddress());
		vendorMasterEntity.setBillingCity(vendorMasterIncomingDto.getBillingCity());
		vendorMasterEntity.setBillingCountry(vendorMasterIncomingDto.getBillingCountry());
		vendorMasterEntity.setBillingState(vendorMasterIncomingDto.getBillingState());
		vendorMasterEntity.setBillingPinCode(vendorMasterIncomingDto.getBillingPinCode());
		
		vendorMasterEntity.setShippingAddress(vendorMasterIncomingDto.getShippingAddress());
		vendorMasterEntity.setShippingCity(vendorMasterIncomingDto.getShippingCity());
		vendorMasterEntity.setShippingCountry(vendorMasterIncomingDto.getShippingCountry());
		vendorMasterEntity.setShippingState(vendorMasterIncomingDto.getShippingState());
		vendorMasterEntity.setShippingPinCode(vendorMasterIncomingDto.getShippingPinCode());
		
		vendorMasterEntity.setGstRegistrationNo(vendorMasterIncomingDto.getGstRegistrationNo());
		vendorMasterEntity.setGstin(vendorMasterIncomingDto.getGstin());
		vendorMasterEntity.setTaxRegistrationNo(vendorMasterIncomingDto.getTaxRegistrationNo());
		vendorMasterEntity.setCstRegistrationNo(vendorMasterIncomingDto.getCstRegistrationNo());
		vendorMasterEntity.setPanNo(vendorMasterIncomingDto.getPanNo());

		vendorMasterEntity.setPrefDelieveryMethod(vendorMasterIncomingDto.getPrefDelieveryMethod());
		vendorMasterEntity.setPaymentDate(DateUtils.stringToDateConvert(vendorMasterIncomingDto.getPaymentDate()));
		vendorMasterEntity.setPaymentTerms(vendorMasterIncomingDto.getPaymentTerms());
		vendorMasterEntity.setOpeningBalance(vendorMasterIncomingDto.getOpeningBalance());
		vendorMasterEntity.setPaysWith(vendorMasterIncomingDto.getPaysWith());
		
		vendorMasterEntity.setPaymentMethodEntity(paymentMethodEntity);
		vendorMasterEntity.setCurrencyMasterEntity(currencyMasterEntity);

		vendorMasterRepository.save(vendorMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl updateVendor ------");
		
		if(vendorMasterIncomingDto.getVendorFirstName() == "" || 
				vendorMasterIncomingDto.getVendorFirstName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor First Name cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorLastName() == "" || 
				vendorMasterIncomingDto.getVendorLastName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Last Name cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorContactNo() == "" || 
				vendorMasterIncomingDto.getVendorContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Contact Details cannot be empty or blank.");
		}
		
		if(vendorMasterIncomingDto.getVendorEmailID() == "" || 
				vendorMasterIncomingDto.getVendorEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Email ID cannot be empty or blank.");
		}
		
		CurrencyMasterEntity currencyMasterEntity = currencyMasterService.getCurrencyMasterEntityByCurrencyId(vendorMasterIncomingDto.getCurrencyName());
		PaymentMethodEntity paymentMethodEntity = paymentMethodService.getPaymentMethodEntityByPaymentMethodID(vendorMasterIncomingDto.getPrefPaymentMethod());
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorMasterIncomingDto.getVendorID()).get();
		vendorMasterEntity.setIsdeleted("N");
		vendorMasterEntity.setTitle(vendorMasterIncomingDto.getTitle());
		vendorMasterEntity.setVendorFirstName(vendorMasterIncomingDto.getVendorFirstName());
		vendorMasterEntity.setVendorLastName(vendorMasterIncomingDto.getVendorLastName());
		vendorMasterEntity.setMobileNumber(vendorMasterIncomingDto.getVendorContactNo());
		vendorMasterEntity.setEmailId(vendorMasterIncomingDto.getVendorEmailID());
		
		vendorMasterEntity.setCompanyName(vendorMasterIncomingDto.getCompanyName());
		vendorMasterEntity.setFax(vendorMasterIncomingDto.getFax());
		vendorMasterEntity.setWebsite(vendorMasterIncomingDto.getWebsite());
		
		vendorMasterEntity.setBillingAddress(vendorMasterIncomingDto.getBillingAddress());
		vendorMasterEntity.setBillingCity(vendorMasterIncomingDto.getBillingCity());
		vendorMasterEntity.setBillingCountry(vendorMasterIncomingDto.getBillingCountry());
		vendorMasterEntity.setBillingState(vendorMasterIncomingDto.getBillingState());
		vendorMasterEntity.setBillingPinCode(vendorMasterIncomingDto.getBillingPinCode());
		
		vendorMasterEntity.setShippingAddress(vendorMasterIncomingDto.getShippingAddress());
		vendorMasterEntity.setShippingCity(vendorMasterIncomingDto.getShippingCity());
		vendorMasterEntity.setShippingCountry(vendorMasterIncomingDto.getShippingCountry());
		vendorMasterEntity.setShippingState(vendorMasterIncomingDto.getShippingState());
		vendorMasterEntity.setShippingPinCode(vendorMasterIncomingDto.getShippingPinCode());
		
		vendorMasterEntity.setGstRegistrationNo(vendorMasterIncomingDto.getGstRegistrationNo());
		vendorMasterEntity.setGstin(vendorMasterIncomingDto.getGstin());
		vendorMasterEntity.setTaxRegistrationNo(vendorMasterIncomingDto.getTaxRegistrationNo());
		vendorMasterEntity.setCstRegistrationNo(vendorMasterIncomingDto.getCstRegistrationNo());
		vendorMasterEntity.setPanNo(vendorMasterIncomingDto.getPanNo());

		vendorMasterEntity.setPrefDelieveryMethod(vendorMasterIncomingDto.getPrefDelieveryMethod());
		vendorMasterEntity.setPaymentDate(DateUtils.stringToDateConvert(vendorMasterIncomingDto.getPaymentDate()));
		vendorMasterEntity.setPaymentTerms(vendorMasterIncomingDto.getPaymentTerms());
		vendorMasterEntity.setOpeningBalance(vendorMasterIncomingDto.getOpeningBalance());
		vendorMasterEntity.setPaysWith(vendorMasterIncomingDto.getPaysWith());
		
		vendorMasterEntity.setPaymentMethodEntity(paymentMethodEntity);
		vendorMasterEntity.setCurrencyMasterEntity(currencyMasterEntity);
		
		vendorMasterRepository.save(vendorMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteVendorByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl deleteVendorByVendorID ------");
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorID).get();
		
		vendorMasterEntity.setIsdeleted("Y");
		vendorMasterRepository.save(vendorMasterEntity);
		//vendorMasterRepository.delete(vendorMasterEntity);
		
		return true;
	}

	@Override
	public VendorMasterResponseDto getVendorInfoByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl getVendorInfoByVendorID ------");
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorID).get();
		
		return VendorMasterMapper.toVendorMasterResponseDto(vendorMasterEntity);
	}

	@Override
	public List<VendorMasterResponseDto> getAllVendorList() {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl getAllVendorList ------");
		
		List<VendorMasterEntity> vendorMasterEntitiesList = vendorMasterRepository.getAllVendorListByStatus();
		
		return VendorMasterMapper.toVendorMasterResponseDtosList(vendorMasterEntitiesList);
	}

	@Override
	public VendorMasterEntity getVendorMasterEntityByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		return vendorMasterRepository.findById(vendorID).get();
	}

}
