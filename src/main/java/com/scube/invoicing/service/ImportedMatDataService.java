package com.scube.invoicing.service;

import java.text.ParseException;
import java.util.List;

import com.scube.invoicing.dto.ImportedmatDataDto;
import com.scube.invoicing.entity.ImportedMatDataEntity;

public interface ImportedMatDataService {
	
	List<ImportedMatDataEntity> uploadImportedMatData() throws Exception;
	
	List<ImportedmatDataDto> getImportedMaterialData() throws ParseException;

}
