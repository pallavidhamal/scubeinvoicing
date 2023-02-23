package com.scube.invoicing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.PaymentMethodResponseDto;
import com.scube.invoicing.dto.mapper.PaymentMethodResponseMapper;
import com.scube.invoicing.entity.PaymentMethodEntity;
import com.scube.invoicing.repository.PaymentMethodRepository;


@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

	@Override
	public List<PaymentMethodResponseDto> getAllPaymentMethodList() {
		// TODO Auto-generated method stub
		logger.info("---- PaymentMethodServiceImpl getAllPaymentMethodList ------");
		
		List<PaymentMethodEntity> paymentMethodEntitiesList = paymentMethodRepository.findAll();
		
		return PaymentMethodResponseMapper.toPaymentMethodResponseDtosList(paymentMethodEntitiesList);
	}

	@Override
	public PaymentMethodEntity getPaymentMethodEntityByPaymentMethodID(String paymentMethodID) {
		// TODO Auto-generated method stub
		return paymentMethodRepository.findById(paymentMethodID).get();
	}
}
