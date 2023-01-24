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
import java.util.TreeMap;

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

import com.scube.invoicing.dto.RejectedCurrentOrderPipelineRecordDto;
import com.scube.invoicing.entity.CurrentOrdersInPipelineSummaryEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CurrentOrdersInPipelineRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;

@Service
public class CurrentOrderPipelineServiceImpl implements CurrentOrdersInPipelineService{

	private static final Logger logger = LoggerFactory.getLogger(CurrentOrderPipelineServiceImpl.class);
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CurrentOrdersInPipelineRepository currentOrdersInPipelineRepository;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Value("${file.currentorderpipeline.path}") private File inputCurrentOrderInPipelineFilePath;
	
	@Value("${file.currentorderpipeline.path}") private String inputCurrentOrderSummaryPath;
	
	@Value("${file.rejectedcurrentorderpipelinesummary.path}") private String rejectedFilePath1;

	private XSSFWorkbook workBook;
	
	public List<CurrentOrdersInPipelineSummaryEntity> getCurrentOrdersInPipelineSummaryExcelFileData () throws Exception {
		
		logger.info("*****CurrentOrderPipelineServiceImpl getCurrentOrdersInPipelineSummaryExcelFileData*****");
/*		
		File directoryPath = new File(inputCurrentOrderInPipelineFilePath); 
	 
		File filesList[] = directoryPath.listFiles();
*/		
		FileInputStream fileInputStream = new FileInputStream(inputCurrentOrderInPipelineFilePath);
		
		List<CurrentOrdersInPipelineSummaryEntity> currentOrdersInPipelineEntityList = new ArrayList<CurrentOrdersInPipelineSummaryEntity>();
		
		List<RejectedCurrentOrderPipelineRecordDto> rejectedCurrentOrderPipelineRecordList = new ArrayList<RejectedCurrentOrderPipelineRecordDto>();
		
//		for (File file : filesList) {
		
			workBook = new XSSFWorkbook(fileInputStream);  
		
		//workbook = new XSSFWorkbook(currentOrderExcelFile.getInputStream());
			XSSFSheet worksheet = workBook.getSheetAt(0);
		
			logger.info("-------" + worksheet.getSheetName() + "------" + inputCurrentOrderInPipelineFilePath.getName());
		
			String withoutExtensionFileName = FilenameUtils.removeExtension(inputCurrentOrderInPipelineFilePath.getName());
		
			logger.info("File Name without extension : " + withoutExtensionFileName);

/*
		String fileNameExceptDate = withoutExtensionFileName.substring(0, 12);
		
		String dateFromInputFile = withoutExtensionFileName.substring(27, withoutExtensionFileName.length());
		
		logger.info("File Name with only Date : " + dateFromInputFile + "------" +fileNameExceptDate);
*/
		
			int rowcnt = worksheet.getLastRowNum();
			System.out.println(rowcnt + "-----");
			int numberOfColumns = worksheet.getRow(0).getPhysicalNumberOfCells();
		
			if(inputCurrentOrderInPipelineFilePath.getName().startsWith("Current Orders in Pipeline (Summary)") == true) {
				
				if(numberOfColumns < 2) {
			
					throw BRSException.throwException("Error : Incorrect No of Columns");
			
				}
		
				String excelName = "";
			
				Date issueDate = new Date();
				for (int i = 2; i <= rowcnt; i++) {
			
					CurrentOrdersInPipelineSummaryEntity currentOrdersInPipelineEntityObject = new CurrentOrdersInPipelineSummaryEntity();
			
					RejectedCurrentOrderPipelineRecordDto  RejectedCurrentOrderPipelineObject = new RejectedCurrentOrderPipelineRecordDto();
					
					XSSFRow row = worksheet.getRow(i);
					logger.info("Correct -------" );
			
					issueDate = row.getCell(2).getDateCellValue();
					String issueDateString = DateUtils.formattedDate(issueDate);
					logger.info("Issue Date String" + issueDateString + "-----");
			
					if (row != null) {
				
						excelName = ExcelUtils.checkIfCellBlank(row.getCell(0));
						logger.info(" *---*" + i + "excelName=" + excelName);
				
						int rowcell = row.getPhysicalNumberOfCells();
						logger.info("rowcell=" + rowcell);
				
						Date recordDateFormat = row.getCell(2).getDateCellValue();
						String recordDateInString = new SimpleDateFormat("yyyy-MM-dd").format(recordDateFormat);
				
						logger.info("Record Date in Date and String format -----" + recordDateFormat + "------" + recordDateInString);
				
						if(row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(1).getCellType()== Cell.CELL_TYPE_BLANK){
						
							Date rejectedIssueDatePipeline = row.getCell(2).getDateCellValue();
							String rejectedIssueRecoedDatePipeline = DateUtils.formattedDate(rejectedIssueDatePipeline);
					
							if(row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						
								RejectedCurrentOrderPipelineObject.setRecordDate(rejectedIssueRecoedDatePipeline);
							}
					
							RejectedCurrentOrderPipelineObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
							RejectedCurrentOrderPipelineObject.setOrderUnits(ExcelUtils.getFormattedCellValue(row.getCell(1)));
							RejectedCurrentOrderPipelineObject.setRecordDate(recordDateInString);
							RejectedCurrentOrderPipelineObject.setRowNo(String.valueOf(row.getRowNum()+1));
					
							rejectedCurrentOrderPipelineRecordList.add(RejectedCurrentOrderPipelineObject);
							logger.info("Rejected Record List ----->>>>" + rejectedCurrentOrderPipelineRecordList);
						}
						else {
					
							currentOrdersInPipelineEntityObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
							currentOrdersInPipelineEntityObject.setOrderUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(1))));
					
							if(DateUtil.isCellDateFormatted(row.getCell(2))) {
								logger.info("Cell is Date Formatted");
								currentOrdersInPipelineEntityObject.setRecordDate(recordDateInString);
							}
							currentOrdersInPipelineEntityList.add(currentOrdersInPipelineEntityObject);
						}
				
					}
			
				}
				
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByReportDate(DateUtils.formattedDate(issueDate));
				
				if (duplicateEntryCheckList.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputCurrentOrderInPipelineFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(issueDate));
					
				}
				
				List<Map<String, String>> duplicateEntryCheckList2 = reportRepository.getRecordsByRecordDateForRmInventory(DateUtils.formattedDate(issueDate));
				
				if (duplicateEntryCheckList2.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputCurrentOrderInPipelineFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(issueDate) + " in RM inventory control");
					
				}
			
				logger.info("Rejected List ----->>>>" + rejectedCurrentOrderPipelineRecordList);
			
				logger.info("-----------------" + rejectedCurrentOrderPipelineRecordList.size() + "--------------------");
			
				if(rejectedCurrentOrderPipelineRecordList.size() > 0) {
				
					XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
	
					XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Last Issuance Old");
				
					XSSFRow rejectedRecordSheetRow;
				
					Map<String, Object[]> rejectedRecordData = new TreeMap<String, Object[]>();
				
					rejectedRecordData.put("1", new Object[] {"Material Code", "Record Date", "Order Units"});
				
					ListIterator<RejectedCurrentOrderPipelineRecordDto> rejectedCurrentOrderPipelineListIterator = rejectedCurrentOrderPipelineRecordList.listIterator();
				
					int rowId = 1;
					
					rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
					
					rejectedRecordSheetRow.createCell(0).setCellValue("Material Code");
					rejectedRecordSheetRow.createCell(1).setCellValue("Current Order In Pipeline");
					rejectedRecordSheetRow.createCell(2).setCellValue("Record Date");
					
					logger.info("Size " + rejectedCurrentOrderPipelineRecordList.size());
				
					while (rejectedCurrentOrderPipelineListIterator.hasNext()) {
					
						logger.info("Checkkkk");
					
						RejectedCurrentOrderPipelineRecordDto rejectedCurrentOrderPipelineRecordDto = rejectedCurrentOrderPipelineListIterator.next();
					
						rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
					
						rejectedRecordSheetRow.createCell(0).setCellValue(rejectedCurrentOrderPipelineRecordDto.getMaterialCode());
						rejectedRecordSheetRow.createCell(1).setCellValue(rejectedCurrentOrderPipelineRecordDto.getOrderUnits());
						rejectedRecordSheetRow.createCell(2).setCellValue(rejectedCurrentOrderPipelineRecordDto.getRecordDate());
						//rejectedRecordSheetRow.createCell(3).setCellValue(rejectedCurrentOrderPipelineRecordDto.getRowNo());
					
					}
					
					FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/OrderPipelineSummary/Rejected_Current_Orders_Pipeline.xlsx"));
					writeRejectedWorkBook.write(outPutStream);
					outPutStream.close();
				
					//String rejectedFilePath1 = "C:/Rejected_Records/OrderPipelineSummary/Rejected_Current_Orders_Pipeline.xlsx";
					File rejectedFile = new File(rejectedFilePath1);
				
					emailService.sendMailWithAttachment(rejectedFile, inputCurrentOrderInPipelineFilePath);
				
					logger.info("-----------------" + "Rejected Data Stored in RejectedOrderPipelineRecord.xlsx" + "---------------");
				
				}
			}
		
		currentOrdersInPipelineRepository.saveAll(currentOrdersInPipelineEntityList);
		
		logger.info("-----------------" + "saved in DB" + "---------------");
		
		fileMoveService.moveFilesAfterDataImport(inputCurrentOrderSummaryPath, inputCurrentOrderInPipelineFilePath.getName());
		
		return currentOrdersInPipelineEntityList;
	
	}
	
}
