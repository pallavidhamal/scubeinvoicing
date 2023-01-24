package com.scube.invoicing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.ImportedItemDto;
import com.scube.invoicing.dto.mapper.ImportedItemMapper;
import com.scube.invoicing.entity.ImportedItemEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ImportedItemRepository;
import com.scube.invoicing.repository.UnitOfMeasurementRepository;

@Service
public class ImportedItemServiceimpl implements ImportedItemService{

	private static final Logger logger = LoggerFactory.getLogger(MaterialInfoMasterServiceImpl.class);
	
	@Autowired
	ImportedItemRepository importedItemRepository;
	
	@Autowired
	UnitOfMeasurementRepository unitOfMeasurementRepository;
	
	@Override
	public List<ImportedItemDto> getAllImportedData() {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl getAllMaterialInfo*****");
		
		List<ImportedItemEntity> importedItemEntity = importedItemRepository.getImportedItemData();
		
		if (importedItemEntity == null) {
			
			throw BRSException.throwException("Error : No Records Present");
			
		}
		
		return ImportedItemMapper.toImportedItemDtoList(importedItemEntity);
	}

}
