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

import com.scube.invoicing.dto.RejectedLastPurchaseRecordDto;
import com.scube.invoicing.entity.LastPurchaseReceiptEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.LastPurchaseReceiptRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;

@Service
public class LastPurchaseReceiptServiceImpl implements LastPurchaseReceiptService{
	
	private static final Logger logger = LoggerFactory.getLogger(LastPurchaseReceiptServiceImpl.class);
	
	private XSSFWorkbook workBook;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Autowired
	LastPurchaseReceiptRepository lastPurchaseReceiptRepository;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Value("${file.lastpurchasereceipt.path}") private File inputLastPurchaseReceiptFilePath;
	
	@Value("${file.lastpurchasereceipt.path}") private String inputFilePath;
	
	@Value("${folder.archivedexcelfile.path}") private String archivedExcelFilesPath;
	
	@Value("${file.rejectedlastpurchaserecipt.path}") private String rejectedFilePath2;
	

	@Override
	public List<LastPurchaseReceiptEntity> uploadLastPurchaseReceiptData() throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("*****LastPurchaseReceiptServiceImpl uploadLastPurchaseReceipt*****");
		
		/*
		 * File directoryPath = new File(inputLastPurchaseReceiptFilePath);
		 * 
		 * File filesList[] = directoryPath.listFiles();
		 */
		
		FileInputStream fileInputStream = new FileInputStream(inputLastPurchaseReceiptFilePath);
		
		List<LastPurchaseReceiptEntity> lastPurchaseReceiptEntities = new ArrayList<LastPurchaseReceiptEntity>();
		
		List<RejectedLastPurchaseRecordDto> rejectedLastPurchaseRecordList = new ArrayList<RejectedLastPurchaseRecordDto>();
		
/*		for (File file : filesList) {*/
		
		workBook = new XSSFWorkbook(fileInputStream);
		XSSFSheet workSheet = workBook.getSheetAt(0);
			
		logger.info("-------" + workSheet.getSheetName() + "------" + inputLastPurchaseReceiptFilePath.getName());
			
		String withoutExtensionFileName = FilenameUtils.removeExtension(inputLastPurchaseReceiptFilePath.getName());
			
		logger.info("File Name without extension : " + withoutExtensionFileName);

/*
			String fileNameExceptDate = withoutExtensionFileName.substring(0, 12);
			
			String dateFromInputFile = withoutExtensionFileName.substring(22, withoutExtensionFileName.length());
			
			logger.info("File Name with only Date : " + dateFromInputFile + "------" +fileNameExceptDate);
*/			
		
		int rowCount = workSheet.getLastRowNum();
		int cellCount = workSheet.getRow(0).getLastCellNum();
		int numberOfColumns = workSheet.getRow(0).getPhysicalNumberOfCells();
			
		if(inputLastPurchaseReceiptFilePath.getName().startsWith("Last 7 Days Purchase Receipt") == true) {
			
				if(numberOfColumns < 2) {
				
					throw BRSException.throwException("Error : Incorrect No of Columns");
				
				}
		
				logger.info("Total No of Columns : " + numberOfColumns + "Cell Count : " + cellCount + "Row Count=" + rowCount);
		
				String excelReceiptName = "";
				
				Date reportDate = new Date();
				Date receiptDate = new Date();
		
				for(int i = 2; i<=rowCount; i++) {
					
						String receiptDtString= "";
			
						LastPurchaseReceiptEntity lastPurchaseReceiptEntityObject = new LastPurchaseReceiptEntity();
						RejectedLastPurchaseRecordDto RejectedLastPurchaseReceiptObject = new RejectedLastPurchaseRecordDto();
				
						XSSFRow row = workSheet.getRow(i);
				
						logger.info("Correct---------");
				
						//reportDate = row.getCell(3).getDateCellValue();
						//String reportDateString = DateUtils.formattedDate(reportDate);
						//logger.info("Issue Date String" + reportDateString + "-----");
			
				logger.info("----------Total No of Columns : " + numberOfColumns + "Cell Count : " + cellCount + "-----------------Row Count=" + rowCount+ "---i="+i);
				
						if(row != null) {
				
							excelReceiptName =  ExcelUtils.checkIfCellBlank(row.getCell(12));
							logger.info(" *---*" + i + "excelReceiptName" + excelReceiptName + "--" + row.getCell(0));
				
							int rowcell = row.getPhysicalNumberOfCells();
							logger.info("rowcell=" + rowcell);	
							
							if (DateUtil.isCellDateFormatted(row.getCell(1))) 
							{
								receiptDate = row.getCell(1).getDateCellValue();
								receiptDtString = DateUtils.formattedDate(receiptDate);
							}
							else {
								if (row.getCell(1)!=null)
								{
									receiptDtString = ExcelUtils.getFormattedCellValue(row.getCell(1));
								}
							}

							Date recordDateFormat = row.getCell(3).getDateCellValue();
							String recordDateInString = new SimpleDateFormat("yyyy-MM-dd").format(recordDateFormat);
					
							logger.info("Record Date in Date and String format -----" + recordDateFormat + "------" + recordDateInString);
					
							if (row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || receiptDtString == null || receiptDtString.equals("")
									|| row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {
								
								logger.info("Record Date String" + recordDateInString + "-----" + "receipt Date " + receiptDtString);
								
									//Date rejectedLastPurchaseReceipt = row.getCell(1).getDateCellValue();
									//String rejectedLastPurchaseReceiptRecordDate = DateUtils.formattedDate(rejectedLastPurchaseReceipt);
									
									//if (row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC) {
										
										RejectedLastPurchaseReceiptObject.setReceiptDate(receiptDtString);
										
									//}
									
									if (row.getCell(3).getCellType()== Cell.CELL_TYPE_NUMERIC) {
							
											RejectedLastPurchaseReceiptObject.setRecordDate(recordDateInString);
									}
						
									RejectedLastPurchaseReceiptObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
									RejectedLastPurchaseReceiptObject.setReceiptDate(receiptDtString);
									RejectedLastPurchaseReceiptObject.setReceiptQuantity(ExcelUtils.getFormattedCellValue(row.getCell(2)));
									RejectedLastPurchaseReceiptObject.setRecordDate(recordDateInString);
									//RejectedLastPurchaseReceiptObject.setReceiptDate(ExcelUtils.getFormattedCellValue(row.getCell(3)));
									RejectedLastPurchaseReceiptObject.setRow(String.valueOf(row.getRowNum()+1));
						
									rejectedLastPurchaseRecordList.add(RejectedLastPurchaseReceiptObject);
									logger.info("Rejected Record List ---------->>>>>" + rejectedLastPurchaseRecordList);
							}
							else {
						
									//Date receiptDate = row.getCell(1).getDateCellValue();
									//String receiptDateString = DateUtils.formattedDate(receiptDate);
									//logger.info("Issue Date String" + receiptDateString + "-----");
						
									lastPurchaseReceiptEntityObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
									lastPurchaseReceiptEntityObject.setReceiptQuantity(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(2))));
									
									lastPurchaseReceiptEntityObject.setReceiptDate(receiptDtString);
									if(DateUtil.isCellDateFormatted(row.getCell(3))) {
											lastPurchaseReceiptEntityObject.setRecordDate(recordDateInString);
									}
						
									/*if(DateUtil.isCellDateFormatted(row.getCell(1))) {
							
											lastPurchaseReceiptEntityObject.setReceiptDate(receiptDateString);
									}*/
						
									lastPurchaseReceiptEntities.add(lastPurchaseReceiptEntityObject);
							}
						}
				}				
				
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByRecordDateForLastPurchase(DateUtils.formattedDate(reportDate));
				
				if(duplicateEntryCheckList.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputLastPurchaseReceiptFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(reportDate));
			
				}
				
				List<Map<String, String>> duplicateEntryCheckList1 = reportRepository.getRecordsByRecordDateForRmInventory(DateUtils.formattedDate(reportDate));
				
				if(duplicateEntryCheckList1.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputLastPurchaseReceiptFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(reportDate)+ " in RM inventory control");
			
				}
		
				logger.info("Rejected List ----->" + rejectedLastPurchaseRecordList);
			
				logger.info("-----------------------" + rejectedLastPurchaseRecordList.size() + "-----------");
			
				if(rejectedLastPurchaseRecordList.size() > 0) {
				
						XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
	
						XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Last Purchase Old");
				
						XSSFRow rejectedRecordSheetRow;
				
						ListIterator<RejectedLastPurchaseRecordDto> rejectedLastPurchaseListIterator = rejectedLastPurchaseRecordList.listIterator();
				
						int rowId = 1;
						
						rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
						
						rejectedRecordSheetRow.createCell(0).setCellValue("Material Code");
						rejectedRecordSheetRow.createCell(1).setCellValue("Receipt Date");
						rejectedRecordSheetRow.createCell(2).setCellValue("Receipt Quantity");
						rejectedRecordSheetRow.createCell(3).setCellValue("Record Date");
				
						logger.info("Size " + rejectedLastPurchaseRecordList.size());
				
						while (rejectedLastPurchaseListIterator.hasNext()) {
					
							logger.info("Checkkkk");
					
							RejectedLastPurchaseRecordDto rejectedLastPurchaseRecordDto = rejectedLastPurchaseListIterator.next();
					
							rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
					
							rejectedRecordSheetRow.createCell(0).setCellValue(rejectedLastPurchaseRecordDto.getMaterialCode());
							rejectedRecordSheetRow.createCell(1).setCellValue(rejectedLastPurchaseRecordDto.getReceiptDate());
							rejectedRecordSheetRow.createCell(2).setCellValue(rejectedLastPurchaseRecordDto.getReceiptQuantity());
							rejectedRecordSheetRow.createCell(3).setCellValue(rejectedLastPurchaseRecordDto.getRecordDate());
						}
			
						FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/LastPurchase/Rejected_Last_Purchase_Receipt.xlsx"));
						writeRejectedWorkBook.write(outPutStream);
						outPutStream.close();
				
						//String rejectedFilePath2 = "C:/Rejected_Records/LastPurchase/Rejected_Last_Purchase_Receipt.xlsx";
						File rejectedFile = new File(rejectedFilePath2);
						
						emailService.sendMailWithAttachment(rejectedFile, inputLastPurchaseReceiptFilePath);
				
						logger.info("-----------------" + "Rejected Data Stored in RejectedLastPurchaseReceipt.xlsx" + "---------------");
				
				}
				
		}
		
		lastPurchaseReceiptRepository.saveAll(lastPurchaseReceiptEntities);
	
		logger.info("-----------------" + "saved in DB" + "---------------");
		
		fileMoveService.moveFilesAfterDataImport(inputFilePath, inputLastPurchaseReceiptFilePath.getName());
		
		return lastPurchaseReceiptEntities;
		
	}
	
}
