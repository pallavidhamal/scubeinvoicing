package com.scube.invoicing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_credit_note")
@Getter @Setter
public class CustomerCreditNoteEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_customer_master")
	private CustomerMasterEntity customerMasterEntity;
	
	@Column(name = "credit_note_no")
	private String creditNoteNo;
	
	@Column(name = "credit_note_date")
	private Date creditNoteDate;
	
	@Column(name = "sub_total")
	private double subTotal;
	
	@Column(name = "total_amount")
	private double totalAmount;
	
	@Column(name = "cgst_amount")
	private double cgstAmount;
	
	@Column(name = "sgst_amount")
	private double sgstAmount;
	
	@Column(name = "credits_remaining")
	private double creditsRemaining;

}
