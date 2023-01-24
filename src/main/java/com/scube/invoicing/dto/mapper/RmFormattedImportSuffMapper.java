package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ImportedmatDataDto;
import com.scube.invoicing.dto.RmImportsuffDto;
import com.scube.invoicing.dto.RmformattedImportsuffDto;

public class RmFormattedImportSuffMapper {
	
	public static List<RmformattedImportsuffDto> toRmImportsuffDtos  (List<Map<String, String>> listResponseRmImportSuff) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<RmformattedImportsuffDto> rmImportsuffDtosList = new ArrayList<RmformattedImportsuffDto>();
		
		for(int i=0; i<listResponseRmImportSuff.size(); i++) {
			
			RmformattedImportsuffDto rmImportsuffDtoObj = new RmformattedImportsuffDto();
			
			final RmformattedImportsuffDto pojo = mapper.convertValue(listResponseRmImportSuff.get(i), RmformattedImportsuffDto.class);
			
			rmImportsuffDtoObj.setMat_code(pojo.getMat_code());
			rmImportsuffDtoObj.setInventory(pojo.getInventory());
			rmImportsuffDtoObj.setPlant(pojo.getPlant());
			rmImportsuffDtoObj.setPlant_it(pojo.getPlant_it());
			
			
			rmImportsuffDtoObj.setPlant_it_icd(pojo.getPlant_it_icd());
			
			rmImportsuffDtoObj.setPlant_it_icd_hs(pojo.getPlant_it_icd_hs());
			
			rmImportsuffDtoObj.setPlant_it_icd_hs_opcd(pojo.getPlant_it_icd_hs_opcd());
			
			rmImportsuffDtoObj.setUom(pojo.getUom());
			rmImportsuffDtosList.add(rmImportsuffDtoObj);
		}
		return rmImportsuffDtosList;
	}

	public static List<RmformattedImportsuffDto> toRmImportsuffDtosList(
			List<Map<String, String>> defaultAllRmImportSuffEntityList) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
