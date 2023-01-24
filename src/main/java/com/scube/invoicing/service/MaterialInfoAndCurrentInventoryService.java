package com.scube.invoicing.service;

import java.io.File;
import java.util.List;

import com.scube.invoicing.entity.ExcelFileResultEntity;


public interface MaterialInfoAndCurrentInventoryService {
	
	List<ExcelFileResultEntity> uploadExcelFileData () throws Exception;

}
