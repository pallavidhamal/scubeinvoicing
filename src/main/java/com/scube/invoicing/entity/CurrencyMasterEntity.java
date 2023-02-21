package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Entity
@Table(name = "mst_currency")
@Getter @Setter
public class CurrencyMasterEntity extends BaseEntity {

	@Column(name = "currency_name")
	private String currencyName;
	
}
