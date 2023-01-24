package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.ImportedItemDto;
import com.scube.invoicing.entity.ImportedItemEntity;

public class ImportedItemMapper {
	
	public static ImportedItemDto toImportedItemDto (ImportedItemEntity importedItemEntity) {
		
		return new ImportedItemDto()
				.setMaterialId(importedItemEntity.getId())
				.setMaterialCode(importedItemEntity.getMaterialCode())
				.setReadilyAvailable(importedItemEntity.getReadilyAvailable())
				.setAveragePerDayUnits(importedItemEntity.getAveragePerDayUnits())
				.setEoq(importedItemEntity.getEoq())
				.setKeptInStock(importedItemEntity.getKeptInStock())
				.setLeadTime(String.valueOf(importedItemEntity.getLeadTime()))
				.setTransTime(String.valueOf(importedItemEntity.getTransTime()))
				.setSupplierMOQ(importedItemEntity.getSupplierMOQ())
				.setSafetyFactory(importedItemEntity.getSafetyFactory())
				.setSsNormUnits(importedItemEntity.getSsNormUnits())
				.setRegularIntermediate(importedItemEntity.getRegularIntermediate())
				.setMaterialDescription(importedItemEntity.getMaterialDescription())
				.setUnitId(String.valueOf(importedItemEntity.getUnitOfMeasurementEntity().getId()))
				.setFsDays(importedItemEntity.getFsDays())
				.setUnitOfMeasurementValue(importedItemEntity.getUnitOfMeasurementEntity().getUnitOfMeasurement())
				.setItemCategory(importedItemEntity.getItemCategory());
			
		
	}

	public static List<ImportedItemDto> toImportedItemDtoList(List<ImportedItemEntity> importedItemEntities) {
		
		List<ImportedItemDto> importedItemDtos = new ArrayList<ImportedItemDto>();
		// TODO Auto-generated method stub
		for (ImportedItemEntity importedItemEntity: importedItemEntities) {
			
			importedItemDtos.add(toImportedItemDto(importedItemEntity));
		}
		return importedItemDtos;
	}

}
