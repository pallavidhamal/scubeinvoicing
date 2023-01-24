package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.MaterialInfoMasterDto;
import com.scube.invoicing.entity.MaterialInfoMasterEntity;

public class MaterialInfoMasterMapper {
	
	public static MaterialInfoMasterDto toMaterialInfoMasterDto (MaterialInfoMasterEntity materialInfoMasterEntity) {
		
		return new MaterialInfoMasterDto()
				.setMaterialInfoId(materialInfoMasterEntity.getId())
				.setMaterialCode(materialInfoMasterEntity.getMaterialCode())
				.setReadilyAvailable(materialInfoMasterEntity.getReadilyAvailable())
				.setAveragePerDayUnits(materialInfoMasterEntity.getAveragePerDayUnits())
				.setEoq(materialInfoMasterEntity.getEoq())
				.setKeptInStock(materialInfoMasterEntity.getKeptInStock())
				.setLeadTime(String.valueOf(materialInfoMasterEntity.getLeadTime()))
				.setTransTime(String.valueOf(materialInfoMasterEntity.getTransTime()))
				.setSupplierMOQ(materialInfoMasterEntity.getSupplierMOQ())
				.setSafetyFactory(materialInfoMasterEntity.getSafetyFactory())
				.setSsNormUnits(materialInfoMasterEntity.getSsNormUnits())
				.setRegularIntermediate(materialInfoMasterEntity.getRegularIntermediate())
				.setMaterialDescription(materialInfoMasterEntity.getMaterialDescription())
				.setUnitId(String.valueOf(materialInfoMasterEntity.getUnitOfMeasurementEntity().getId()))
				.setFsDays(materialInfoMasterEntity.getFsDays())
				.setUnitOfMeasurementValue(materialInfoMasterEntity.getUnitOfMeasurementEntity().getUnitOfMeasurement())
				.setItemCategory(materialInfoMasterEntity.getItemCategory());
				
		
	}
	
	
	public static List<MaterialInfoMasterDto> toMaterialInfoMasterDtosList (List<MaterialInfoMasterEntity> materialInfoMasterEntities) {
		
		List<MaterialInfoMasterDto> materialInfoMasterDtos = new ArrayList<MaterialInfoMasterDto>();
		
		for(MaterialInfoMasterEntity materialInfoMasterEntity : materialInfoMasterEntities) {
			
			materialInfoMasterDtos.add(toMaterialInfoMasterDto(materialInfoMasterEntity));
			
		}
		
		return materialInfoMasterDtos;
				
	}

}
