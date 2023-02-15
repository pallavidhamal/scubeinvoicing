package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.ServiceMasterResponseDto;
import com.scube.invoicing.dto.incoming.ServiceMasterIncomingDto;
import com.scube.invoicing.dto.mapper.ServiceMasterMapper;
import com.scube.invoicing.entity.ServiceMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.ServiceMasterRepository;

@Service
public class ServiceMasterDetailsServiceImpl implements ServiceMasterDetailsService {
	
	@Autowired
	ServiceMasterRepository serviceMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceMasterDetailsServiceImpl.class);

	@Override
	public boolean addServiceInfoDetails(@Valid ServiceMasterIncomingDto serviceMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- ServiceMasterDetailsServiceImpl addServiceInfoDetails ------");
		
		if(serviceMasterIncomingDto.getServiceName() == "" || serviceMasterIncomingDto.getServiceName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Service Name cannot be empty or blank");
		}
		
		ServiceMasterEntity duplicateCheckEntity = serviceMasterRepository.findByServiceName(serviceMasterIncomingDto.getServiceName());
		
		if(duplicateCheckEntity != null) {
			throw BRSException.throwException(EntityType.SERVICE, ExceptionType.ALREADY_EXIST, serviceMasterIncomingDto.getServiceName());
		}
		
		ServiceMasterEntity serviceMasterEntity = new ServiceMasterEntity();
		
		serviceMasterEntity.setServiceName(serviceMasterIncomingDto.getServiceName());
		serviceMasterEntity.setIsdeleted("N");
		serviceMasterEntity.setStatus("ACTIVE");
		
		serviceMasterRepository.save(serviceMasterEntity);
		
		return true;
	}

	@Override
	public boolean updateServiceInfoDetails(@Valid ServiceMasterIncomingDto serviceMasterIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- ServiceMasterDetailsServiceImpl updateServiceInfoDetails ------");
		
		if(serviceMasterIncomingDto.getServiceName() == "" || serviceMasterIncomingDto.getServiceName().trim().isEmpty()) {
			throw BRSException.throwException("Error : Service Name cannot be empty or blank");
		}
		
		ServiceMasterEntity serviceMasterEntity = serviceMasterRepository.findById(serviceMasterIncomingDto.getServiceID()).get();
		
		if(serviceMasterEntity == null) {
			throw BRSException.throwException(EntityType.SERVICE, ExceptionType.ENTITY_NOT_FOUND, serviceMasterIncomingDto.getServiceID());
		}
		
		serviceMasterEntity.setServiceName(serviceMasterIncomingDto.getServiceName());
		serviceMasterEntity.setIsdeleted("N");
		serviceMasterEntity.setStatus(serviceMasterIncomingDto.getServiceStatus());
		
		serviceMasterRepository.save(serviceMasterEntity);
		
		return true;
	}

	@Override
	public boolean deleteServiceInfoByServiceID(String serviceID) {
		// TODO Auto-generated method stub
		
		logger.info("----- ServiceMasterDetailsServiceImpl deleteServiceInfoByServiceID ------");
		
		ServiceMasterEntity serviceMasterEntity = serviceMasterRepository.findById(serviceID).get();
		
		if(serviceMasterEntity == null) {
			throw BRSException.throwException(EntityType.SERVICE, ExceptionType.ENTITY_NOT_FOUND, serviceID);
		}
		
		serviceMasterRepository.delete(serviceMasterEntity);
		
		return true;
	}

	@Override
	public ServiceMasterResponseDto getServiceInfoByServiceID(String serviceID) {
		// TODO Auto-generated method stub
		
		logger.info("----- ServiceMasterDetailsServiceImpl getServiceInfoByServiceID ------");
		
		ServiceMasterEntity serviceMasterEntity = serviceMasterRepository.findByIdAndStatus(serviceID, "ACTIVE");
		
		if(serviceMasterEntity == null) {
			throw BRSException.throwException(EntityType.SERVICE, ExceptionType.ENTITY_NOT_FOUND, serviceID);
		}
		
		return ServiceMasterMapper.toServiceMasterResponseDto(serviceMasterEntity);
	}

	@Override
	public List<ServiceMasterResponseDto> getAllServiceInfo() {
		// TODO Auto-generated method stub
		
		logger.info("----- ServiceMasterDetailsServiceImpl getAllServiceInfo ------");
		
		List<ServiceMasterEntity> serviceMasterEntity = serviceMasterRepository.findByStatus("ACTIVE");
		
		if(serviceMasterEntity.size() == 0) {
			throw BRSException.throwException("Error : NO Active Service Records present");
		}
		
		return ServiceMasterMapper.toAllServiceDataList(serviceMasterEntity);
	}

}
