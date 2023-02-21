package com.scube.invoicing.dto.incoming;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ConfigurationMasterService {

	String uploadLogoAndSave(MultipartFile multipartFile);
	
	String editUploadedLogoAndSave(MultipartFile multipartFile, String configID);
	
	public Resource getUploadedLogoFile(String companyID) throws Exception;
	
}
