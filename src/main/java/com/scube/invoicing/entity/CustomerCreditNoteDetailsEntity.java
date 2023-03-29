package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_credit_note_service")
@Getter @Setter
public class CustomerCreditNoteDetailsEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_tax")
	private GSTMasterEntity gstMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_credit_note")
	private CustomerCreditNoteEntity customerCreditNoteEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_service")
	private ServiceMasterEntity serviceMasterEntity;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "rate")
	private double rate;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "service_amount_with_gst")
	private String serviceAmountWithGst;
	
	@Column(name = "gst_Amount")
	private String gstAmount;
	

}
