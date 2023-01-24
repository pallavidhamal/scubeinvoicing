package com.scube.invoicing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import com.scube.invoicing.dto.CurrentOrdersInPipelineResponseDto;
import com.scube.invoicing.dto.RejectedCurrentOrderPipelineDetailsExcelRecordDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.mapper.CurrentOrdersInPipelineDetailsMapper;
import com.scube.invoicing.entity.CurrentOrdersInPipelineDetailsEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.CurrentOrdersInPipelineDetailsRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;

@Service
public class CurrentOrdersInPipelineDetailsServiceImpl implements CurrentOrdersInPipelineDetailService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrentOrdersInPipelineDetailsServiceImpl.class);
	
	@Autowired
	CurrentOrdersInPipelineDetailsRepository currentOrdersInPipelineDetailsRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Autowired
	ReportRepository reportRepository;
	
	private XSSFWorkbook workBook;
	
	@Value("${file.currentorderspipelinedetails.path}") private File inputCurrentOrderInPipelineDetailsFilePath;
	
	@Value("${file.currentorderspipelinedetails.path}") private String inputCurrentOrderDetailsPath;
	
	@Value("${file.rejectedcurrentorderpipelinedetails.path}") private String rejectedFilePath;
	
	@Override
	public List<CurrentOrdersInPipelineDetailsEntity> getCurrentOrdersInPipelineDetailsExcelFileData() throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("*****CurrentOrdersInPipelineDetailsServiceImpl getCurrentOrdersInPipelineDetailsExcelFileData*****");
/*		
		File directoryPath = new File(inputCurrentOrderInPipelineDetailsFilePath); 
		
		File filesList[] = directoryPath.listFiles();
*/
	
		FileInputStream fileInputStream = new FileInputStream(inputCurrentOrderInPipelineDetailsFilePath);
		
		List<CurrentOrdersInPipelineDetailsEntity> currentOrdersInPipelineDetailsList = new ArrayList<CurrentOrdersInPipelineDetailsEntity>();
		
		List<RejectedCurrentOrderPipelineDetailsExcelRecordDto> rejectedCurrentOrderPipelineDetailsExcelRecordsList = 
				new ArrayList<RejectedCurrentOrderPipelineDetailsExcelRecordDto>(); 

		
//		for(File file : filesList) {
			
		workBook = new XSSFWorkbook(fileInputStream);			
		XSSFSheet worksheet = workBook.getSheetAt(0);
			
		logger.info("-----------------" +  inputCurrentOrderInPipelineDetailsFilePath.getName() + "-------------------");
			
		String withoutExtensionFileName = FilenameUtils.removeExtension(inputCurrentOrderInPipelineDetailsFilePath.getName());	
		logger.info("File Name without extension : " + withoutExtensionFileName);
		
		int rowcnt = worksheet.getLastRowNum();
		System.out.println(rowcnt + "-----");
		int numberOfColumns = worksheet.getRow(0).getPhysicalNumberOfCells();
			
		String excelName = "";
				
		if(inputCurrentOrderInPipelineDetailsFilePath.getName().startsWith("Current Orders in Pipeline (Detail)")) {
					
				logger.info("-----------------" + "File Name Correct" + "-----------------------"
								+ inputCurrentOrderInPipelineDetailsFilePath.getName().startsWith("Current Orders in Pipeline (Detail)"));
					
				if(numberOfColumns < 2) {
						
						throw BRSException.throwException("Error : Incorrect No of Columns");
					
				}
				
				Date reportDate = new Date();
				Date poDate = new Date();
				
				
				for (int i = 1; i <= rowcnt; i++) {
					String poDtString = "";
					logger.info("Correct -------" );
						
					CurrentOrdersInPipelineDetailsEntity currentOrdersInPipelineDetailsObj = new CurrentOrdersInPipelineDetailsEntity();
					RejectedCurrentOrderPipelineDetailsExcelRecordDto rejectedRecordsObj = new RejectedCurrentOrderPipelineDetailsExcelRecordDto();
						
					XSSFRow row = worksheet.getRow(i);
						
					if(row != null) {
							
						excelName = ExcelUtils.checkIfCellBlank(row.getCell(0));
						logger.info(" *---*" + i + "excelName=" + excelName);
						
						int rowcell = row.getPhysicalNumberOfCells();
						logger.info("rowcell=" + rowcell);
						
						if (DateUtil.isCellDateFormatted(row.getCell(3))) 
							{
							
						poDate = row.getCell(3).getDateCellValue();
						poDtString = DateUtils.formattedDate(poDate);
							}
							else {
								if(row.getCell(3)!=null)
								{
								poDtString = ExcelUtils.getFormattedCellValue(row.getCell(3));
								}
							
							}
					//	String poDateInString = DateUtils.formattedDate(poDate);
							
						reportDate = row.getCell(8).getDateCellValue();
						String reportDateInString = DateUtils.formattedDate(reportDate);
						
						
						if(poDtString==null || poDtString.equals("") || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(1).getCellType()== Cell.CELL_TYPE_BLANK
								|| row.getCell(2).getCellType()== Cell.CELL_TYPE_BLANK || 
								 row.getCell(4).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(5).getCellType()== Cell.CELL_TYPE_BLANK
								|| row.getCell(6).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(7).getCellType()== Cell.CELL_TYPE_BLANK
								|| row.getCell(8).getCellType()==Cell.CELL_TYPE_BLANK) {
							
							logger.info("Report Date String" + reportDateInString + "-----" + "PO Date " + poDtString);
							
						//	Date poDate1 = row.getCell(3).getDateCellValue();
						//	String poDateInStr = DateUtils.formattedDate(poDate1);
							
						//	if(row.getCell(3).getCellType()==Cell.CELL_TYPE_NUMERIC) {
								rejectedRecordsObj.setPoDate(poDtString);
						//	}
							
							if(row.getCell(8).getCellType()==Cell.CELL_TYPE_NUMERIC) {
								rejectedRecordsObj.setReportDate(reportDateInString);					
							}
								
							rejectedRecordsObj.setPoType(ExcelUtils.getFormattedCellValue(row.getCell(0)));
							rejectedRecordsObj.setPoNo(ExcelUtils.getFormattedCellValue(row.getCell(1)));
							rejectedRecordsObj.setLineNo(ExcelUtils.getFormattedCellValue(row.getCell(2)));
							rejectedRecordsObj.setPoDate(poDtString);
							rejectedRecordsObj.setItem(ExcelUtils.getFormattedCellValue(row.getCell(4)));
							rejectedRecordsObj.setCurrentOrdersPipelineUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(5))));
							rejectedRecordsObj.setRate(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(5))));
							rejectedRecordsObj.setCurrency(ExcelUtils.getFormattedCellValue(row.getCell(7)));
							rejectedRecordsObj.setReportDate(reportDateInString);
							rejectedRecordsObj.setRowNo(String.valueOf(row.getRowNum()+1));
							
							rejectedCurrentOrderPipelineDetailsExcelRecordsList.add(rejectedRecordsObj);
							logger.info("Rejected Record List ----->>>>" + rejectedCurrentOrderPipelineDetailsExcelRecordsList);					
								
						}
						else {
								
							currentOrdersInPipelineDetailsObj.setPoType(ExcelUtils.getFormattedCellValue(row.getCell(0)));
							currentOrdersInPipelineDetailsObj.setPoNo(ExcelUtils.getFormattedCellValue(row.getCell(1)));
							currentOrdersInPipelineDetailsObj.setLineNo(ExcelUtils.getFormattedCellValue(row.getCell(2)));
							
							currentOrdersInPipelineDetailsObj.setItem(ExcelUtils.getFormattedCellValue(row.getCell(4)));
							currentOrdersInPipelineDetailsObj.setCurrentOrderPipelineUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(5))));
							currentOrdersInPipelineDetailsObj.setRate(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(6))));
							currentOrdersInPipelineDetailsObj.setCurrency(ExcelUtils.getFormattedCellValue(row.getCell(7)));
							
						/*
						 * if(DateUtil.isCellDateFormatted(row.getCell(3))) {
						 * logger.info("PO Date Cell is Date Formatted");
						 * currentOrdersInPipelineDetailsObj.setPoDate(poDateInString); }
						 */
							
							currentOrdersInPipelineDetailsObj.setPoDate(poDtString);
							
							if(DateUtil.isCellDateFormatted(row.getCell(8))) {
								logger.info("Report Date Cell is Date Formatted");
								currentOrdersInPipelineDetailsObj.setReportDate(DateUtils.formattedDate(reportDate));
							}
								
							currentOrdersInPipelineDetailsList.add(currentOrdersInPipelineDetailsObj);
								
						}
							
					}
						
				}
				
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByRecordDate(DateUtils.formattedDate(reportDate));
				
				if(duplicateEntryCheckList.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputCurrentOrderInPipelineDetailsFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(reportDate));
					
				}
				
				List<Map<String, String>> duplicateEntryCheckList1 = reportRepository.getRecordsByRecordDateForRmInventory(DateUtils.formattedDate(reportDate));
				
				if(duplicateEntryCheckList1.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputCurrentOrderInPipelineDetailsFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(reportDate)+ " in RM inventory control");
					
				}
					
				logger.info("Rejected List ----->>>>" + rejectedCurrentOrderPipelineDetailsExcelRecordsList);
					
				logger.info("-----------------" + rejectedCurrentOrderPipelineDetailsExcelRecordsList.size() + "--------------------");
					
				if(rejectedCurrentOrderPipelineDetailsExcelRecordsList.size() > 0) {
						
					XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
					XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Current Orders In Pipeline (Details)");
					XSSFRow rejectedRecordSheetRow;
						
					ListIterator<RejectedCurrentOrderPipelineDetailsExcelRecordDto> rejectedCurrentOrderPipelineListIterator = rejectedCurrentOrderPipelineDetailsExcelRecordsList.listIterator();
						
					int rowId = 1;
					
					rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
					
					rejectedRecordSheetRow.createCell(0).setCellValue("PO Type");
					rejectedRecordSheetRow.createCell(1).setCellValue("PO No");
					rejectedRecordSheetRow.createCell(2).setCellValue("Line No");
					rejectedRecordSheetRow.createCell(3).setCellValue("Po Date");
					rejectedRecordSheetRow.createCell(4).setCellValue("Item");
					rejectedRecordSheetRow.createCell(5).setCellValue("Current Order In Pipeline");
					rejectedRecordSheetRow.createCell(6).setCellValue("Rate");
					rejectedRecordSheetRow.createCell(7).setCellValue("Currency");
					rejectedRecordSheetRow.createCell(8).setCellValue("Report Date");
						
					logger.info("Size " + rejectedCurrentOrderPipelineDetailsExcelRecordsList.size());
						
					while (rejectedCurrentOrderPipelineListIterator.hasNext()) {
							
						logger.info("Checkkkk");
							
						RejectedCurrentOrderPipelineDetailsExcelRecordDto rejectedCurrentOrderPipelineRecordDto = rejectedCurrentOrderPipelineListIterator.next();
							
						rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
							
						rejectedRecordSheetRow.createCell(0).setCellValue(rejectedCurrentOrderPipelineRecordDto.getPoType());
						rejectedRecordSheetRow.createCell(1).setCellValue(rejectedCurrentOrderPipelineRecordDto.getPoNo());
						rejectedRecordSheetRow.createCell(2).setCellValue(rejectedCurrentOrderPipelineRecordDto.getLineNo());
						rejectedRecordSheetRow.createCell(3).setCellValue(rejectedCurrentOrderPipelineRecordDto.getPoDate());
						rejectedRecordSheetRow.createCell(4).setCellValue(rejectedCurrentOrderPipelineRecordDto.getItem());
						rejectedRecordSheetRow.createCell(5).setCellValue(rejectedCurrentOrderPipelineRecordDto.getCurrentOrdersPipelineUnits());
						rejectedRecordSheetRow.createCell(6).setCellValue(rejectedCurrentOrderPipelineRecordDto.getRate());
						rejectedRecordSheetRow.createCell(7).setCellValue(rejectedCurrentOrderPipelineRecordDto.getCurrency());
						rejectedRecordSheetRow.createCell(8).setCellValue(rejectedCurrentOrderPipelineRecordDto.getReportDate());	
					}
					FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/OrderPipelineDetails/Rejected Current Orders Pipeline Details.xlsx"));
					writeRejectedWorkBook.write(outPutStream);
					outPutStream.close();
						
					//String rejectedFilePath = "C:/Rejected_Records/OrderPipelineDetails/Rejected Current Orders Pipeline Details.xlsx";
					File rejectedFile = new File(rejectedFilePath);
						
					emailService.sendMailWithAttachment(rejectedFile, inputCurrentOrderInPipelineDetailsFilePath);
						
					logger.info("-----------------" + "Rejected Data Stored in RejectedOrderPipelineDetailsRecord.xlsx" + "---------------");
						
				}
				
		}
		else {
				
			throw BRSException.throwException("Error : Incorrect File Name for this process");
				
		}
		
		currentOrdersInPipelineDetailsRepository.saveAll(currentOrdersInPipelineDetailsList);
		
		logger.info("-----------------" + "saved in DB" + "---------------");
		
		fileMoveService.moveFilesAfterDataImport(inputCurrentOrderDetailsPath, inputCurrentOrderInPipelineDetailsFilePath.getName());
		
		return currentOrdersInPipelineDetailsList;
		
	}
	

	@Override
	public List<CurrentOrdersInPipelineResponseDto> getCurrentOrdersInPipelineDetailsByDateRange(
			ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("*****CurrentOrdersInPipelineDetailsServiceImpl getCurrentOrdersInPipelineDetailsByDateRange*****");
		
		List<CurrentOrdersInPipelineResponseDto> defaultCurrentOrdersInPipelineResponseList = new ArrayList<CurrentOrdersInPipelineResponseDto>();
		List<CurrentOrdersInPipelineResponseDto> currentOrdersInPipelineByMatCodeEntityList = new ArrayList<CurrentOrdersInPipelineResponseDto>();
		
		if(reportIncomingDto.getMaterialCode().equals("All")) {
		
			List<Map<String, String>> defaultAllCurrentOrdersInPipelineEntityList = currentOrdersInPipelineDetailsRepository.getCurrentOrdersPipelineDetailsByDateRange(reportIncomingDto.getFromdt());
			
			if(defaultAllCurrentOrdersInPipelineEntityList == null) {
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of Default All List " + defaultAllCurrentOrdersInPipelineEntityList.size() + "-----------------------------");
			
			defaultCurrentOrdersInPipelineResponseList = CurrentOrdersInPipelineDetailsMapper.toCurrentOrdersInPipelineResponseDtoList(defaultAllCurrentOrdersInPipelineEntityList);
			
			return defaultCurrentOrdersInPipelineResponseList;
		
		}
		
		else {
			
			List<Map<String, String>> currentOrdersInPipelineEntityList = currentOrdersInPipelineDetailsRepository.
					getCurrentOrdersPipelineDetailsByReportDateAndMaterialCode(reportIncomingDto.getFromdt(), reportIncomingDto.getMaterialCode());
			
			if(currentOrdersInPipelineEntityList == null) {
				
				throw BRSException.throwException("Error : No records present");
				
			}
			
			logger.info("-------------------------" + "Size of List By Mat Code " + currentOrdersInPipelineEntityList.size() + "-----------------------------");
			
			currentOrdersInPipelineByMatCodeEntityList = CurrentOrdersInPipelineDetailsMapper.toCurrentOrdersInPipelineResponseDtoList(currentOrdersInPipelineEntityList);
			
		}
		return currentOrdersInPipelineByMatCodeEntityList;
		
	}
	
}
