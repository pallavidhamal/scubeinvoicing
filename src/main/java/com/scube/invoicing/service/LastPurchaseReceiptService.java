package com.scube.invoicing.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scube.invoicing.entity.LastPurchaseReceiptEntity;

public interface LastPurchaseReceiptService {
	
	List<LastPurchaseReceiptEntity> uploadLastPurchaseReceiptData () throws Exception;
	
}
