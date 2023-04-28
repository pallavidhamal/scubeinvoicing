package com.scube.invoicing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.entity.LedgerTypeEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.LedgerTypeRepository;

@Service
public class LedgerTypeServiceImpl implements LedgerTypeService {
	
	@Autowired
	LedgerTypeRepository ledgerTypeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LedgerTypeServiceImpl.class);

	@Override
	public LedgerTypeEntity getLedgerTypeEntityByLedgerName(String ledgerName) {
		// TODO Auto-generated method stub
		logger.info("---- LedgerTypeServiceImpl getLedgerTypeEntityByLedgerName ----");
		
		LedgerTypeEntity ledgerTypeEntity = ledgerTypeRepository.findByLegderType(ledgerName);
		
		if(ledgerTypeEntity == null) {
			throw BRSException.throwException(EntityType.LEDGERTYPE, ExceptionType.ENTITY_NOT_FOUND, ledgerName);
		}
		
		return ledgerTypeEntity;
	}

}
