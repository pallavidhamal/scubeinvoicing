package com.scube.invoicing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scube.invoicing.service.GenerateInvoiceAndCreditNoteService;

@Component
public class Schedulers {
	
	@Autowired
	GenerateInvoiceAndCreditNoteService generateInvoiceAndCreditNoteService;
	
	private static final Logger logger = LoggerFactory.getLogger(Schedulers.class);
	
	@Scheduled(cron = "${mail.send.cronTime}")
	public int sendInvoiceMailToCustomer() throws Exception {
		logger.info("----- Schedulers sendInvoiceMailToCustomer -----");
		generateInvoiceAndCreditNoteService.sendGeneratedInvoiceMail();
		return 0;
	}
	
	@Scheduled(cron = "${mail.send.cronTime}")
	public int sendCreditNoteMailToCustomer() throws Exception {
		logger.info("----- Schedulers sendCreditNoteMailToCustomer -----");
		generateInvoiceAndCreditNoteService.sendGeneratedCreditNoteMail();
		return 0;
	}

}
