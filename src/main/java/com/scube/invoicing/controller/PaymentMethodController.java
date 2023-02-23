package com.scube.invoicing.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.PaymentMethodService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"api/v1/paymentMethod"}, produces = APPLICATION_JSON_VALUE)
public class PaymentMethodController {
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodController.class);
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/getAllPaymentMethodList")
	public Response getAllPaymentMethodList() {
		logger.info("------- PaymentMethodController getAllPaymentMethodList ------");
		return Response.ok().setPayload(paymentMethodService.getAllPaymentMethodList());
	}

}
