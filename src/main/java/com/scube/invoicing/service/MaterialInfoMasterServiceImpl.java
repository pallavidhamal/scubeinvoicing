package com.scube.invoicing.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.MaterialInfoMasterDto;
import com.scube.invoicing.dto.incoming.MaterialInfoMasterIncomingDto;
import com.scube.invoicing.dto.mapper.MaterialInfoMasterMapper;
import com.scube.invoicing.entity.MaterialInfoMasterEntity;
import com.scube.invoicing.entity.UnitOfMeasurementEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.MaterialInfoMasterRepository;
import com.scube.invoicing.repository.UnitOfMeasurementRepository;


@Service
public class MaterialInfoMasterServiceImpl implements MaterialInfoMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialInfoMasterServiceImpl.class);
	
	@Autowired
	MaterialInfoMasterRepository materialInfoMasterRepository;
	
	@Autowired
	UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Override
	public boolean addMaterialInfoDetails(@Valid MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl addMaterialInfoDetails*****");


		if(materialInfoMasterIncomingDto.getMaterialCode() == "" || materialInfoMasterIncomingDto.getMaterialCode().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Material Code cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getKeptInStock() == "" || materialInfoMasterIncomingDto.getKeptInStock().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Kept in Stock cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getReadilyAvailable() == "" || materialInfoMasterIncomingDto.getReadilyAvailable().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Readily Available Material cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getRegularIntermediate() == "" || materialInfoMasterIncomingDto.getRegularIntermediate().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Register Interval cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getMaterialDescription() == "" || materialInfoMasterIncomingDto.getMaterialDescription().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Material Description cannot be blank");
			
		}
		
		MaterialInfoMasterEntity checkDuplicateMaterialCode = materialInfoMasterRepository.findByMaterialCode(materialInfoMasterIncomingDto.getMaterialCode());
		
		if(checkDuplicateMaterialCode != null) {
			
			logger.info("Material Code already exists : " + checkDuplicateMaterialCode.getMaterialCode());
			throw BRSException.throwException(EntityType.MATERIALCODE, ExceptionType.ALREADY_EXIST, materialInfoMasterIncomingDto.getMaterialCode());
			
		}
		
		MaterialInfoMasterEntity materialInfoMasterEntity = new MaterialInfoMasterEntity();
		
		materialInfoMasterEntity.setMaterialCode(materialInfoMasterIncomingDto.getMaterialCode());
		materialInfoMasterEntity.setRegularIntermediate(materialInfoMasterIncomingDto.getRegularIntermediate());
		materialInfoMasterEntity.setKeptInStock(materialInfoMasterIncomingDto.getKeptInStock());
		materialInfoMasterEntity.setReadilyAvailable(materialInfoMasterIncomingDto.getReadilyAvailable());
		materialInfoMasterEntity.setAveragePerDayUnits(materialInfoMasterIncomingDto.getAveragePerDayUnits());
		materialInfoMasterEntity.setLeadTime(Integer.valueOf(materialInfoMasterIncomingDto.getLeadTime()));
		materialInfoMasterEntity.setTransTime(Integer.valueOf(materialInfoMasterIncomingDto.getTransTime()));
		materialInfoMasterEntity.setSupplierMOQ(materialInfoMasterIncomingDto.getSupplierMOQ());
		materialInfoMasterEntity.setEoq(materialInfoMasterIncomingDto.getEoq());
		materialInfoMasterEntity.setSafetyFactory(materialInfoMasterIncomingDto.getSafetyFactory());
		materialInfoMasterEntity.setSsNormUnits(materialInfoMasterIncomingDto.getSsNormUnits());
		materialInfoMasterEntity.setFsDays(materialInfoMasterIncomingDto.getFsDays());
		materialInfoMasterEntity.setItemCategory(materialInfoMasterIncomingDto.getItemCategory());
		
		
		materialInfoMasterEntity.setMaterialDescription(materialInfoMasterIncomingDto.getMaterialDescription());
		
		UnitOfMeasurementEntity unitOfMeasurementEntity = unitOfMeasurementRepository.getUnitOfMeasurementEntityById(Long.parseLong(materialInfoMasterIncomingDto.getUnitId()));
		
		logger.info("Entity ID ----" + unitOfMeasurementEntity.getId() + "Entity Name ----------" + unitOfMeasurementEntity.getUnitOfMeasurement());
		
		materialInfoMasterEntity.setUnitOfMeasurementEntity(unitOfMeasurementEntity);
		
		materialInfoMasterRepository.save(materialInfoMasterEntity);
		
		logger.info("---------------------" + "saved in DB" + "---------------");
		
		return true;
	}

	@Override
	public boolean updateMaterialInfoDetails(@Valid MaterialInfoMasterIncomingDto materialInfoMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl updateMaterialInfoDetails*****");
		
		if(materialInfoMasterIncomingDto.getMaterialCode() == "" || materialInfoMasterIncomingDto.getMaterialCode().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Material Code cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getKeptInStock() == "" || materialInfoMasterIncomingDto.getKeptInStock().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Kept in Stock cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getReadilyAvailable() == "" || materialInfoMasterIncomingDto.getReadilyAvailable().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Readily Available Material cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getRegularIntermediate() == "" || materialInfoMasterIncomingDto.getRegularIntermediate().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Register Interval cannot be blank");
			
		}
		
		if(materialInfoMasterIncomingDto.getLeadTime() == "" || materialInfoMasterIncomingDto.getLeadTime().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Lead Time cannot be empty");
			
		}
		
		if(materialInfoMasterIncomingDto.getTransTime() == "" || materialInfoMasterIncomingDto.getTransTime().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Trans Time cannot be empty");
			
		}
		
		if(materialInfoMasterIncomingDto.getMaterialDescription() == "" || materialInfoMasterIncomingDto.getMaterialDescription().trim().isEmpty()) {
			
			throw BRSException.throwException("Error : Material Description cannot be blank");
			
		}
		
		
		UnitOfMeasurementEntity unitOfMeasurementEntity = unitOfMeasurementRepository.getUnitOfMeasurementEntityById(Long.parseLong(materialInfoMasterIncomingDto.getUnitId()));
		
		if(unitOfMeasurementEntity == null) {
			
			throw BRSException.throwException("Error : Unit Of Measurement not present");
			
		}	
		
		MaterialInfoMasterEntity materialInfoMasterEntity = materialInfoMasterRepository.findMaterialInfoMasterEntityById(materialInfoMasterIncomingDto.getMaterialInfoId());
		
		logger.info("ID to be edited -----" + materialInfoMasterEntity.getId());
		
		materialInfoMasterEntity.setMaterialCode(materialInfoMasterIncomingDto.getMaterialCode());
		materialInfoMasterEntity.setRegularIntermediate(materialInfoMasterIncomingDto.getRegularIntermediate());
		materialInfoMasterEntity.setKeptInStock(materialInfoMasterIncomingDto.getKeptInStock());
		materialInfoMasterEntity.setReadilyAvailable(materialInfoMasterIncomingDto.getReadilyAvailable());
		materialInfoMasterEntity.setAveragePerDayUnits(materialInfoMasterIncomingDto.getAveragePerDayUnits());
		materialInfoMasterEntity.setLeadTime(Integer.valueOf(materialInfoMasterIncomingDto.getLeadTime()));
		materialInfoMasterEntity.setTransTime(Integer.valueOf(materialInfoMasterIncomingDto.getTransTime()));
		materialInfoMasterEntity.setSupplierMOQ(materialInfoMasterIncomingDto.getSupplierMOQ());
		materialInfoMasterEntity.setEoq(materialInfoMasterIncomingDto.getEoq());
		materialInfoMasterEntity.setSafetyFactory(materialInfoMasterIncomingDto.getSafetyFactory());
		materialInfoMasterEntity.setSsNormUnits(materialInfoMasterIncomingDto.getSsNormUnits());
		materialInfoMasterEntity.setMaterialDescription(materialInfoMasterIncomingDto.getMaterialDescription());
		materialInfoMasterEntity.setUnitOfMeasurementEntity(unitOfMeasurementEntity);
		materialInfoMasterEntity.setFsDays(materialInfoMasterIncomingDto.getFsDays());
		materialInfoMasterEntity.setItemCategory(materialInfoMasterIncomingDto.getItemCategory());
		
		
		materialInfoMasterRepository.save(materialInfoMasterEntity);
		
		logger.info("---------------------" + "Record Edited Successfully" + "---------------");
		
		return true;
	}

	@Override
	public MaterialInfoMasterDto getMaterialInfoById(long materialInfoId) {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl getMaterialInfoById*****");
		
		MaterialInfoMasterEntity materialInfoMasterEntity = materialInfoMasterRepository.findMaterialInfoMasterEntityById(materialInfoId);
		
		logger.info("Material Info ID ------- " + materialInfoMasterEntity);
		
		if(materialInfoMasterEntity == null) {
			
			throw BRSException.throwException("Error : Invalid Material Info ID");
			
		}
		
		MaterialInfoMasterDto materialInfoMasterDto = new MaterialInfoMasterDto();
		
		materialInfoMasterDto.setMaterialCode(materialInfoMasterEntity.getMaterialCode());
		materialInfoMasterDto.setMaterialInfoId(materialInfoMasterEntity.getId());
		materialInfoMasterDto.setAveragePerDayUnits(materialInfoMasterEntity.getAveragePerDayUnits());
		materialInfoMasterDto.setEoq(materialInfoMasterEntity.getEoq());
		materialInfoMasterDto.setKeptInStock(materialInfoMasterEntity.getKeptInStock());
		materialInfoMasterDto.setRegularIntermediate(materialInfoMasterEntity.getRegularIntermediate());
		materialInfoMasterDto.setSupplierMOQ(materialInfoMasterEntity.getSupplierMOQ());
		materialInfoMasterDto.setLeadTime(String.valueOf(materialInfoMasterEntity.getLeadTime()));
		materialInfoMasterDto.setTransTime(String.valueOf(materialInfoMasterEntity.getTransTime()));
		materialInfoMasterDto.setSafetyFactory(materialInfoMasterEntity.getSafetyFactory());
		materialInfoMasterDto.setSsNormUnits(materialInfoMasterEntity.getSsNormUnits());
		materialInfoMasterDto.setMaterialDescription(materialInfoMasterEntity.getMaterialDescription());
		materialInfoMasterDto.setReadilyAvailable(materialInfoMasterEntity.getReadilyAvailable());
		materialInfoMasterDto.setUnitId(String.valueOf(materialInfoMasterEntity.getUnitOfMeasurementEntity().getId()));
		materialInfoMasterDto.setUnitOfMeasurementValue(materialInfoMasterEntity.getUnitOfMeasurementEntity().getUnitOfMeasurement());
		materialInfoMasterDto.setFsDays(materialInfoMasterEntity.getFsDays());
		materialInfoMasterDto.setItemCategory(materialInfoMasterEntity.getItemCategory());
		
		
		return materialInfoMasterDto;
	}
	
	
	@Override
	public MaterialInfoMasterDto getMaterialInfoByMaterialCode(String materialCode) {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl getMaterialInfoByMaterialCode*****");
		
		MaterialInfoMasterEntity materialInfoMasterEntity = materialInfoMasterRepository.findMaterialInfoMasterEntityByMaterialCode(materialCode);
		
		if(materialInfoMasterEntity == null) {
			
			throw BRSException.throwException("Error : Invalid Material Code");
			
		}
		
		MaterialInfoMasterDto materialInfoMasterDto = new MaterialInfoMasterDto();
		
		materialInfoMasterDto.setMaterialCode(materialInfoMasterEntity.getMaterialCode());
		materialInfoMasterDto.setMaterialInfoId(materialInfoMasterEntity.getId());
		materialInfoMasterDto.setAveragePerDayUnits(materialInfoMasterEntity.getAveragePerDayUnits());
		materialInfoMasterDto.setEoq(materialInfoMasterEntity.getEoq());
		materialInfoMasterDto.setKeptInStock(materialInfoMasterEntity.getKeptInStock());
		materialInfoMasterDto.setRegularIntermediate(materialInfoMasterEntity.getRegularIntermediate());
		materialInfoMasterDto.setSupplierMOQ(materialInfoMasterEntity.getSupplierMOQ());
		materialInfoMasterDto.setLeadTime(String.valueOf(materialInfoMasterEntity.getLeadTime()));
		materialInfoMasterDto.setTransTime(String.valueOf(materialInfoMasterEntity.getTransTime()));
		materialInfoMasterDto.setSafetyFactory(materialInfoMasterEntity.getSafetyFactory());
		materialInfoMasterDto.setSsNormUnits(materialInfoMasterEntity.getSsNormUnits());
		materialInfoMasterDto.setMaterialDescription(materialInfoMasterEntity.getMaterialDescription());
		materialInfoMasterDto.setReadilyAvailable(materialInfoMasterEntity.getReadilyAvailable());
		materialInfoMasterDto.setUnitId(String.valueOf(materialInfoMasterEntity.getUnitOfMeasurementEntity().getId()));
		materialInfoMasterDto.setUnitOfMeasurementValue(materialInfoMasterEntity.getUnitOfMeasurementEntity().getUnitOfMeasurement());
		materialInfoMasterDto.setFsDays(materialInfoMasterEntity.getFsDays());
		materialInfoMasterDto.setItemCategory(materialInfoMasterEntity.getItemCategory());
		
		return materialInfoMasterDto;
	}
	

	@Override
	public List<MaterialInfoMasterDto> getAllMaterialInfo() {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl getAllMaterialInfo*****");
		
		List<MaterialInfoMasterEntity> materialInfoMasterEntity = materialInfoMasterRepository.findAll();
		
		if(materialInfoMasterEntity == null) {
			
			throw BRSException.throwException("Error : No Records Present");
			
		}
		
		return MaterialInfoMasterMapper.toMaterialInfoMasterDtosList(materialInfoMasterEntity);
	}

	@Override
	public List<MaterialInfoMasterDto> getAllUnitOfMeasurementValue() {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl getAllUnitOfMeasurementValue*****");
		
		List<UnitOfMeasurementEntity> unitOfMeasurementEntities = unitOfMeasurementRepository.findAll();
		
		if(unitOfMeasurementEntities == null) {
			
			throw BRSException.throwException("Error : Unit Of Measurment Not Present");
			
		}
		
		List<MaterialInfoMasterDto> materialInfoMasterList = new ArrayList<MaterialInfoMasterDto>();
		
		for(UnitOfMeasurementEntity unitOfMeasurementEntity : unitOfMeasurementEntities) {
			
			MaterialInfoMasterDto materialInfoMasterDto = new MaterialInfoMasterDto();
			
			materialInfoMasterDto.setUnitId(String.valueOf(unitOfMeasurementEntity.getId()));
			materialInfoMasterDto.setUnitOfMeasurementValue(unitOfMeasurementEntity.getUnitOfMeasurement());
			
			materialInfoMasterList.add(materialInfoMasterDto);
			
		}
		
		return materialInfoMasterList;
	}

	@Override
	public boolean deleteMaterialInfoRecordById(long materialInfoId) {
		// TODO Auto-generated method stub
		
		logger.info("*****MaterialInfoMasterServiceImpl deleteMaterialInfoRecordById*****");
		
		MaterialInfoMasterEntity materialInfoMasterEntity = materialInfoMasterRepository.findMaterialInfoMasterEntityById(materialInfoId);
		
		logger.info("Material Info ID ------- " + materialInfoMasterEntity);
		
		if(materialInfoMasterEntity == null) {
			
			throw BRSException.throwException("Error : Invalid Material Info ID");
			
		}
		
		materialInfoMasterRepository.delete(materialInfoMasterEntity);
		
		logger.info("Record Deleted Successfully ------- " + materialInfoMasterEntity.getId() + "---" + materialInfoMasterEntity.getMaterialCode());
		
		return true;
	}
	
}
