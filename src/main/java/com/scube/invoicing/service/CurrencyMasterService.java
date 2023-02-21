package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.dto.CurrencyMasterResponseDto;
import com.scube.invoicing.entity.CurrencyMasterEntity;

public interface CurrencyMasterService {
	
	CurrencyMasterEntity getCurrencyMasterEntityByCurrencyId(String currencyID);

	List<CurrencyMasterResponseDto> getAllCurrencyLists();
	
}
