package com.scube.invoicing.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.scube.invoicing.service.ChangesInMatMasterService;

public class SchedulerForChangesInMatMaster {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerForChangesInMatMaster.class);
	
	  @Autowired
	  ChangesInMatMasterService changesInMatMasterService;
	  
	   @Scheduled(cron = "${updateStart.cronTime}")
    public int updateStartDay() throws Exception {
            
		   
		   logger.info("entered in Changes in material master ------------------ " + new Date().getTime());
		   
		   changesInMatMasterService.getChangesInMatMasterByLast7Days();
            
           logger.info("Changes in material master records ------------------ " + new Date().getTime());
            
            return 0;
            
    }
}
