package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.GSTMasterResponseDto;
import com.scube.invoicing.dto.incoming.GSTMasterIncomingDto;
import com.scube.invoicing.dto.mapper.GSTMasterMapper;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.GSTMasterRepository;

@Service
public class GSTMasterServiceImpl implements GSTMasterService{
	
	@Autowired
	GSTMasterRepository gstMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(GSTMasterServiceImpl.class);

	@Override
	public boolean addGstInfoDetails(@Valid GSTMasterIncomingDto gstMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("-------- GSTMasterServiceImpl addGstInfoDetails ------");
		
		if(gstMasterIncomingDto.getDescription() == "" || gstMasterIncomingDto.getDescription().trim().isEmpty()) {
			throw BRSException.throwException("Error : Description cannot be blank");
		}
		
		GSTMasterEntity gstMasterEntity = new GSTMasterEntity();
		
		gstMasterEntity.setIsdeleted("N");
		gstMasterEntity.setValue(gstMasterIncomingDto.getPercentValue());
		gstMasterEntity.setDescription(gstMasterIncomingDto.getDescription());
		
		gstMasterRepository.save(gstMasterEntity);
		
		logger.info("--- Record Added Successfully ----");
		
		return true;
	}

	@Override
	public boolean updateGstInfoDetails(@Valid GSTMasterIncomingDto gstMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("-------- GSTMasterServiceImpl updateGstInfoDetails ------");
		
		if(gstMasterIncomingDto.getDescription() == "" || gstMasterIncomingDto.getDescription().trim().isEmpty()) {
			throw BRSException.throwException("Error : Description cannot be blank");
		}
		
		GSTMasterEntity gstMasterEntity = gstMasterRepository.findById(gstMasterIncomingDto.getGstId()).get();
		
		gstMasterEntity.setIsdeleted("N");
		gstMasterEntity.setValue(gstMasterIncomingDto.getPercentValue());
		gstMasterEntity.setDescription(gstMasterIncomingDto.getDescription());
		
		gstMasterRepository.save(gstMasterEntity);
		
		logger.info("--- Record Updated Successfully ----");
		
		return true;
	}

	@Override
	public boolean deleteGstInfoDetailsByGstId(String gstID) {
		// TODO Auto-generated method stub
		
		logger.info("-------- GSTMasterServiceImpl deleteGstInfoDetailsByGstId ------");
		
		GSTMasterEntity gstMasterEntity = gstMasterRepository.findById(gstID).get();
		
		if(gstMasterEntity == null) {
			throw BRSException.throwException("Error : No GST Records Found ");
		}
		
		gstMasterRepository.delete(gstMasterEntity);
		
		logger.info("--- Record Deleted Successfully ----");
		
		return true;
	}

	@Override
	public GSTMasterResponseDto getGstInfoDetailsByGstId(String gstID) {
		// TODO Auto-generated method stub
		
		logger.info("-------- GSTMasterServiceImpl getGstInfoDetailsByGstId ------");
		
		GSTMasterEntity gstMasterEntity = gstMasterRepository.findById(gstID).get();
		
		if(gstMasterEntity == null) {
			throw BRSException.throwException("Error : No GST Records Found ");
		}
		
		return GSTMasterMapper.toGstMasterResponseDto(gstMasterEntity);
	}

	@Override
	public List<GSTMasterResponseDto> getAllGstInfoDetails() {
		// TODO Auto-generated method stub
		
		logger.info("-------- GSTMasterServiceImpl getAllGstInfoDetails ------");
		
		List<GSTMasterEntity> gstMasterEntityList = gstMasterRepository.findAll();
		
		if(gstMasterEntityList == null) {
			throw BRSException.throwException("Error : No GST Records Found ");
		}
		
		return GSTMasterMapper.toAllGSTMasterDataList(gstMasterEntityList);
	}

	@Override
	public GSTMasterEntity getGstMasterEntityByGstID(String gstID) {
		// TODO Auto-generated method stub
		return gstMasterRepository.findById(gstID).get();
	}

}
