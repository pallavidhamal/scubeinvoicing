package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.VendorMasterResponseDto;
import com.scube.invoicing.dto.incoming.VendorMasterIncomingDto;
import com.scube.invoicing.dto.mapper.VendorMasterMapper;
import com.scube.invoicing.entity.VendorMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.VendorMasterRepository;

@Service
public class VendorMasterServiceImpl implements VendorMasterService {
	
	@Autowired
	VendorMasterRepository vendorMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(VendorMasterServiceImpl.class);

	@Override
	public boolean addNewVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl addNewVendor ------");
		
		if(vendorMasterIncomingDto.getVendorName() == "" || 
				vendorMasterIncomingDto.getVendorName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Name cannot be blank or null.");
		}
		
		if(vendorMasterIncomingDto.getVendorContactNo() == "" || 
				vendorMasterIncomingDto.getVendorContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Contact No cannot be blank or null.");
		}
		
		if(vendorMasterIncomingDto.getVendorEmailID() == "" || 
				vendorMasterIncomingDto.getVendorEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Email ID cannot be blank or null.");
		}
		
		VendorMasterEntity dupliVendorCheckEntity = vendorMasterRepository.
				findByVendorName(vendorMasterIncomingDto.getVendorName());
		
		if(dupliVendorCheckEntity != null) {
			throw BRSException.throwException(EntityType.VENDOR, ExceptionType.ALREADY_EXIST, vendorMasterIncomingDto.getVendorName());
		}
		
		VendorMasterEntity vendorMasterEntity = new VendorMasterEntity();
		vendorMasterEntity.setIsdeleted("N");
		vendorMasterEntity.setVendorName(vendorMasterIncomingDto.getVendorName());
		vendorMasterEntity.setVendorContactNo(vendorMasterIncomingDto.getVendorContactNo());
		vendorMasterEntity.setVendorEmailID(vendorMasterIncomingDto.getVendorEmailID());

		vendorMasterRepository.save(vendorMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl updateVendor ------");
		
		if(vendorMasterIncomingDto.getVendorName() == "" || 
				vendorMasterIncomingDto.getVendorName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Name cannot be blank or null.");
		}
		
		if(vendorMasterIncomingDto.getVendorContactNo() == "" || 
				vendorMasterIncomingDto.getVendorContactNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Contact No cannot be blank or null.");
		}
		
		if(vendorMasterIncomingDto.getVendorEmailID() == "" || 
				vendorMasterIncomingDto.getVendorEmailID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Vendor Email ID cannot be blank or null.");
		}
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorMasterIncomingDto.getVendorID()).get();
		vendorMasterEntity.setIsdeleted("N");
		vendorMasterEntity.setVendorName(vendorMasterIncomingDto.getVendorName());
		vendorMasterEntity.setVendorContactNo(vendorMasterIncomingDto.getVendorContactNo());
		vendorMasterEntity.setVendorName(vendorMasterIncomingDto.getVendorEmailID());

		vendorMasterRepository.save(vendorMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteVendorByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl deleteVendorByVendorID ------");
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorID).get();
		vendorMasterRepository.delete(vendorMasterEntity);
		
		return true;
	}

	@Override
	public VendorMasterResponseDto getVendorInfoByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl getVendorInfoByVendorID ------");
		
		VendorMasterEntity vendorMasterEntity = vendorMasterRepository.findById(vendorID).get();
		
		return VendorMasterMapper.toVendorMasterResponseDto(vendorMasterEntity);
	}

	@Override
	public List<VendorMasterResponseDto> getAllVendorList() {
		// TODO Auto-generated method stub
		logger.info("----- VendorMasterServiceImpl getAllVendorList ------");
		
		List<VendorMasterEntity> vendorMasterEntitiesList = vendorMasterRepository.findAll();
		
		return VendorMasterMapper.toVendorMasterResponseDtosList(vendorMasterEntitiesList);
	}

	@Override
	public VendorMasterEntity getVendorMasterEntityByVendorID(String vendorID) {
		// TODO Auto-generated method stub
		return vendorMasterRepository.findById(vendorID).get();
	}

}
