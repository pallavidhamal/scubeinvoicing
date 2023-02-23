package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_payment_method")
@Getter @Setter
public class PaymentMethodEntity extends BaseEntity {

	@Column(name = "payment_method_name")
	private String methodName;
	
}
