package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Entity
@Table(name = "invoice_ledger")
@Getter @Setter
public class InvoiceLedgerEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "fk_ledger_mst")
	private LedgerMasterEntity ledgerMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_customer_invoice")
	private CustomerInvoiceEntity customerInvoiceEntity;
	
	@Column(name = "amount")
	private String amount;
	
}
