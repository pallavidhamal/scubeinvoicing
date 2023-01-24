package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.entity.ChangesInMatMasterEntity;

public interface ChangesInMatMasterService {

	List<ChangesInMatMasterEntity> getChangesInMatMasterByLast7Days () throws Exception;

}
