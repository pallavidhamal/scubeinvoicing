package com.scube.invoicing.service;

import com.scube.invoicing.entity.LedgerTypeEntity;

public interface LedgerTypeService {
	
	LedgerTypeEntity getLedgerTypeEntityByLedgerName(String ledgerName);

}
