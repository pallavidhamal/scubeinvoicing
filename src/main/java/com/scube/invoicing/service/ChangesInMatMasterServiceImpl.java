package com.scube.invoicing.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.ChangesInMatMasterDto;
import com.scube.invoicing.dto.RejectedCurrentOrderPipelineRecordDto;
import com.scube.invoicing.entity.ChangesInMatMasterEntity;
//import com.scube.invoicing.entity.CurrentOrdersInPipelineEnitty;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ChangesInMatMasterRepository;
import com.scube.invoicing.util.ExcelUtils;
import com.scube.invoicing.util.StringNullEmpty;

@Service
public class ChangesInMatMasterServiceImpl implements ChangesInMatMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChangesInMatMasterServiceImpl.class);
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ChangesInMatMasterRepository changesInMatMasterRepository;
	
	@Value("${file.changesinmatmaster.path}") private File changesInMatMasterFilePath;
	   

		@Override
		public List<ChangesInMatMasterEntity> getChangesInMatMasterByLast7Days() throws Exception {
			// TODO Auto-generated method stub
			
			 List<ChangesInMatMasterEntity> changesInMatMasterEntities = changesInMatMasterRepository.getChangesInMaterialMasterEntityByAuditDateAndTime();
			 
			 if(changesInMatMasterEntities.size() == 0) {
				 throw BRSException.throwException("Error : Records not present");
			 }
			 
			 logger.info("-----------------------" + changesInMatMasterEntities.size());
		
	    	XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
	    	
	    	XSSFSheet changesInMatMasterSheet = writeRejectedWorkBook.createSheet("Changes In Material Master");
			
			XSSFRow changesInMatMasterSheetRow;
			int rowId = 1;
			
			changesInMatMasterSheetRow = changesInMatMasterSheet.createRow(0);
			
			changesInMatMasterSheetRow.createCell(0).setCellValue("AuditDateAndTime");
			changesInMatMasterSheetRow.createCell(1).setCellValue("AuditActions");
			changesInMatMasterSheetRow.createCell(2).setCellValue("Id");
			changesInMatMasterSheetRow.createCell(3).setCellValue("materialCode Old");
			changesInMatMasterSheetRow.createCell(4).setCellValue("materialCode New");
			changesInMatMasterSheetRow.createCell(5).setCellValue("materialDescription Old");
			changesInMatMasterSheetRow.createCell(6).setCellValue("materialDescription New");
			changesInMatMasterSheetRow.createCell(7).setCellValue("regInter Old");
			changesInMatMasterSheetRow.createCell(8).setCellValue("regInter New");
			changesInMatMasterSheetRow.createCell(9).setCellValue("keptInstock Old");
			changesInMatMasterSheetRow.createCell(10).setCellValue("keptInstock New");
			changesInMatMasterSheetRow.createCell(11).setCellValue("readilyAvailable Old");
			changesInMatMasterSheetRow.createCell(12).setCellValue("readilyAvailable New");
			changesInMatMasterSheetRow.createCell(13).setCellValue("avgconsPerdayunits Old");
			changesInMatMasterSheetRow.createCell(14).setCellValue("avgconsPerdayunits New");
			changesInMatMasterSheetRow.createCell(15).setCellValue("leadTime Old");
			changesInMatMasterSheetRow.createCell(16).setCellValue("leadTime New");
			changesInMatMasterSheetRow.createCell(17).setCellValue("transTime Old");
			changesInMatMasterSheetRow.createCell(18).setCellValue("transTime New");
			changesInMatMasterSheetRow.createCell(19).setCellValue("supplierMoq Old");
			changesInMatMasterSheetRow.createCell(20).setCellValue("supplierMoq New");
			changesInMatMasterSheetRow.createCell(21).setCellValue("Eoq Old");
			changesInMatMasterSheetRow.createCell(22).setCellValue("Eoq New");
			changesInMatMasterSheetRow.createCell(23).setCellValue("safetyFactor Old");
			changesInMatMasterSheetRow.createCell(24).setCellValue("safetyFactor New");
			changesInMatMasterSheetRow.createCell(25).setCellValue("uOm Old");
			changesInMatMasterSheetRow.createCell(26).setCellValue("uOm New");
			changesInMatMasterSheetRow.createCell(27).setCellValue("fkUom Old");
			changesInMatMasterSheetRow.createCell(28).setCellValue("fkUom New");
			changesInMatMasterSheetRow.createCell(29).setCellValue("ss norm Old");
			changesInMatMasterSheetRow.createCell(30).setCellValue("ss norm New");
			changesInMatMasterSheetRow.createCell(31).setCellValue("fs Days Old");
			changesInMatMasterSheetRow.createCell(32).setCellValue("fs days New");
			
		 for(ChangesInMatMasterEntity changesInMatMasterEntity : changesInMatMasterEntities ) {
				
				
				changesInMatMasterSheetRow = changesInMatMasterSheet.createRow(rowId++);
				
				changesInMatMasterSheetRow.createCell(1).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getAuditActions()));
				changesInMatMasterSheetRow.createCell(0).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getAuditDateAndTime()));
				changesInMatMasterSheetRow.createCell(2).setCellValue(changesInMatMasterEntity.getId());
				changesInMatMasterSheetRow.createCell(3).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getMaterialDescriptionOld()));
				changesInMatMasterSheetRow.createCell(4).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getMaterialCodeNew()));
				changesInMatMasterSheetRow.createCell(5).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getMaterialDescriptionOld()));
				changesInMatMasterSheetRow.createCell(6).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getMaterialDescriptionNew()));
				changesInMatMasterSheetRow.createCell(7).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getRegInterOld()));
				changesInMatMasterSheetRow.createCell(8).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getRegInterNew()));
				changesInMatMasterSheetRow.createCell(9).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getKeptInstockOld()));
				changesInMatMasterSheetRow.createCell(10).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getKeptInstockNew()));
				changesInMatMasterSheetRow.createCell(11).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getReadilyAvailableOld()));
				changesInMatMasterSheetRow.createCell(12).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getReadilyAvailableNew()));
				changesInMatMasterSheetRow.createCell(13).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getAvgconsPerdayunitsOld()));
				changesInMatMasterSheetRow.createCell(14).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getAvgconsPerdayunitsNew()));
				changesInMatMasterSheetRow.createCell(15).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getLeadTimeOld()));
				changesInMatMasterSheetRow.createCell(16).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getLeadTimeNew()));
				changesInMatMasterSheetRow.createCell(17).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getTransTimeOld()));
				changesInMatMasterSheetRow.createCell(18).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getTransTimeNew()));
				changesInMatMasterSheetRow.createCell(19).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSupplierMoqOld()));
				changesInMatMasterSheetRow.createCell(20).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSupplierMoqNew()));
				changesInMatMasterSheetRow.createCell(21).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getEoqOld()));
				changesInMatMasterSheetRow.createCell(22).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getEoqNew()));
				changesInMatMasterSheetRow.createCell(23).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSafetyFactorOld()));
				changesInMatMasterSheetRow.createCell(24).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSafetyFactorNew()));
				changesInMatMasterSheetRow.createCell(25).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getUOmOld()));
				changesInMatMasterSheetRow.createCell(26).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getUOmNew()));
				changesInMatMasterSheetRow.createCell(27).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getFkUomOld()));
				changesInMatMasterSheetRow.createCell(28).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getFkUomNew()));
				changesInMatMasterSheetRow.createCell(29).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSsNormUnitsOld()));
				changesInMatMasterSheetRow.createCell(30).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getSsNormUnitsNew()));
				changesInMatMasterSheetRow.createCell(31).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getFsDaysOld()));
				changesInMatMasterSheetRow.createCell(32).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(changesInMatMasterEntity.getFsDaysNew()));
				
			}
	    
		 
		 	File changesInMatMasterFilePath = new File("C:/ChangesInMaterialMaster/Changes_In_Material_Master"+Math.random()+".xlsx");
		 
			FileOutputStream outPutStream = new FileOutputStream(changesInMatMasterFilePath);
			writeRejectedWorkBook.write(outPutStream);
			outPutStream.close();
			
			if(changesInMatMasterEntities.size() > 0) {
			
			emailService.sendMailWithAttachment(changesInMatMasterFilePath);
		
			}
			logger.info("-----------------" + "Updated Records Stored in changes_In_Material_Master.xlsx" + "---------------");
			
			changesInMatMasterRepository.saveAll(changesInMatMasterEntities);
			
			return changesInMatMasterEntities;
			
		}
}
	 


	
	    
	    


