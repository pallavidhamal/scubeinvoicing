package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.CurrencyMasterResponseDto;
import com.scube.invoicing.entity.CurrencyMasterEntity;

public class CurrencyMasterMapper {
	
	public static CurrencyMasterResponseDto toCurrencyMasterResponseDto(CurrencyMasterEntity currencyMasterEntity) {
		return new CurrencyMasterResponseDto()
				.setCurrencyID(currencyMasterEntity.getId())
				.setCurrencyName(currencyMasterEntity.getCurrencyName());
	}
	
	public static List<CurrencyMasterResponseDto> toCurrencyMasterResponseDtosList(List<CurrencyMasterEntity> currencyMasterEntities) {
		List<CurrencyMasterResponseDto> currencyMasterResponseDtos = new ArrayList<CurrencyMasterResponseDto>();
		
		for(CurrencyMasterEntity currencyMasterEntity : currencyMasterEntities) {
			currencyMasterResponseDtos.add(toCurrencyMasterResponseDto(currencyMasterEntity));
		}
		
		return currencyMasterResponseDtos;
	}

}
