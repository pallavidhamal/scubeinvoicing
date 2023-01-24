package com.scube.invoicing.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.RmImportsuffDto;
import com.scube.invoicing.dto.RmformattedImportsuffDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.mapper.RmFormattedImportSuffMapper;
import com.scube.invoicing.dto.mapper.RmImportSuffMapper;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.repository.RmImportSuffRepository;

@Service
public class RmImportSuffServiceImpl implements RmImportSuffService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImportedMatDataServiceImpl.class);
	
	@Autowired
	RmImportSuffRepository rmImportSuffRepository;
	
	@Autowired
	
	ReportRepository  repRepository;
	
	

	@Override
	public List<RmImportsuffDto> getRmImportData(@Valid ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("*****RmImportSuffServiceImpl getRmImportData*****");
		
		List<RmImportsuffDto> defaultRmImportSuffList = new ArrayList<RmImportsuffDto>();
		List<RmImportsuffDto> rmImportSuffByMatCodeEntityList = new ArrayList<RmImportsuffDto>();
		
		if(reportIncomingDto.getMaterialCode().equals("All")) {
			
			List<Map<String, String>> defaultAllRmImportSuffEntityList = rmImportSuffRepository.getAllRmImportSuffData();
			
			if(defaultAllRmImportSuffEntityList == null){
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of Default All List " +defaultAllRmImportSuffEntityList.size() + "-----------------------------");
			
			defaultRmImportSuffList = RmImportSuffMapper.toRmImportsuffDtos(defaultAllRmImportSuffEntityList);
		
			return defaultRmImportSuffList;
		}
		else {
			List<Map<String, String>> rmImportSuffEntityList = rmImportSuffRepository.getRmImportSuffDataByMatCode(reportIncomingDto.getMaterialCode());
			
			if (rmImportSuffEntityList == null) {
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of List By Mat Code " + rmImportSuffEntityList.size() + "-----------------------------");
			
			rmImportSuffByMatCodeEntityList = RmImportSuffMapper.toRmImportsuffDtos(rmImportSuffEntityList);
					
		}
		
		
		return rmImportSuffByMatCodeEntityList;
		
	}

	@Override
	public List<RmformattedImportsuffDto> getRmFormattedImportData(@Valid ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		List<RmformattedImportsuffDto> defaultRmImportSuffList = new ArrayList<RmformattedImportsuffDto>();
		List<RmformattedImportsuffDto> rmImportSuffByMatCodeEntityList = new ArrayList<RmformattedImportsuffDto>();
		
		if(reportIncomingDto.getMaterialCode().equals("All")) {
			
			List<Map<String, String>> defaultAllRmImportSuffEntityList = repRepository.getAllRmFormattedImportSuffData();
			
			if(defaultAllRmImportSuffEntityList == null){
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of Default All List " +defaultAllRmImportSuffEntityList.size() + "-----------------------------");
			
			defaultRmImportSuffList = RmFormattedImportSuffMapper.toRmImportsuffDtos(defaultAllRmImportSuffEntityList);   
		
			return defaultRmImportSuffList;
		}
		else {
			List<Map<String, String>> rmImportSuffEntityList = repRepository.getRmFormattedImportSuffDataByMatCode(reportIncomingDto.getMaterialCode());
			
			if (rmImportSuffEntityList == null) {
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of List By Mat Code " + rmImportSuffEntityList.size() + "-----------------------------");
			
			rmImportSuffByMatCodeEntityList = RmFormattedImportSuffMapper.toRmImportsuffDtos(rmImportSuffEntityList);
					
		}
		
		
		return rmImportSuffByMatCodeEntityList;
		
	}
	

}
