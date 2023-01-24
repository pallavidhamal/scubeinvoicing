package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.MaterialInfoMasterDto;
import com.scube.invoicing.dto.incoming.MaterialInfoMasterIncomingDto;

public interface MaterialInfoMasterService {
	
	boolean addMaterialInfoDetails (@Valid MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto);

	boolean updateMaterialInfoDetails(@Valid MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto);
	
	boolean deleteMaterialInfoRecordById(long materialInfoId);
	
	MaterialInfoMasterDto getMaterialInfoById(long materialInfoId);
	
	List<MaterialInfoMasterDto> getAllMaterialInfo();
	
	List<MaterialInfoMasterDto> getAllUnitOfMeasurementValue();
	
	MaterialInfoMasterDto getMaterialInfoByMaterialCode(String materialCode);
	
}
