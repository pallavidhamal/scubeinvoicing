package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CompanyMasterResponseDto;
import com.scube.invoicing.entity.CompanyMasterEntity;

public class CompanyMasterMapper {
	
	public static CompanyMasterResponseDto toCompanyMasterResponseDto(CompanyMasterEntity companyMasterEntity) {
		return new CompanyMasterResponseDto()
				.setCompanyID(companyMasterEntity.getId())
				.setName(companyMasterEntity.getName())
				.setEmailId(companyMasterEntity.getEmailId())
				.setContactNo(companyMasterEntity.getContactNo())
				
				.setCompanyName(companyMasterEntity.getCompanyName())
				.setCompanyAddress(companyMasterEntity.getCompanyAddress())
				.setCompanyWebsite(companyMasterEntity.getCompanyWebsite())
				.setCompanyGstin(companyMasterEntity.getCompanyGstin())
				.setCompanyCinNo(companyMasterEntity.getCompanyCinNo())
				.setCompanyPanNo(companyMasterEntity.getCompanyPanNo())
				
				.setCompanyAccountantEmailId(companyMasterEntity.getCompanyFinancialAccountant())
				
				.setBankName(companyMasterEntity.getBankName())
				.setBankAccountNo(companyMasterEntity.getCompanyBankAccNo())
				.setBankAddress(companyMasterEntity.getBankAddress())
				.setBeneficiary(companyMasterEntity.getBeneficiary())
				.setSwiftCode(companyMasterEntity.getSwiftCode());
		
	}
	
	public static List<CompanyMasterResponseDto> toCompanyMasterResponseDtosList(List<CompanyMasterEntity> companyMasterEntities) {
		List<CompanyMasterResponseDto> companyMasterResponseDtos = new ArrayList<CompanyMasterResponseDto>();
		
		for(CompanyMasterEntity companyMasterEntity : companyMasterEntities) {
			companyMasterResponseDtos.add(toCompanyMasterResponseDto(companyMasterEntity));
		}
		
		return companyMasterResponseDtos;
	}

}
