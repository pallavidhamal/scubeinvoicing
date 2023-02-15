package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.CompanyMasterResponseDto;
import com.scube.invoicing.dto.incoming.CompanyMasterIncomingDto;
import com.scube.invoicing.entity.CompanyMasterEntity;

public interface CompanyMasterService {
	
	boolean addCompanyDetails(@Valid CompanyMasterIncomingDto companyMasterIncomingDto);
	
	boolean updateCompanyDetails(@Valid CompanyMasterIncomingDto companyMasterIncomingDto);
	
	boolean deleteCompanyDetailsByCompanyId(String companyID);
	
	CompanyMasterResponseDto getCompanyDetailsByCompanyId(String companyID);
	
	List<CompanyMasterResponseDto> getAllCompanyDetails();
	
	CompanyMasterEntity getCompanyEntityByCompanyID(String companyID);

}
