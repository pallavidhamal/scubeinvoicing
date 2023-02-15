package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CompanyMasterResponseDto;
import com.scube.invoicing.dto.incoming.CompanyMasterIncomingDto;
import com.scube.invoicing.dto.mapper.CompanyMasterMapper;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CompanyMasterRepository;


@Service
public class CompanyMasterServiceImpl implements CompanyMasterService {
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyMasterServiceImpl.class);

	@Override
	public boolean addCompanyDetails(@Valid CompanyMasterIncomingDto companyMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl addCompanyDetails -----");
		
		if(companyMasterIncomingDto.getCompanyName() == "" || companyMasterIncomingDto.getCompanyName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Name cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getEmailId() == "" || companyMasterIncomingDto.getEmailId().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Email ID cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getContactNo() == "" || companyMasterIncomingDto.getContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Contact No cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyWebsite() == "" || companyMasterIncomingDto.getCompanyWebsite().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Website cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyPanNo() == "" || companyMasterIncomingDto.getCompanyPanNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company PAN No cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyAddress() == "" || companyMasterIncomingDto.getCompanyAddress().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Address cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyGstin() == "" || companyMasterIncomingDto.getCompanyGstin().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company GSTIN cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyCinNo() == "" || companyMasterIncomingDto.getCompanyCinNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company CIN No cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyAccountantEmailId() == "" || companyMasterIncomingDto.getCompanyAccountantEmailId().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Accountant Email ID cannot be blank or empty");
		}
		
		CompanyMasterEntity companyMasterEntity = new CompanyMasterEntity();
		
		companyMasterEntity.setIsdeleted("N");
		
		companyMasterEntity.setName(companyMasterIncomingDto.getName());
		companyMasterEntity.setEmailId(companyMasterIncomingDto.getEmailId());
		companyMasterEntity.setContactNo(companyMasterIncomingDto.getContactNo());
		
		companyMasterEntity.setCompanyName(companyMasterIncomingDto.getCompanyName());
		companyMasterEntity.setCompanyAddress(companyMasterIncomingDto.getCompanyAddress());
		companyMasterEntity.setCompanyWebsite(companyMasterIncomingDto.getCompanyWebsite());
		companyMasterEntity.setCompanyGstin(companyMasterIncomingDto.getCompanyGstin());
		companyMasterEntity.setCompanyPanNo(companyMasterIncomingDto.getCompanyPanNo());
		companyMasterEntity.setCompanyCinNo(companyMasterIncomingDto.getCompanyCinNo());
		
		companyMasterEntity.setCompanyFinancialAccountant(companyMasterIncomingDto.getCompanyAccountantEmailId());
		
		companyMasterEntity.setBankName(companyMasterIncomingDto.getBankName());
		companyMasterEntity.setBankAddress(companyMasterIncomingDto.getBankAddress());
		companyMasterEntity.setCompanyBankAccNo(companyMasterIncomingDto.getBankAccountNo());
		companyMasterEntity.setBeneficiary(companyMasterIncomingDto.getBeneficiary());
		companyMasterEntity.setSwiftCode(companyMasterIncomingDto.getSwiftCode());
		
		companyMasterRepository.save(companyMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateCompanyDetails(@Valid CompanyMasterIncomingDto companyMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl updateCompanyDetails -----");
		
		if(companyMasterIncomingDto.getCompanyName() == "" || companyMasterIncomingDto.getCompanyName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Name cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getEmailId() == "" || companyMasterIncomingDto.getEmailId().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Email ID cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getContactNo() == "" || companyMasterIncomingDto.getContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Contact No cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyWebsite() == "" || companyMasterIncomingDto.getCompanyWebsite().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Website cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyPanNo() == "" || companyMasterIncomingDto.getCompanyPanNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company PAN No cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyAddress() == "" || companyMasterIncomingDto.getCompanyAddress().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company Address cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyGstin() == "" || companyMasterIncomingDto.getCompanyGstin().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company GSTIN cannot be blank or empty");
		}
		
		if(companyMasterIncomingDto.getCompanyCinNo() == "" || companyMasterIncomingDto.getCompanyCinNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Company CIN No cannot be blank or empty");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterRepository.findById(companyMasterIncomingDto.getCompanyID()).get();
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : No Company Details found.");
		}
		
		companyMasterEntity.setIsdeleted("N");
		
		companyMasterEntity.setName(companyMasterIncomingDto.getName());
		companyMasterEntity.setEmailId(companyMasterIncomingDto.getEmailId());
		companyMasterEntity.setContactNo(companyMasterIncomingDto.getContactNo());
		
		companyMasterEntity.setCompanyName(companyMasterIncomingDto.getCompanyName());
		companyMasterEntity.setCompanyAddress(companyMasterIncomingDto.getCompanyAddress());
		companyMasterEntity.setCompanyWebsite(companyMasterIncomingDto.getCompanyWebsite());
		companyMasterEntity.setCompanyGstin(companyMasterIncomingDto.getCompanyGstin());
		companyMasterEntity.setCompanyPanNo(companyMasterIncomingDto.getCompanyPanNo());
		companyMasterEntity.setCompanyCinNo(companyMasterIncomingDto.getCompanyCinNo());
		
		companyMasterEntity.setCompanyFinancialAccountant(companyMasterIncomingDto.getCompanyAccountantEmailId());
		
		companyMasterEntity.setBankName(companyMasterIncomingDto.getBankName());
		companyMasterEntity.setBankAddress(companyMasterIncomingDto.getBankAddress());
		companyMasterEntity.setCompanyBankAccNo(companyMasterIncomingDto.getBankAccountNo());
		companyMasterEntity.setBeneficiary(companyMasterIncomingDto.getBeneficiary());
		companyMasterEntity.setSwiftCode(companyMasterIncomingDto.getSwiftCode());
		
		companyMasterRepository.save(companyMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteCompanyDetailsByCompanyId(String companyID) {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl deleteCompanyDetailsByCompanyId -----");
		
		CompanyMasterEntity companyMasterEntity = companyMasterRepository.findById(companyID).get();
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : No Company Details found.");
		}
		
		companyMasterRepository.delete(companyMasterEntity);
		
		return true;
	}

	@Override
	public CompanyMasterResponseDto getCompanyDetailsByCompanyId(String companyID) {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl getCompanyDetailsByCompanyId -----");
		
		CompanyMasterEntity companyMasterEntity = companyMasterRepository.findById(companyID).get();
		
		if(companyMasterEntity == null) {
			throw BRSException.throwException("Error : No Company Details found.");
		}
		
		return CompanyMasterMapper.toCompanyMasterResponseDto(companyMasterEntity);
	}

	@Override
	public List<CompanyMasterResponseDto> getAllCompanyDetails() {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl getAllCompanyDetails -----");
		
		List<CompanyMasterEntity> companyMasterEntityList = companyMasterRepository.findAll();
		
		if(companyMasterEntityList.size() == 0) {
			throw BRSException.throwException("Error : No Company Details found.");
		}
		
		return CompanyMasterMapper.toCompanyMasterResponseDtosList(companyMasterEntityList);
	}

	@Override
	public CompanyMasterEntity getCompanyEntityByCompanyID(String companyID) {
		// TODO Auto-generated method stub
		
		logger.info("----- CompanyMasterServiceImpl getCompanyEntityByCompanyID -----");
		
		return companyMasterRepository.findById(companyID).orElseThrow();
	}

}
