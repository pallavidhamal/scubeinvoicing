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
@Table(name = "expense")
@Getter @Setter
public class ExpenseInfoEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_vendor")
	private VendorMasterEntity vendorMasterEntity;

	@Column(name = "payment_account")
	private String paymentAccount;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@OneToOne
	@JoinColumn(name = "fk_payment_method")
	private PaymentMethodEntity paymentMethodEntity;
	
	@Column(name = "reference_no")
	private String referenceNo;
	
	
	
}
