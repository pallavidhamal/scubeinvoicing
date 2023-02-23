package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.PaymentMethodResponseDto;
import com.scube.invoicing.entity.PaymentMethodEntity;

public class PaymentMethodResponseMapper {

	public static PaymentMethodResponseDto toPaymentMethodResponseDto(PaymentMethodEntity paymentMethodEntity) {
		return new PaymentMethodResponseDto()
				.setPaymentMethodID(paymentMethodEntity.getId())
				.setPaymentMethodName(paymentMethodEntity.getMethodName());
	}
	
	public static List<PaymentMethodResponseDto> toPaymentMethodResponseDtosList(List<PaymentMethodEntity> paymentMethodEntitiesList) {
		List<PaymentMethodResponseDto> paymentMethodResponseDtosList = new ArrayList<PaymentMethodResponseDto>();
		
		for(PaymentMethodEntity paymentMethodEntity : paymentMethodEntitiesList) {
			paymentMethodResponseDtosList.add(toPaymentMethodResponseDto(paymentMethodEntity));
		}
		
		return paymentMethodResponseDtosList;
	}
	
}
