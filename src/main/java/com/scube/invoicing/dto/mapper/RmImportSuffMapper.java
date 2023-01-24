package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scube.invoicing.dto.ImportedmatDataDto;
import com.scube.invoicing.dto.RmImportsuffDto;

public class RmImportSuffMapper {
	
	public static List<RmImportsuffDto> toRmImportsuffDtos  (List<Map<String, String>> listResponseRmImportSuff) {
		
		final ObjectMapper mapper = new ObjectMapper();
		List<RmImportsuffDto> rmImportsuffDtosList = new ArrayList<RmImportsuffDto>();
		
		for(int i=0; i<listResponseRmImportSuff.size(); i++) {
			
			RmImportsuffDto rmImportsuffDtoObj = new RmImportsuffDto();
			
			final RmImportsuffDto pojo = mapper.convertValue(listResponseRmImportSuff.get(i), RmImportsuffDto.class);
			
			rmImportsuffDtoObj.setMat_code(pojo.getMat_code());
			rmImportsuffDtoObj.setMaterial_description(pojo.getMaterial_description());
			rmImportsuffDtoObj.setPlant_mnstknorm1(pojo.getPlant_mnstknorm1());
			rmImportsuffDtoObj.setPlant_mxstknorm1(pojo.getPlant_mxstknorm1());
			rmImportsuffDtoObj.setPlant_mnstknorm2(pojo.getPlant_mnstknorm2());
			rmImportsuffDtoObj.setPlant_mxstknorm2(pojo.getPlant_mxstknorm2());
			rmImportsuffDtoObj.setPlant_curinv_transitpipeorders(pojo.getPlant_curinv_transitpipeorders());
			rmImportsuffDtoObj.setPlant_mnstknorm3(pojo.getPlant_mnstknorm3());
			rmImportsuffDtoObj.setPlant_mxstknorm3(pojo.getPlant_mxstknorm3());
			rmImportsuffDtoObj.setPlant_cum_curinv_transitpipeorders(pojo.getPlant_cum_curinv_transitpipeorders());
			rmImportsuffDtoObj.setInland_mnstknorm1(pojo.getInland_mnstknorm1());
			rmImportsuffDtoObj.setInland_mnstknorm2(pojo.getInland_mnstknorm2());
			rmImportsuffDtoObj.setInland_curinv_transitpipeorders(pojo.getInland_curinv_transitpipeorders());
			rmImportsuffDtoObj.setInland_mnstknorm3(pojo.getInland_mnstknorm3());
			rmImportsuffDtoObj.setInland_mxstknorm3(pojo.getInland_mxstknorm3());
			rmImportsuffDtoObj.setPlantit_cum_curinv_transitpipeorders(pojo.getPlantit_cum_curinv_transitpipeorders());
			rmImportsuffDtoObj.setIcd_mnstknorm1(pojo.getIcd_mnstknorm1());
			rmImportsuffDtoObj.setIcd_mnstknorm2(pojo.getIcd_mnstknorm2());
			rmImportsuffDtoObj.setIcd_curinv_transitpipeorders(pojo.getIcd_curinv_transitpipeorders());
			rmImportsuffDtoObj.setIcd_mnstknorm3(pojo.getIcd_mnstknorm3());
			rmImportsuffDtoObj.setIcd_mxstknorm3(pojo.getIcd_mxstknorm3());
			rmImportsuffDtoObj.setPlantiticd_cum_curinv_transitpipeorders(pojo.getPlantiticd_cum_curinv_transitpipeorders());
			rmImportsuffDtoObj.setHs_mnstknorm1(pojo.getHs_mnstknorm1());
			rmImportsuffDtoObj.setHs_mnstknorm2(pojo.getHs_mnstknorm2());
			rmImportsuffDtoObj.setHs_curinv_transitpipeorders(pojo.getHs_curinv_transitpipeorders());
			rmImportsuffDtoObj.setHs_mnstknorm3(pojo.getHs_mnstknorm3());
			rmImportsuffDtoObj.setHs_mxstknorm3(pojo.getHs_mxstknorm3());
			rmImportsuffDtoObj.setPlantiticdhs_cum_curinv_transitpipeorders(pojo.getPlantiticdhs_cum_curinv_transitpipeorders());
			rmImportsuffDtoObj.setOpcd_mnstknorm1(pojo.getOpcd_mnstknorm1());
			rmImportsuffDtoObj.setOpcd_mnstknorm2(pojo.getOpcd_mnstknorm2());
			rmImportsuffDtoObj.setOpcd_curinv_transitpipeorders(pojo.getOpcd_curinv_transitpipeorders());
			rmImportsuffDtoObj.setOpcd_mnstknorm3(pojo.getOpcd_mnstknorm3());
			rmImportsuffDtoObj.setOpcd_mxstknorm3(pojo.getOpcd_mxstknorm3());
			rmImportsuffDtoObj.setPlantiticdhsopcd_cum_curinv_transitpipeorders(pojo.getPlantiticdhsopcd_cum_curinv_transitpipeorders());
			rmImportsuffDtoObj.setPlant_suff(pojo.getPlant_suff());
			rmImportsuffDtoObj.setInland_suff(pojo.getInland_suff());
			rmImportsuffDtoObj.setIcd_suff(pojo.getIcd_suff());
			rmImportsuffDtoObj.setHs_suff(pojo.getHs_suff());
			rmImportsuffDtoObj.setOpcd_suff(pojo.getOpcd_suff());
			rmImportsuffDtoObj.setReport_date(pojo.getReport_date());
			
			rmImportsuffDtosList.add(rmImportsuffDtoObj);
		}
		return rmImportsuffDtosList;
	}

	public static List<RmImportsuffDto> toRmImportsuffDtosList(
			List<Map<String, String>> defaultAllRmImportSuffEntityList) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
