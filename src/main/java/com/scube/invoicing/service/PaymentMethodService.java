package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.dto.PaymentMethodResponseDto;
import com.scube.invoicing.entity.PaymentMethodEntity;

public interface PaymentMethodService {
	
	List<PaymentMethodResponseDto> getAllPaymentMethodList();
	
	PaymentMethodEntity getPaymentMethodEntityByPaymentMethodID(String paymentMethodID);

}
