package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name = "last_purchase_receipt")
public class LastPurchaseReceiptEntity {
	
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column (name = "material_code")
	private String materialCode;
	
	@Column (name = "receipt_date")
	private String receiptDate;
	
	@Column (name = "receipt_qty")
	private double receiptQuantity;
	
	@Column (name = "record_date")
	private String recordDate;
	
	
}
