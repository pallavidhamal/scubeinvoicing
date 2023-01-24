package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ImportedmatDataDto;
import com.scube.invoicing.dto.ReportResponseDto;

public class ImportedMaterialDataMapper {
	
	public static List<ImportedmatDataDto> toImportedmatDataDtos (List<Map<String, String>> listResponseImportedMatData) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<ImportedmatDataDto> importedmatDataDtosList  = new ArrayList<ImportedmatDataDto>();
		
		for(int i=0; i<listResponseImportedMatData.size(); i++) {
			
			ImportedmatDataDto importedmatDataDtoObj = new ImportedmatDataDto();
			
			final ImportedmatDataDto pojo = mapper.convertValue(listResponseImportedMatData.get(i), ImportedmatDataDto.class);
			
			importedmatDataDtoObj.setMaterial_code(pojo.getMaterial_code());
			importedmatDataDtoObj.setMat_desc(pojo.getMat_desc());
			importedmatDataDtoObj.setReport_date(pojo.getReport_date());
			importedmatDataDtoObj.setDomestic_imported(pojo.getDomestic_imported());
			importedmatDataDtoObj.setInland_transit_days(pojo.getInland_transit_days());
			importedmatDataDtoObj.setMaterial_in_inland_transit_units(pojo.getMaterial_in_inland_transit_units());
			importedmatDataDtoObj.setPort_ICD_clrnc_days(pojo.getPort_ICD_clrnc_days());
			importedmatDataDtoObj.setMaterial_port_ICD_units(pojo.getMaterial_port_ICD_units());
			importedmatDataDtoObj.setHigh_seas_transit_days(pojo.getHigh_seas_transit_days());
			importedmatDataDtoObj.setMaterial_high_seas_units(pojo.getMaterial_high_seas_units());
			importedmatDataDtoObj.setLeadtime_days(pojo.getLeadtime_days());
			importedmatDataDtoObj.setUnexecuted_orders_units(pojo.getUnexecuted_orders_units());
			importedmatDataDtoObj.setTotal_purchase_odrs_units(pojo.getTotal_purchase_odrs_units());
			
			importedmatDataDtosList.add(importedmatDataDtoObj);
		}
			return importedmatDataDtosList;
	} 

}
