package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.core.io.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StreamUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.scube.invoicing.util.FileStorageService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/images"}, produces = APPLICATION_JSON_VALUE)
public class ImageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Value("${file.dumpdata.path}") private String dumpDataFilePath;
	
	 @GetMapping("/getImage/{fname}")
	 public ResponseEntity<byte[]> getFileFromStorageSelection(@PathVariable  String fname) throws Exception {
		 
			 
		String fpath=fname;
		//"Dump_Data0.15871067114650106.xlsx";

		//D:\var\lib\tomcat9\webapps\DumpData
		 
		 Resource res =  fileStorageService.loadFileAsResource(fpath);
			 
			 byte[] bytes = StreamUtils.copyToByteArray(res.getInputStream());
			 
			 MediaType mediaType = null; 
			 String ext = FilenameUtils.getExtension(res.getFilename());
		        
		        if(ext.equalsIgnoreCase("xlsx") ) {
		        
		        	//mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		        	mediaType = MediaType.APPLICATION_OCTET_STREAM;
		        	
		        	
		        }
		        else {
		      //  	mediaType = MediaType.IMAGE_JPEG ;
		        }
		        	
		        
		        return ResponseEntity.ok()
		                             .contentType(mediaType)
		                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fname)
		                             .body(bytes);

    }
}
