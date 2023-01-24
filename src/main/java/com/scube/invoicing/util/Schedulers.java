package com.scube.invoicing.util;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.service.CurrentOrdersInPipelineDetailService;
import com.scube.invoicing.service.CurrentOrdersInPipelineService;
import com.scube.invoicing.service.EmailService;
import com.scube.invoicing.service.ImportedMatDataService;
import com.scube.invoicing.service.LastIssuanceService;
import com.scube.invoicing.service.LastPurchaseReceiptService;
import com.scube.invoicing.service.MaterialInfoAndCurrentInventoryService;
import com.scube.invoicing.service.ResultService;

@Component
public class Schedulers {
        
        private static final Logger logger = LoggerFactory.getLogger(Schedulers.class);
        
        @Autowired
        MaterialInfoAndCurrentInventoryService materialInfoAndCurrentInventoryService;
        
        @Autowired
        LastPurchaseReceiptService lastPurchaseReceiptService;
        
        @Autowired
        LastIssuanceService lastIssuanceService;
        
        @Autowired
        CurrentOrdersInPipelineService currentOrdersInPipelineService;
        
        @Autowired
        CurrentOrdersInPipelineDetailService currentOrdersInPipelineDetailService;
        
        @Autowired
        ImportedMatDataService importedMatDataService;
        
        @Autowired
        ResultService resultService;
        
        @Autowired
    	EmailService emailService;
        
        @Value("${file.materialandcurrentinventory.path}") private File inputMaterialAndCurrentInventoryFilePath;
        
        @Value("${file.currentorderpipeline.path}") private File inputCurrentOrderInPipelineFilePath;
        
        @Value("${file.currentorderspipelinedetails.path}") private File inputCurrentOrderInPipelineDetailsFilePath;
        
        @Value("${file.lastissuance.path}") private File inputLastIssuanceFilePath;
        
        @Value("${file.lastpurchasereceipt.path}") private File inputLastPurchaseReceiptFilePath;
    
        @Value("${file.importedmat.path}") private File importedMaterialFilePath;
        
        @Scheduled(cron = "${schedule.excelfiles.cronTime}")
        public int resetExcelDay() throws Exception {
        	
        		if (inputMaterialAndCurrentInventoryFilePath.exists() && inputCurrentOrderInPipelineFilePath.exists() && inputCurrentOrderInPipelineDetailsFilePath.exists()
        				&& inputLastIssuanceFilePath.exists() && inputLastPurchaseReceiptFilePath.exists()) {
					
				} else {
							
					 		emailService.sendMailForExcelNotPresent();
							throw BRSException.throwException("Error: Excel file not present");
							
				}
        	
        			
                logger.info("LoadExcelFilesScheduler =======================================>>>>>>>> " + new Date().getTime());

                currentOrdersInPipelineService.getCurrentOrdersInPipelineSummaryExcelFileData();
                
                logger.info("----------" + "Current Orders In Pipeline Service Summary Excel Completed" + "----------------");
                
                currentOrdersInPipelineDetailService.getCurrentOrdersInPipelineDetailsExcelFileData();
                
                logger.info("----------" + "Current Orders In Pipeline Service Detail Excel Completed" + "----------------");
                
                materialInfoAndCurrentInventoryService.uploadExcelFileData();
                
                logger.info("----------" + "Material Info And Current Inventory Excel Completed" + "----------------");
                
                lastPurchaseReceiptService.uploadLastPurchaseReceiptData();
                
                logger.info("----------" + "Last Purchase Receipt Excel Completed" + "----------------");
                
                lastIssuanceService.uploadLastIssuanceData();
                
                logger.info("----------" + "Last Issuance Service Excel Completed" + "----------------");
                
               // importedMatDataService.uploadImportedMatData();
                
                //logger.info("----------" + "Imported Material Data Excel completed" + "----------------");
                
                resultService.runProcedureForImportedExcelDataDate();
                
                logger.info("----------" + "Procedure Completed" + "----------------");
                
                return 0;
                
        }
        
        @Scheduled(cron = "${schedule.matimportexcelfile.cronTime}")
        public int schedulerforimportedmaterial() throws Exception {
        	
        		if (importedMaterialFilePath.exists()) {
					
				} else {
						
					emailService.sendMailForExcelNotPresent();
					throw BRSException.throwException("RM inventory control format excel file not present");
				}
                
                importedMatDataService.uploadImportedMatData();
                
                resultService.callImportItemProcedure();
                
                logger.info("----------" + "Imported Material Data Excel completed" + "----------------");
                
                return 0;
                
        }
        
        
	/*
	 * @Scheduled(cron = "${schedule.procedure.cronTime}") public int
	 * scheduleProcedureTime() throws Exception {
	 * 
	 * logger.
	 * info("ScheduleProcedureForImportedExcelDataTime ==============================>>>>>>>> "
	 * + new Date().getTime());
	 * 
	 * 
	 * 
	 * return 0;
	 * 
	 * }
	 */
        
}
