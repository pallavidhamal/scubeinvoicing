package com.scube.invoicing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.RejectedLastIssuanceRecordDto; 
import com.scube.invoicing.entity.LastIssuanceEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.LastIssuanceRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;


@Service
public class LastIssuanceServiceImpl implements LastIssuanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(LastPurchaseReceiptServiceImpl.class);
	
	private XSSFWorkbook workBook;
	
	@Autowired
	LastIssuanceRepository lastIssuanceRepository;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Value("${file.lastissuance.path}") private File inputLastIssuanceFilePath;
	
	@Value("${file.lastissuance.path}") private String inputFilePath;
	
	@Value("${file.rejectedlastissuance.path}") private String rejectedFilePath;
	
	@Override
	public List<LastIssuanceEntity> uploadLastIssuanceData() throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("*****LastIssuanceServiceImpl uploadLastIssuanceData*****");
		
//		File directoryPath = new File(inputLastIssuanceFilePath);
//		File filesList[] = directoryPath.listFiles();
		
		FileInputStream fileInputStream = new FileInputStream(inputLastIssuanceFilePath);
		
		List<LastIssuanceEntity> lastIssuanceEntity = new ArrayList<LastIssuanceEntity>();
		
		List<RejectedLastIssuanceRecordDto> rejectedLastIssuanceRecordsList = new ArrayList<RejectedLastIssuanceRecordDto>();
		
//		for(File file : filesList) {
		
			workBook = new XSSFWorkbook(fileInputStream);
			XSSFSheet workSheet = workBook.getSheetAt(0);
		
			logger.info("-------" + workSheet.getSheetName() + "------" + inputLastIssuanceFilePath.getName());
		
			String withoutExtensionFileName = FilenameUtils.removeExtension(inputLastIssuanceFilePath.getName());
		
			logger.info("File Name without extension : " + withoutExtensionFileName);
/*		
			String fileNameExceptDate = withoutExtensionFileName.substring(0, 12);
			
			String dateFromInputFile = withoutExtensionFileName.substring(14, withoutExtensionFileName.length());
		
			logger.info("File Name with only Date : " + dateFromInputFile + "------" +fileNameExceptDate);
*/		
			int rowCount = workSheet.getLastRowNum();
			int cellCount = workSheet.getRow(0).getLastCellNum();
			int numberOfColumns = workSheet.getRow(0).getPhysicalNumberOfCells();
			
		
			logger.info("Total No of Columns : " + numberOfColumns + " Cell Count : " + cellCount + " Row Count=" + rowCount);
		
			if(inputLastIssuanceFilePath.getName().startsWith("Last 30 Days Issuance") == true) {
		
				if(numberOfColumns < 2) {
			
					throw BRSException.throwException("Error : Incorrect No of Columns");
			
				}

				String excelAssName = "";
				
				Date issueDate = new Date();
				Date recordDateFormat = new Date();
		
				for(int i = 2; i<=rowCount; i++) {
					
					String issueDtString ="";
			
					LastIssuanceEntity lastIssuanceEntityObject = new LastIssuanceEntity();
					RejectedLastIssuanceRecordDto rejectedLastIssuanceObject = new RejectedLastIssuanceRecordDto();
				
					XSSFRow row = workSheet.getRow(i);
					
					logger.info("Correct -------" );
				
					//issueDate = row.getCell(1).getDateCellValue();
				//	String issueDateString = DateUtils.formattedDate(issueDate);
					//logger.info("Issue Date String" + issueDtString + "-----");
				
					if(row != null) {

						excelAssName =  ExcelUtils.checkIfCellBlank(row.getCell(0));
						logger.info(" *---*" + i + "excelAssName=" + excelAssName );
				
						int rowcell = row.getPhysicalNumberOfCells();
						logger.info("rowcell=" + rowcell);
						
						if (DateUtil.isCellDateFormatted(row.getCell(1))) 
						{
						
							issueDate = row.getCell(1).getDateCellValue();
							issueDtString = DateUtils.formattedDate(issueDate);
						}
						else {
							if (row.getCell(1)!=null)
							{
								issueDtString = ExcelUtils.getFormattedCellValue(row.getCell(1));
							}
						}
					
						recordDateFormat = row.getCell(3).getDateCellValue();
						String recordDateInString = DateUtils.formattedDate(recordDateFormat);
					
						logger.info("Record Date in Date and String format -----" + recordDateFormat + "------" + recordDateInString);
					
					
						if(issueDtString==null || issueDtString.equals("") 
							|| row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {
						
							logger.info("Record Date String" + recordDateInString + "-----" + "issue Date " + issueDtString);
								//Date rejectedIssueDate = row.getCell(1).getDateCellValue();
							//	String rejectedIssueRecordDate = DateUtils.formattedDate(rejectedIssueDate);
						
							//	if(row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							
										rejectedLastIssuanceObject.setIssueDate(issueDtString);
							
							//	}
								if(row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							
										rejectedLastIssuanceObject.setRecordDate(recordDateInString);
								}
						
			
								rejectedLastIssuanceObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
								rejectedLastIssuanceObject.setIssueQuantity(ExcelUtils.getFormattedCellValue(row.getCell(2)));
								rejectedLastIssuanceObject.setRecordDate(recordDateInString);
								rejectedLastIssuanceObject.setIssueDate(issueDtString);
								rejectedLastIssuanceObject.setRowNo(String.valueOf(row.getRowNum()+1));
						
								rejectedLastIssuanceRecordsList.add(rejectedLastIssuanceObject);
								logger.info("Rejected Record List ----->>>>" + rejectedLastIssuanceRecordsList);
						
						}
						else {
						
								lastIssuanceEntityObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
								lastIssuanceEntityObject.setIssueQuantity(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(2))));
								
								lastIssuanceEntityObject.setIssueDate(issueDtString);
								if(DateUtil.isCellDateFormatted(row.getCell(3))) {
										logger.info("Cell is Date Formatted");
										lastIssuanceEntityObject.setRecordDate(recordDateInString);
								}
								
								/*if(DateUtil.isCellDateFormatted(row.getCell(1)) ) {
								
										lastIssuanceEntityObject.setIssueDate(DateUtils.formattedDate(issueDate));  						
						
								}	*/	
								
								lastIssuanceEntity.add(lastIssuanceEntityObject);		
						
						}
					
					}
	
				}
				
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByRecordForLastIssuance(DateUtils.formattedDate(recordDateFormat));
				
				if(duplicateEntryCheckList.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputLastIssuanceFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(recordDateFormat));
				
				}
				
				List<Map<String, String>> duplicateEntryCheckList1 = reportRepository.getRecordsByRecordDateForRmInventory(DateUtils.formattedDate(recordDateFormat));
				
				if(duplicateEntryCheckList1.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputLastIssuanceFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(recordDateFormat)+ " in RM inventory control");
				
				}
				

				logger.info("Rejected List ----->>>>" + rejectedLastIssuanceRecordsList);
			
				XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
			
				if(rejectedLastIssuanceRecordsList.size() > 0) {
	
						XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Last Issuance Old");
				
						XSSFRow rejectedRecordSheetRow;
				
						ListIterator<RejectedLastIssuanceRecordDto> rejectedIssuanceListIterator = rejectedLastIssuanceRecordsList.listIterator();
				
						int rowId = 1;
						
						rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
						
						rejectedRecordSheetRow.createCell(0).setCellValue("Material Code");
						rejectedRecordSheetRow.createCell(1).setCellValue("Issue Date");
						rejectedRecordSheetRow.createCell(2).setCellValue("Issue Qauntity");
						rejectedRecordSheetRow.createCell(3).setCellValue("Record Date");
				
						logger.info("Size " + rejectedLastIssuanceRecordsList.size());
				
						while (rejectedIssuanceListIterator.hasNext()) {
					
								logger.info("Checkkkk");
					
								RejectedLastIssuanceRecordDto rejectedLastIssuanceRecordDto = rejectedIssuanceListIterator.next();
					
								rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
					
								rejectedRecordSheetRow.createCell(0).setCellValue(rejectedLastIssuanceRecordDto.getMaterialCode());
								rejectedRecordSheetRow.createCell(1).setCellValue(rejectedLastIssuanceRecordDto.getIssueDate());
								rejectedRecordSheetRow.createCell(2).setCellValue(rejectedLastIssuanceRecordDto.getIssueQuantity());
								rejectedRecordSheetRow.createCell(3).setCellValue(rejectedLastIssuanceRecordDto.getRecordDate());
					
						}	
				
						FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/LastIssuance/Rejected_Last_Issuance.xlsx"));
				
						writeRejectedWorkBook.write(outPutStream);
						outPutStream.close();
				
						//String rejectedFilePath = "C:/Rejected_Records/LastIssuance/Rejected_Last_Issuance.xlsx";
						File rejectedFile = new File(rejectedFilePath);
				
						emailService.sendMailWithAttachment(rejectedFile, inputLastIssuanceFilePath);
				
						logger.info("-----------------" + "Rejected Data Stored in RejectedIssuanceRecord.xlsx" + "---------------");
				
				}
				
			}
		
			lastIssuanceRepository.saveAll(lastIssuanceEntity);
		
			logger.info("-----------------" + "saved in DB" + "---------------");
			
			fileMoveService.moveFilesAfterDataImport(inputFilePath, inputLastIssuanceFilePath.getName());
		
			return lastIssuanceEntity;
		
	}
	

}
