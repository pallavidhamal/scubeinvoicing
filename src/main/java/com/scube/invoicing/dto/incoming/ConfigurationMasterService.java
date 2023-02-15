package com.scube.invoicing.dto.incoming;

import org.springframework.web.multipart.MultipartFile;

public interface ConfigurationMasterService {

	String uploadLogoAndSave(MultipartFile multipartFile);
	
}
