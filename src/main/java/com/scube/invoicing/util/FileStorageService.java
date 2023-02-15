package com.scube.invoicing.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.exception.FileStorageException;
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

		}
		catch (Exception ex) {
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
	 
	 public String storeFile(MultipartFile multipartFile, String logoFor) {
		 
		logger.info("------ FileStorageService storeFile ------");
		 
		Date date = new Date(System.currentTimeMillis());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		String extension = fileName.split("\\.")[1];
		String fileNewName = logoFor + "_" + date.getTime() + "." + extension;
		
		logger.info("File New Name ------" + fileNewName);
		
		try {
			if(fileNewName.contains("..")) {
				throw new FileStorageException("Sorry! File Name contains invalid path sequence!");
			}
			String newPath;
			if(logoFor.equals("LOGO")) {
				newPath = this.fileBaseLocation +"/"+UploadPathContUtils.FILE_LOGO_DIR; 
				logger.info("---- New Path -----" + newPath);
				
				this.fileStorageLocation = Paths.get(newPath).toAbsolutePath().normalize();
				Files.createDirectories(this.fileStorageLocation);
				
				Path targetLocation = this.fileStorageLocation.resolve(fileNewName);
				Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			}
			return String.valueOf(fileNewName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
		 
	 }
}
