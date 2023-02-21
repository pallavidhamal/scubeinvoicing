package com.scube.invoicing.service;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.incoming.ConfigurationMasterService;
import com.scube.invoicing.entity.ConfigurationMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ConfigurationMasterRepository;
import com.scube.invoicing.util.FileStorageService;

@Service
public class ConfigurationMasterServiceImpl implements ConfigurationMasterService {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	ConfigurationMasterRepository configurationMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationMasterServiceImpl.class);

	@Override
	public String uploadLogoAndSave(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		logger.info("------- ConfigurationMasterServiceImpl uploadLogoAndSave -------");
		
		String fileSubPath = "LOGO";
		String logoFilePath = null;
		logoFilePath = fileStorageService.storeFile(multipartFile , fileSubPath);
		
		logger.info("Logo File Path -----" + logoFilePath);
		
		ConfigurationMasterEntity configurationMasterEntity = new ConfigurationMasterEntity();
		configurationMasterEntity.setIsdeleted("N");
		configurationMasterEntity.setLogoPath(logoFilePath);
		
		configurationMasterRepository.save(configurationMasterEntity);
		
		logger.info("--------- Logo File Uploaded and Saved Successfully ----------------");
		
		return logoFilePath;
	}

	@Override
	public Resource getUploadedLogoFile(String companyID) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("------- ConfigurationMasterServiceImpl getUploadedLogoFile -------");
		
		if(companyID == "" || companyID.trim().isEmpty()) {
			throw BRSException.throwException("Error : Company ID cannot be blank or empty");
		}
		
		String fileName = "";
		Path filePath = null ;
		
		ConfigurationMasterEntity configurationMasterEntity = configurationMasterRepository.findById(companyID).get();
		
		if(configurationMasterEntity == null) {
			throw BRSException.throwException("Error : Logo is not Uploaded.");
		}
		/*
		try {
			filePath = fileStorageService.loadFileAsResource(configurationMasterEntity.getLogoPath());
			return fileStorageService.getFileResourceimg(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("File not found " + fileName,e);
		}
		*/
		return null;
	}

	@Override
	public String editUploadedLogoAndSave(MultipartFile multipartFile, String configID) {
		// TODO Auto-generated method stub
		
		logger.info("------- ConfigurationMasterServiceImpl editUploadedLogoAndSave -------");
		
		String fileSubPath = "LOGO";
		String logoFilePath = null;
		logoFilePath = fileStorageService.storeFile(multipartFile , fileSubPath);
		
		logger.info("Logo File Path -----" + logoFilePath);
		
		ConfigurationMasterEntity configurationMasterEntity = configurationMasterRepository.findById(configID).get();
		configurationMasterEntity.setIsdeleted("N");
		configurationMasterEntity.setLogoPath(logoFilePath);
		
		configurationMasterRepository.save(configurationMasterEntity);
		
		logger.info("--------- Logo File Edited and Saved Successfully ----------------");
		
		return logoFilePath;
	}

}
