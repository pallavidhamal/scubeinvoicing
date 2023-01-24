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
import org.hibernate.hql.internal.ast.util.PathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.RejectedMaterialInfoAndCurrentInventoryDto;
import com.scube.invoicing.entity.ExcelFileResultEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.MaterialAndCurrentInventoryRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.DateUtils;
import com.scube.invoicing.util.ExcelUtils;


@Service
public class MaterialInfoAndCurrentInventoryServiceImpl implements MaterialInfoAndCurrentInventoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialInfoAndCurrentInventoryServiceImpl.class);
	
	private XSSFWorkbook workBook;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	MaterialAndCurrentInventoryRepository materialAndCurrentInventoryRepository;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	FileMoveService fileMoveService;
	
	@Value("${file.materialandcurrentinventory.path}") private File inputMaterialAndCurrentInventoryFilePath;
	
	@Value("${file.materialandcurrentinventory.path}") private String inputFilePath;
	
	@Value("${file.rejectedmaterialinfocurrentinventory.path}") private String rejectedFilePath2;
	
	
	@Override
	public List<ExcelFileResultEntity> uploadExcelFileData() throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("*****ExcelFileToDatabaseServiceImpl uploadExcelFileData*****");

/*
		File directoryPath = new File(inputMaterialAndCurrentInventoryFilePath);
		
		File filesList[] = directoryPath.listFiles();
*/		
		FileInputStream fileInputStream = new FileInputStream(inputMaterialAndCurrentInventoryFilePath);
		
		List<ExcelFileResultEntity> excelFileResultEntities = new ArrayList<ExcelFileResultEntity>();

		List<RejectedMaterialInfoAndCurrentInventoryDto> rejectedMaterialInfoAndCurrentInventoryList = new ArrayList<RejectedMaterialInfoAndCurrentInventoryDto>();
		
//		for(File file : filesList) {
		
			workBook = new XSSFWorkbook(fileInputStream);
			XSSFSheet workSheet = workBook.getSheetAt(0);
			
			logger.info("-------" + workSheet.getSheetName() + "-------" + inputMaterialAndCurrentInventoryFilePath.getName());
			
			String withoutExtensionFileName = FilenameUtils.removeExtension(inputMaterialAndCurrentInventoryFilePath.getName());
			
			logger.info("File Name without extension : " + withoutExtensionFileName);

/*
			String fileNameExceptDate = withoutExtensionFileName.substring(0, 12);
			
			String dateFromInputFile = withoutExtensionFileName.substring(13, withoutExtensionFileName.length());
			
			logger.info("File Name with only Date : " + dateFromInputFile + "------" +fileNameExceptDate);
			
*/
		
			int rowCount = workSheet.getLastRowNum();
			int cellCount = workSheet.getRow(0).getLastCellNum();
			int numberOfColumns = workSheet.getRow(0).getPhysicalNumberOfCells();
	
			if(inputMaterialAndCurrentInventoryFilePath.getName().startsWith("Material Info & Current Inventory")) {
				
				if(numberOfColumns < 2) {
				
					throw BRSException.throwException("Error : Incorrect No of Columns");
				
				}
			
				logger.info("Total No of Columns : " + numberOfColumns + "Cell Count : " + cellCount + "Row Count=" + rowCount);
			
				String excelAssName = "";
				
				Date issueMaterialInfoDate = new Date();
		
				for(int i = 2; i<=rowCount; i++) {
				
					ExcelFileResultEntity  excelFileResultEntityObject = new ExcelFileResultEntity();
					RejectedMaterialInfoAndCurrentInventoryDto  rejectedMaterialInfoAndCurrentInventoryObject = new RejectedMaterialInfoAndCurrentInventoryDto();
				
					XSSFRow row = workSheet.getRow(i);
				
					logger.info("Correct---------");
				
					issueMaterialInfoDate = row.getCell(5).getDateCellValue();
					logger.info("Issue Date String" + DateUtils.formattedDate(issueMaterialInfoDate) + "-------");
			
					if(row != null) {
				
						excelAssName =  ExcelUtils.checkIfCellBlank(row.getCell(12));
						logger.info(" *---*" + i + "excelAssName=" + excelAssName + "--" + row.getCell(0));			
				
						int rowcell = row.getPhysicalNumberOfCells();
						logger.info("rowcell=" + rowcell);
					
						if (row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(1).getCellType()== Cell.CELL_TYPE_BLANK || 
								row.getCell(2).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(3).getCellType()== Cell.CELL_TYPE_BLANK
								|| row.getCell(4).getCellType()== Cell.CELL_TYPE_BLANK || row.getCell(5).getCellType()== Cell.CELL_TYPE_BLANK) {
						
								Date rejectedMaterialInfoReceipt = row.getCell(5).getDateCellValue();
								String rejectedMaterialReceiptRecordDate = DateUtils.formattedDate(rejectedMaterialInfoReceipt);
						
								if (row.getCell(5).getCellType()== Cell.CELL_TYPE_NUMERIC) {
							
									rejectedMaterialInfoAndCurrentInventoryObject.setReportDate(rejectedMaterialReceiptRecordDate);
								}
						
								rejectedMaterialInfoAndCurrentInventoryObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
								rejectedMaterialInfoAndCurrentInventoryObject.setMaterialDescription(ExcelUtils.getFormattedCellValue(row.getCell(1)));
								rejectedMaterialInfoAndCurrentInventoryObject.setUOM(ExcelUtils.getFormattedCellValue(row.getCell(2)));
								rejectedMaterialInfoAndCurrentInventoryObject.setUnitPrice(ExcelUtils.getFormattedCellValue(row.getCell(3)));
								rejectedMaterialInfoAndCurrentInventoryObject.setCurrentInventoryInUnits(ExcelUtils.getFormattedCellValue(row.getCell(4)));
								rejectedMaterialInfoAndCurrentInventoryObject.setReportDate(ExcelUtils.getFormattedCellValue(row.getCell(5)));
								rejectedMaterialInfoAndCurrentInventoryObject.setItemPurchaseCategory(ExcelUtils.getFormattedCellValue(row.getCell(6)));
								rejectedMaterialInfoAndCurrentInventoryObject.setRow(String.valueOf(row.getRowNum()+1));
						
						
								rejectedMaterialInfoAndCurrentInventoryList.add(rejectedMaterialInfoAndCurrentInventoryObject);
								logger.info("Rejected Record List ---------->>>>>" + rejectedMaterialInfoAndCurrentInventoryList);
						}
					
						else {
						
							excelFileResultEntityObject.setMaterialCode(ExcelUtils.getFormattedCellValue(row.getCell(0)));
							excelFileResultEntityObject.setMaterialDescription(ExcelUtils.getFormattedCellValue(row.getCell(1)));
							excelFileResultEntityObject.setUOM(ExcelUtils.getFormattedCellValue(row.getCell(2)));
							excelFileResultEntityObject.setUnitPrice(Double.parseDouble(ExcelUtils.getFormattedCellValue(row.getCell(3))));
							excelFileResultEntityObject.setCurrentInventoryInUnits(ExcelUtils.getFormattedCellValue(row.getCell(4)));
							excelFileResultEntityObject.setReportDate(ExcelUtils.getFormattedCellValue(row.getCell(5)));
							excelFileResultEntityObject.setItemPurchaseCategory(ExcelUtils.getFormattedCellValue(row.getCell(6)));
				
							if(DateUtil.isCellDateFormatted(row.getCell(5))) {
							
								excelFileResultEntityObject.setReportDate(DateUtils.formattedDate(issueMaterialInfoDate));
						
							}
						
							excelFileResultEntities.add(excelFileResultEntityObject);
						}
					}
				}
				
				List<Map<String, String>> duplicateEntryCheckList = reportRepository.getRecordsByRecordDateForMaterialCurrentInventory(DateUtils.formattedDate(issueMaterialInfoDate));

				if(duplicateEntryCheckList.size() > 0) {
	
						emailService.sendMailForDuplicateRecords(inputMaterialAndCurrentInventoryFilePath);
						throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(issueMaterialInfoDate));
		
				}
				
				List<Map<String, String>> duplicateEntryCheckList1 = reportRepository.getRecordsByRecordDateForRmInventory(DateUtils.formattedDate(issueMaterialInfoDate));
				
				if(duplicateEntryCheckList1.size() > 0) {
					
					emailService.sendMailForDuplicateRecords(inputMaterialAndCurrentInventoryFilePath);
					throw BRSException.throwException("Error : Record Already Exists for " + DateUtils.formatDateToDDMMYYYYFormat(issueMaterialInfoDate)+ " in RM inventory control");
	
				}
				
				logger.info("Rejected List ----->" + rejectedMaterialInfoAndCurrentInventoryList);
			
				logger.info("-----------------------" + rejectedMaterialInfoAndCurrentInventoryList.size() + "-----------");
			
				if(rejectedMaterialInfoAndCurrentInventoryList.size()>0) {
			
					XSSFWorkbook writeRejectedWorkBook = new XSSFWorkbook();
			
					XSSFSheet rejectedRecordSheet = writeRejectedWorkBook.createSheet("Rejected Material Info Old");
			
					XSSFRow rejectedRecordSheetRow;;
				
					ListIterator<RejectedMaterialInfoAndCurrentInventoryDto> rejectedMatrialInfoListIterator = rejectedMaterialInfoAndCurrentInventoryList.listIterator();
			
					int rowId = 1;
					
					rejectedRecordSheetRow = rejectedRecordSheet.createRow(0);
					
					rejectedRecordSheetRow.createCell(0).setCellValue("Material Code");
					rejectedRecordSheetRow.createCell(1).setCellValue("Material Description");
					rejectedRecordSheetRow.createCell(2).setCellValue("UOM");
					rejectedRecordSheetRow.createCell(3).setCellValue("Unit Price");
					rejectedRecordSheetRow.createCell(4).setCellValue("Current inventory in the plant in  units\r\n" + 
							"");
					rejectedRecordSheetRow.createCell(5).setCellValue("Report Date");
					rejectedRecordSheetRow.createCell(6).setCellValue("Domestics / Imported");
					
			
					logger.info("Size " + rejectedMaterialInfoAndCurrentInventoryList.size());
				
					while (rejectedMatrialInfoListIterator.hasNext()) {
					
						logger.info("Checkkkk");
				
						RejectedMaterialInfoAndCurrentInventoryDto rejectedMaterialInfoAndCurrentInventoryDto = rejectedMatrialInfoListIterator.next();
				
						rejectedRecordSheetRow = rejectedRecordSheet.createRow(rowId++);
				
						rejectedRecordSheetRow.createCell(0).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getMaterialCode());
						rejectedRecordSheetRow.createCell(1).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getMaterialDescription());
						rejectedRecordSheetRow.createCell(2).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getUOM());
						rejectedRecordSheetRow.createCell(3).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getUnitPrice());
						rejectedRecordSheetRow.createCell(4).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getCurrentInventoryInUnits());
						rejectedRecordSheetRow.createCell(5).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getReportDate());
						rejectedRecordSheetRow.createCell(6).setCellValue(rejectedMaterialInfoAndCurrentInventoryDto.getItemPurchaseCategory());
				
					}
			
					FileOutputStream outPutStream = new FileOutputStream(new File("C:/Rejected_Records/MaterialInfoCurrentInventory/Rejected_Material_Info_Current_Inventory.xlsx"));
					writeRejectedWorkBook.write(outPutStream);
					outPutStream.close();
			
					//String rejectedFilePath2 = "C:/Rejected_Records/MaterialInfoCurrentInventory/Rejected_Material_Info_Current_Inventory.xlsx";
					File rejectedFile = new File(rejectedFilePath2);
			
					emailService.sendMailWithAttachment(rejectedFile, inputMaterialAndCurrentInventoryFilePath);
			
					logger.info("-----------------" + "Rejected Data Stored in RejectedOrderPipelineRecord.xlsx" + "---------------");
				
				}
			
			}
	
		
		materialAndCurrentInventoryRepository.saveAll(excelFileResultEntities);
	
		logger.info("-----------------" + "saved in DB" + "---------------");
		
		fileMoveService.moveFilesAfterDataImport(inputFilePath, inputMaterialAndCurrentInventoryFilePath.getName());
	
		return excelFileResultEntities;
	}
	
}
