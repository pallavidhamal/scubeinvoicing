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
@Table(name = "customer_invoice_details")
@Getter @Setter
public class CustomerInvoiceEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;

	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "invoice_date")
	private Date invoiceDate;
	
	@Column(name = "sub_total")
	private double subTotal;
	
	@Column(name = "total_amount")
	private double totalAmount;
	
	@Column(name = "discounts")
	private double discounts;
	
	@Column(name = "deposit")
	private double deposit;
	
	@Column(name = "balance")
	private double balance;
	
	@Column(name = "cgst_amount")
	private double cgstAmount;
	
	@Column(name = "sgst_amount")
	private double sgstAmount;
	
	@Column(name = "message_invoice")
	private String messageInvoice;
	
	@Column(name = "message_statement")
	private String messageStatement;
	
	@Column(name = "due_date")
	private Date dueDate;
	
	@Column(name = "tracking_no")
	private String trackingNo;
	
	@Column(name = "cust_email_id")
	private String custEmailId;
	
	@Column(name = "cust_billing_address")
	private String customerBillingAddress;
	
	@Column(name = "terms")
	private String terms;
	
	@Column(name = "shipping_to")
	private String shippingTo;
	
	@Column(name = "shipping_via")
	private String shippingVia;
	
	@Column(name = "shipping_date")
	private Date shippingDate;
	
}
