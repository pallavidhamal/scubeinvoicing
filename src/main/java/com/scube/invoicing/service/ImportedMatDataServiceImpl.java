package com.scube.invoicing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
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

import com.scube.invoicing.dto.ImportedmatDataDto;
import com.scube.invoicing.dto.RejectedCurrentOrderPipelineDetailsExcelRecordDto;
import com.scube.invoicing.dto.RejectedImportedMatDataDto;
import com.scube.invoicing.dto.RejectedLastIssuanceRecordDto;
import com.scube.invoicing.dto.mapper.ImportedMaterialDataMapper;
import com.scube.invoicing.entity.ImportedMatDataEntity;
import com.scube.invoicing.entity.LastIssuanceEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ImportedMatDataRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;


@Service
public class ImportedMatDataServiceImpl implements ImportedMatDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImportedMatDataServiceImpl.class);
	
	private XSSFWorkbook workBook;
	
	@Autowired
	ImportedMatDataRepository importedMatDataRepository;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	EmailService emailService;
	
	@Value("${file.importedmat.path}") private File importedMaterialFilePath;
	
	@Value("${file.importedmat.path}") private String importedMaterialInputFilePath;
	
	@Value ("${file.rejectedimportedmatdata.path}") private String rejectedFilePath;
	
	@Override
	public List<ImportedMatDataEntity> uploadImportedMatData() throws Exception {
		
		logger.info("***** ImportedDataServiceImpl uploadImportedMatData*****");
		
		FileInputStream fileInputStream = new FileInputStream(importedMaterialFilePath);
		
		List<ImportedMatDataEntity>importedMatDataList = new ArrayList<ImportedMatDataEntity>();
		
		List<RejectedImportedMatDataDto> rejectedImportedDataRecordList = new ArrayList<RejectedImportedMatDataDto>();
		
		workBook = new XSSFWorkbook(fileInputStream);
		XSSFSheet workSheet = workBook.getSheetAt(0);
		
		logger.info("-------" + workSheet.getSheetName() + "------" + importedMaterialFilePath.getName());
		
		String withoutExtensionFileName = FilenameUtils.removeExtension(importedMaterialFilePath.getName());
		
		logger.info("File Name without extension : " + withoutExtensionFileName);
		
		
		int rowcnt = workSheet.getLastRowNum();
		System.out.println(rowcnt + "-----");
		int numberOfColumns = workSheet.getRow(0).getPhysicalNumberOfCells();
			
		String excelName = "";
		
		if(importedMaterialFilePath.getName().startsWith("RM Inventory control format")) {
			logger.info("-----------------" + "File Name Correct" + "-----------------------"
					+ importedMaterialFilePath.getName().startsWith("RM Inventory control format"));
			
			if(numberOfColumns < 2) {
				
				throw BRSException.throwException("Error : Incorrect No of Columns");
			
			}
			String reportDateInString = "";
			for (int i = 4; i <= rowcnt; i++) {
				
				logger.info("Correct -------" );
				
				ImportedMatDataEntity importedMatDataobj = new ImportedMatDataEntity();
				RejectedImportedMatDataDto rejectedImportedDataObj = new RejectedImportedMatDataDto();
				
				XSSFRow row = workSheet.getRow(i);
				
				Date reportDate = row.getCell(41).getDateCellValue();
				
				
			if(row != null) {
					
	//				excelName = ExcelUtils.checkIfCellBlank(row.getCell(0));
					logger.info(" *---*" + i + "excelName=" + excelName);
					
					int rowcell = row.getPhysicalNumberOfCells();
					logger.info("rowcell=" + rowcell);
					
					/*
					 * Date reportDateFormat = row.getCell(41).getDateCellValue(); String
					 * reportDateInString = new
					 * SimpleDateFormat("yyyy-MM-dd").format(reportDateFormat);
					 */
				
					logger.info("Record Date in Date and String format -----" + reportDate + "------" + reportDateInString);
					
			if(row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK
					|| row.getCell(41).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(42).getCellType()== Cell.CELL_TYPE_BLANK
					|| row.getCell(43).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(44).getCellType()== Cell.CELL_TYPE_BLANK
					|| row.getCell(45).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(46).getCellType()== Cell.CELL_TYPE_BLANK
					|| row.getCell(47).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(48).getCellType()== Cell.CELL_TYPE_BLANK
					|| row.getCell(49).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(50).getCellType()== Cell.CELL_TYPE_BLANK
					|| row.getCell(51).getCellType()== Cell.CELL_TYPE_BLANK ) 
			
				
				
				{
				
				reportDateInString = DateUtils.formattedDate(reportDate);
						
					logger.info("Report Date String" + reportDateInString + "-----");
						
					if(row.getCell(41).getCellType()==Cell.CELL_TYPE_NUMERIC || DateUtil.isCellDateFormatted(row.getCell(41))) {
							
							rejectedImportedDataObj.setReportDate(reportDateInString);
								
					}
						
					rejectedImportedDataObj.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(1)));
					rejectedImportedDataObj.setMaterialDescription(ExcelUtils.getFormattedCellValue(row.getCell(2)));
					rejectedImportedDataObj.setReportDate(reportDateInString);
					rejectedImportedDataObj.setDomesticOrImportedBasedOnLocationOfSupplier(ExcelUtils.getFormattedCellValue(row.getCell(42)));
					rejectedImportedDataObj.setInlandTransit(ExcelUtils.getFormattedCellValue(row.getCell(43)));
					rejectedImportedDataObj.setMaterialInlandTransit(ExcelUtils.getFormattedCellValue(row.getCell(44)));
					rejectedImportedDataObj.setPortICDclearanceTime(ExcelUtils.getFormattedCellValue(row.getCell(45)));
					rejectedImportedDataObj.setMaterialAtPortIcdUnits(ExcelUtils.getFormattedCellValue(row.getCell(46)));
					rejectedImportedDataObj.setHighSeasTransitDays(ExcelUtils.getFormattedCellValue(row.getCell(47)));
					rejectedImportedDataObj.setMaterialHighSeasUnits(ExcelUtils.getFormattedCellValue(row.getCell(48)));
					rejectedImportedDataObj.setLeadTimeDays(ExcelUtils.getFormattedCellValue(row.getCell(49)));
					rejectedImportedDataObj.setUnexecutedOrdersUnits(ExcelUtils.getFormattedCellValue(row.getCell(50)));
					rejectedImportedDataObj.setTotalPurchaseOrdersUnits(ExcelUtils.getFormattedCellValue(row.getCell(51)));
					rejectedImportedDataObj.setRowNo(String.valueOf(row.getRowNum()+1));
						
					rejectedImportedDataRecordList.add(rejectedImportedDataObj);
						
					logger.info("Rejected Record List ----->>>>" + rejectedImportedDataRecordList );	
				}
				else {
						
						importedMatDataobj.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(1)));
						importedMatDataobj.setMaterialDescription(ExcelUtils.getFormattedCellValue(row.getCell(2)));
						importedMatDataobj.setDomesticOrImportedBasedOnLocationOfSupplier(ExcelUtils.getFormattedCellValue(row.getCell(42)));
						importedMatDataobj.setInlandTransit(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(43))));
						importedMatDataobj.setMaterialInlandTransit(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(44))));
						importedMatDataobj.setPortICDclearanceTime(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(45))));
						importedMatDataobj.setMaterialAtPortIcdUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(46))));
						importedMatDataobj.setHighSeasTransitDays(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(47))));
						importedMatDataobj.setMaterialHighSeasUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(48))));
						importedMatDataobj.setLeadTimeDays(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(49))));
						importedMatDataobj.setUnexecutedOrdersUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(50))));
						importedMatDataobj.setTotalPurchaseOrdersUnits(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(51))));
						
							if(DateUtil.isCellDateFormatted(row.getCell(41))) {
							
								logger.info("Report Date Cell is Date Formatted");
								importedMatDataobj.setReportDate(DateUtils.formattedDate(reportDate));
								
							}
								importedMatDataList.add(importedMatDataobj);
						}
					}
				}
			
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByRecordDateForImportedMaterial(reportDateInString);
				
				if(duplicateEntryCheckList.size() > 0) {
							
							emailService.sendMailForDuplicateRecords(importedMaterialFilePath);
							throw BRSException.throwException("Error : Record Already Exists for " + reportDateInString);
							
				}
				
				List<Map<String, String>> duplicateEntryCheckList1 = reportRepository.getrecordsByRecordDateForRmImportSufficiency(reportDateInString);
				
				if(duplicateEntryCheckList1.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(importedMaterialFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + reportDateInString + " in RM Import sufficiency");
					
				}
						
				logger.info("Rejected List ----->>>>" + rejectedImportedDataRecordList);
							
				logger.info("-----------------" + rejectedImportedDataRecordList.size() + "--------------------");
						
				if(rejectedImportedDataRecordList.size() > 0) {
							
				  XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
				  XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Imported Data");
				  XSSFRow rejectedRecordSheetRow;
							
				  ListIterator<RejectedImportedMatDataDto> rejectedImportedMatDataListIterator = rejectedImportedDataRecordList.listIterator();
							
				  int rowId = 1;
							
				  logger.info("Size " + rejectedImportedDataRecordList.size());
							
				  while (rejectedImportedMatDataListIterator.hasNext()) {
								
					  			rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
					  			
					  			rejectedRecordSheetRow.createCell(0).setCellValue("Material Code");
					  			rejectedRecordSheetRow.createCell(1).setCellValue("Material Description");
					  			rejectedRecordSheetRow.createCell(2).setCellValue("Report date");
					  			rejectedRecordSheetRow.createCell(3).setCellValue("Domestic/ Imported (Based on location of supplier)");
					  			rejectedRecordSheetRow.createCell(4).setCellValue("Inland transit (days)");
					  			rejectedRecordSheetRow.createCell(5).setCellValue("Material in inland transit  (no. of units)");
					  			rejectedRecordSheetRow.createCell(6).setCellValue("Port/ICD Clearance time (no. of days)");
					  			rejectedRecordSheetRow.createCell(7).setCellValue("Material at port/ ICD (no. of units)");
					  			rejectedRecordSheetRow.createCell(8).setCellValue("High seas transit time (no. of days)");
					  			rejectedRecordSheetRow.createCell(9).setCellValue("Material in high seas  (no. of units)");
					  			rejectedRecordSheetRow.createCell(10).setCellValue("Lead time (no. of days) ");
					  			rejectedRecordSheetRow.createCell(11).setCellValue("Unexecuted orders (no. of units)");
					  			rejectedRecordSheetRow.createCell(12).setCellValue("Total Purchase orders(no. of units)");
					  			
					  			
								logger.info("Checkkkk");
								
								RejectedImportedMatDataDto rejectedImportedMatDataRecordDto = rejectedImportedMatDataListIterator.next();
								
								rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
								
								rejectedRecordSheetRow.createCell(0).setCellValue(rejectedImportedMatDataRecordDto.getMaterialCode());
								rejectedRecordSheetRow.createCell(1).setCellValue(rejectedImportedMatDataRecordDto.getMaterialDescription());
								rejectedRecordSheetRow.createCell(2).setCellValue(rejectedImportedMatDataRecordDto.getReportDate());
								rejectedRecordSheetRow.createCell(3).setCellValue(rejectedImportedMatDataRecordDto.getDomesticOrImportedBasedOnLocationOfSupplier());
								rejectedRecordSheetRow.createCell(4).setCellValue(rejectedImportedMatDataRecordDto.getInlandTransit());
								rejectedRecordSheetRow.createCell(5).setCellValue(rejectedImportedMatDataRecordDto.getMaterialInlandTransit());
								rejectedRecordSheetRow.createCell(6).setCellValue(rejectedImportedMatDataRecordDto.getPortICDclearanceTime());
								rejectedRecordSheetRow.createCell(7).setCellValue(rejectedImportedMatDataRecordDto.getMaterialAtPortIcdUnits());
								rejectedRecordSheetRow.createCell(8).setCellValue(rejectedImportedMatDataRecordDto.getHighSeasTransitDays());
								rejectedRecordSheetRow.createCell(9).setCellValue(rejectedImportedMatDataRecordDto.getMaterialHighSeasUnits());
								rejectedRecordSheetRow.createCell(10).setCellValue(rejectedImportedMatDataRecordDto.getLeadTimeDays());
								rejectedRecordSheetRow.createCell(11).setCellValue(rejectedImportedMatDataRecordDto.getUnexecutedOrdersUnits());
								rejectedRecordSheetRow.createCell(12).setCellValue(rejectedImportedMatDataRecordDto.getTotalPurchaseOrdersUnits());					
				  }
				  FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/ImportedItem/Rejected Imported Material Data.xlsx"));
				  writeRejectedWorkBook.write(outPutStream);
				  outPutStream.close();
							
				//  String rejectedFilePath = "C:/Rejected_Records/ImportedItem/Rejected Imported Material Data.xlsx";
			      File rejectedFile = new File(rejectedFilePath);
							
				  emailService.sendMailWithAttachment(rejectedFile,importedMaterialFilePath );
							
				  logger.info("-----------------" + "Rejected Data Stored in Rejected Imported Material Data.xlsx" + "---------------");
			}
		
		}
		else {
			
				throw BRSException.throwException("Error : Incorrect File Name for this process");
				
		}
				importedMatDataRepository.saveAll(importedMatDataList);
		
		logger.info("-----------------" + "saved in DB" + "---------------");
		
		fileMoveService.moveFilesAfterDataImport(importedMaterialInputFilePath, importedMaterialFilePath.getName());
		
		return importedMatDataList;	
		
	}

	@Override
	public List<ImportedmatDataDto> getImportedMaterialData() throws ParseException {
		// TODO Auto-generated method stub
		
		logger.info("***ImportedDataServiceImpl getImportedMaterialData***");
		
		List<Map<String, String>> importeddMatDataList = importedMatDataRepository.getImportedMaterialData();
		
		List<ImportedmatDataDto> importedmatDataDtosList = ImportedMaterialDataMapper.toImportedmatDataDtos(importeddMatDataList);
		
		return importedmatDataDtosList;
	}

}
