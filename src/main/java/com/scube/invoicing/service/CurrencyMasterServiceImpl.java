package com.scube.invoicing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CurrencyMasterResponseDto;
import com.scube.invoicing.dto.mapper.CurrencyMasterMapper;
import com.scube.invoicing.entity.CurrencyMasterEntity;
import com.scube.invoicing.repository.CurrencyMasterRepository;

@Service
public class CurrencyMasterServiceImpl implements CurrencyMasterService {
	
	@Autowired
	CurrencyMasterRepository currencyMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyMasterServiceImpl.class);

	@Override
	public List<CurrencyMasterResponseDto> getAllCurrencyLists() {
		// TODO Auto-generated method stub
		logger.info("------- CurrencyMasterServiceImpl getAllCurrencyLists -------");
		
		List<CurrencyMasterEntity> currencyMasterEntities = currencyMasterRepository.findAll();
		
		return CurrencyMasterMapper.toCurrencyMasterResponseDtosList(currencyMasterEntities);
	}

	@Override
	public CurrencyMasterEntity getCurrencyMasterEntityByCurrencyId(String currencyID) {
		// TODO Auto-generated method stub
		logger.info("------- CurrencyMasterServiceImpl getCurrencyMasterEntityByCurrencyId -------");
		
		return currencyMasterRepository.findById(currencyID).get();
	}

}
