package com.scube.invoicing.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileMoveService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileMoveService.class);
	
	@Value("${folder.archivedexcelfile.path}") private String archivedExcelFilesPath;
	
	public void moveFilesAfterDataImport(String sourcePath, String fileName) throws IOException {
		// TODO Auto-generated method stub
		
		logger.info("------------------- " + "FileMoveService moveFilesAfterDataImport" + " ---------------------");
		
		logger.info("------------------- " + fileName + " File Moving to Archived Folder After Data Import" + " ---------------------");
		
		Date currentDate = new Date();
		String currentDateInString = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);
//		String currentDateInString = "04-10-2022";
		String archivedFileDest = archivedExcelFilesPath + currentDateInString + "/" + fileName;
		
		logger.info("--------------" + "Folder Name   " + currentDateInString + "-------------" + "Archived File Destination Path " + archivedFileDest);
		
		File newFolderFile = new File(archivedExcelFilesPath + currentDateInString);
		
		boolean checkFolderCreation = newFolderFile.mkdirs();

		if(checkFolderCreation) {
			logger.info("-----------" + "Folder for Current Date Created Successfully" + "--------------");
		}
		
		Path tempPath = null;	
		tempPath = Files.move(Paths.get(sourcePath), Paths.get(archivedFileDest));
		
		if(tempPath != null) {
			
			logger.info("-----------" + fileName + " File Moved Successfully" + "--------------");
			
		}
		else {
			
			logger.info("-----------" + fileName + " File Not Moved" + "--------------");
			
		}
		
	}

}