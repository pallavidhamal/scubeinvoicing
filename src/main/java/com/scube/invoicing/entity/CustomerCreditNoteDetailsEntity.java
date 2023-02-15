package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_credit_note_details")
@Getter @Setter
public class CustomerCreditNoteDetailsEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_customer_master")
	private CustomerMasterEntity customerMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_tax")
	private GSTMasterEntity gstMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_credit_note")
	private CustomerCreditNoteEntity customerCreditNoteEntity;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "rate")
	private double rate;
	
	@Column(name = "amount")
	private double amount;

}
