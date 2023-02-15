package com.scube.invoicing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.dto.incoming.ConfigurationMasterService;
import com.scube.invoicing.entity.ConfigurationMasterEntity;
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

}
