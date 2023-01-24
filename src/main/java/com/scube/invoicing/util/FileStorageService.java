package com.scube.invoicing.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.repository.DumpDataRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class FileStorageService {
	
	private Path fileStorageLocation;
	
	private final String fileBaseLocation;
	
	private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
	
	@Autowired
	DumpDataRepository dumpDataRepository;
	
	@Autowired
	ReportResponseDto reportResponseDto;
	
	//@Value("${file.dumpdata.path}") private String dumpDataFilePath;

	public FileStorageService(FileStorageProperties fileStorageProperties) 
	  {    
	  this.fileBaseLocation = fileStorageProperties.getUploadDir();
	  }
	 
	public Resource loadFileAsResource(String fpath) throws Exception {
		// TODO Auto-generated method stub
		
		 logger.info("--------path found--------------" + fpath);
		
		String fileName ="";
	 	String newPAth = ""; 
		Path filePath = null ;
		
		try {
        	
				newPAth = this.fileBaseLocation;
				logger.info("newPath===="+ newPAth);
				
      		fileStorageLocation = Paths.get(newPAth).toAbsolutePath().normalize();
      		logger.info("fileStorageLocation===="+ fileStorageLocation);
	            
	            filePath = fileStorageLocation.resolve(fpath).normalize();
	            logger.info("filePath===="+ filePath);
			
			
		return getFileResource(filePath);

	}catch (Exception ex) {
        throw new Exception("File not found " + fileName, ex);
    }

}
	
	 public Resource getFileResource(Path filePath) throws Exception {
		 
		 Resource resource  = new UrlResource(filePath.toUri());
           if(resource.exists()) {
           	logger.info("Inside IF(resource.exists)");
               return resource;
           } else {
           	logger.info("Inside else()");
               throw new Exception("File not found " + filePath);
           }
	 
	 }
}
