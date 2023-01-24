package com.scube.invoicing.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.dto.incoming.DumpDataIncomingDto;
import com.scube.invoicing.entity.ChangesInMatMasterEntity;
import com.scube.invoicing.entity.DumpDataEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.DumpDataRepository;
import com.scube.invoicing.repository.ReportRepository;
import com.scube.invoicing.util.StaticPathContUtils;
import com.scube.invoicing.util.StringNullEmpty;

@Service
public class DumpDataServiceImpl implements DumpDataService {
	private static final Logger logger = LoggerFactory.getLogger(ChangesInMatMasterServiceImpl.class);
	
	@Autowired
	DumpDataRepository dumpDataRepository;
	
	@Value("${file.dumpdata.path}") private String dumpDataFilePath;
	
	@Value("${get.dump.url}") private String dumpUrl;


	@Override
	public ReportResponseDto getDumpDataByDate(DumpDataIncomingDto dumpDataIncomingDto) throws Exception {
		
		
		// TODO Auto-generated method stub
		
		List<DumpDataEntity> dumpDataEntities = dumpDataRepository.getDumpDataByReportDate(dumpDataIncomingDto.getReportDate());
		ReportResponseDto reportResponseDto = new ReportResponseDto();
		
		if(dumpDataEntities.size() == 0) {
			 throw BRSException.throwException("Error : Records not present");
		 }
		
		 logger.info("-----------------------" + dumpDataEntities.size());
		 
		 XSSFWorkbook writeDumpDataWorkBook = new XSSFWorkbook();
	    	
	    	XSSFSheet dumpDataSheet = writeDumpDataWorkBook.createSheet("Changes In Material Master");
			
			XSSFRow dumpDataSheetRow;
			int rowId = 1;
			
		
		  dumpDataSheetRow = dumpDataSheet.createRow(0);
		  
		  dumpDataSheetRow.createCell(0).setCellValue("Material Code");
		  dumpDataSheetRow.createCell(1).setCellValue("Material Description");
		  dumpDataSheetRow.createCell(2).setCellValue("UOM");
		  dumpDataSheetRow.createCell(3).setCellValue("Unit Price in INR");
		  dumpDataSheetRow.createCell(4).setCellValue("Regular / Intermittent (R/I)");
		  dumpDataSheetRow.createCell(5).setCellValue("To be kept in stock (Y/N)");
		  dumpDataSheetRow.createCell(6).setCellValue("Readily available with supplier (Y/N)");
		  dumpDataSheetRow.createCell(7).setCellValue("Avg cons per day in units");
		  dumpDataSheetRow.createCell(8).setCellValue("Lead time in days");
		  dumpDataSheetRow.createCell(9).setCellValue("Trans. time in days");
		  dumpDataSheetRow.createCell(10).setCellValue("Supplier MOQ");
		  dumpDataSheetRow.createCell(11).setCellValue("EOQ");
		  dumpDataSheetRow.createCell(12).setCellValue("Frequency of supply in days (FS)");
		  dumpDataSheetRow.createCell(13).setCellValue("Safety Factor");
		  dumpDataSheetRow.createCell(14).setCellValue("Safety Stock norm in units");
		  dumpDataSheetRow.createCell(15).setCellValue("Safety Stock norm in Rs. Lacs");
		  dumpDataSheetRow.createCell(16).setCellValue("Max inventory norm in plant in units");
		  dumpDataSheetRow.createCell(17).setCellValue("Max Inventory norm in plant in Rs. Lacs");
		  dumpDataSheetRow.createCell(18).setCellValue("Avg plant inventory norm in units");
		  dumpDataSheetRow.createCell(19).setCellValue("Orders in pipeline norm in  units");
		  dumpDataSheetRow.createCell(20).setCellValue("Orders in pipeline norm in Rs. Lacs");
		  dumpDataSheetRow.createCell(21).setCellValue("Max inventory norm (plant +pipeline) in units");
		  dumpDataSheetRow.createCell(22).setCellValue("Current inventory in the plant in  units");
		  dumpDataSheetRow.createCell(23).setCellValue("Current Inventory in Rs. Lacs");
		  dumpDataSheetRow.createCell(24).setCellValue("Current orders in pipeline in units");
		  dumpDataSheetRow.createCell(25).setCellValue("Current orders in pipe line in Rs. Lacs");
		  dumpDataSheetRow.createCell(26).setCellValue("Max inv norm (plant + pipeline) - current inv - current orders in pipeline (in units)"); 
		  dumpDataSheetRow.createCell(27).setCellValue("PO to be released / (cancelled) in units");
		  dumpDataSheetRow.createCell(28).setCellValue("PO to be released / cancelled in Rs. Lacs");
		  dumpDataSheetRow.createCell(29).setCellValue("Average cons. per day in Rs. Lacs");
		  dumpDataSheetRow.createCell(30).setCellValue("Current Inventory in no. of days");
		  dumpDataSheetRow.createCell(31).setCellValue("Max inv norm - current inv in Rs. Lacs");
		  dumpDataSheetRow.createCell(32).setCellValue("Last Receipt Date");
		  dumpDataSheetRow.createCell(33).setCellValue("Last Receipt Qty");
		  dumpDataSheetRow.createCell(34).setCellValue("Received Qty /Max of MOQ & EOQ");
		  dumpDataSheetRow.createCell(35).setCellValue("Last Issue Date");
		  dumpDataSheetRow.createCell(36).setCellValue("Last Issue Qty");
		  dumpDataSheetRow.createCell(37).setCellValue("Issued quantity as no. of days of cons.");
		  dumpDataSheetRow.createCell(38).setCellValue("Days after issue");
		  dumpDataSheetRow.createCell(39).setCellValue("Report Date");
		  dumpDataSheetRow.createCell(40).setCellValue("Color");
		 
		
			
			for (DumpDataEntity dumpDataEntity : dumpDataEntities) {
				
				dumpDataSheetRow = dumpDataSheet.createRow(rowId++);
				
				dumpDataSheetRow.createCell(0).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaterialCode()));
				dumpDataSheetRow.createCell(1).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaterialDescription()));
				dumpDataSheetRow.createCell(2).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getUOm()));
				dumpDataSheetRow.createCell(3).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getUnitPrice()));
				dumpDataSheetRow.createCell(4).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getRegInter()));
				dumpDataSheetRow.createCell(5).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getKeptInstock()));
				dumpDataSheetRow.createCell(6).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getReadilyAvlble()));
				dumpDataSheetRow.createCell(7).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getAvgconsPerdayunits()));
				dumpDataSheetRow.createCell(8).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getLeadTime()));
				dumpDataSheetRow.createCell(9).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getTransTime()));
				dumpDataSheetRow.createCell(10).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getSupplierMoq()));
				dumpDataSheetRow.createCell(11).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getEOq()));
				dumpDataSheetRow.createCell(12).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getFsDays()));
				dumpDataSheetRow.createCell(13).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getSafetyFactor()));
				dumpDataSheetRow.createCell(14).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getSsNormUnits()));
				dumpDataSheetRow.createCell(15).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getSsNormUnitsLacs()));
				dumpDataSheetRow.createCell(16).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaxInvnormUnits()));
				dumpDataSheetRow.createCell(17).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaxInvnormLacs()));
				dumpDataSheetRow.createCell(18).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getAvgInvnormUnits()));
				dumpDataSheetRow.createCell(19).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getOrdersPipelinenormUnits()));
				dumpDataSheetRow.createCell(20).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getOrdersPipelinenormLacs()));
				dumpDataSheetRow.createCell(21).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaxInvnormPlantPipelineUnits()));
				dumpDataSheetRow.createCell(22).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getCurInvUnits()));
				dumpDataSheetRow.createCell(23).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getCurInvLacs()));
				dumpDataSheetRow.createCell(24).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getCurOrdersPipeUnits()));
				dumpDataSheetRow.createCell(25).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getCurOrdersPipeLacs()));
				dumpDataSheetRow.createCell(26).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaxinvnormCurinvCurpiporders()));
				dumpDataSheetRow.createCell(27).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getPoRelcanUnits()));
				dumpDataSheetRow.createCell(28).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getPoRelcanLacs()));
				dumpDataSheetRow.createCell(29).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getAvgConsLacs()));
				dumpDataSheetRow.createCell(30).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getCurInvDays()));
				dumpDataSheetRow.createCell(31).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getMaxinvnormCurinvLacs()));
				dumpDataSheetRow.createCell(32).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getLastRcptDate()));
				dumpDataSheetRow.createCell(33).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getLastRcptQty()));
				dumpDataSheetRow.createCell(34).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getRecqtyByMaxMoeq()));
				dumpDataSheetRow.createCell(35).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getLastIssueDate()));
				dumpDataSheetRow.createCell(36).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getLastIssueQty()));
				dumpDataSheetRow.createCell(37).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getIssuedQty()));
				dumpDataSheetRow.createCell(38).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getDaysAfterIssue()));
				dumpDataSheetRow.createCell(39).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getReportDate()));
				dumpDataSheetRow.createCell(40).setCellValue(StringNullEmpty.stringNullAndEmptyToBlank(dumpDataEntity.getColor()));
				
			}
			
			String fname="Dump_Data"+Math.random()+".xlsx";
			
			String path= dumpDataFilePath+fname;
			
			System.out.println(path);
			
			
			Path fileStorageLocation = Paths.get("../inventoryApi/uploadedfiles").toAbsolutePath().normalize();
      		logger.info("fileStorageLocation===="+ fileStorageLocation);
	            
      		Path filePath = fileStorageLocation.resolve(fname).normalize();
			
			
			
			FileOutputStream outPutStream = new FileOutputStream(path);
			File inputFile = new File(path);
			writeDumpDataWorkBook.write(outPutStream);
	
			outPutStream.close();
			
			logger.info("-----------------" + "dump data stored in Dump_Data.xlsx" + "---------------");
			
			//dumpDataRepository.saveAll(dumpDataEntities);
			
			System.out.println("-----excel saved path-----"+path);
			
			
		//	http://localhost:8081/inventoryApi/api/v1/images/getImage/Dump_Data0.15871067114650106.xlsx
			
		//	reportResponseDto.setFpath(StaticPathContUtils.DUMP_DATA_DIR+fname);
			reportResponseDto.setFpath(dumpUrl+fname);
			
			return reportResponseDto;
	}
}
