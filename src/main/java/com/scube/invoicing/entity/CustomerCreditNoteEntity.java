package com.scube.invoicing.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_credit_note")
public class CustomerCreditNoteEntity extends BaseEntity {
	
	public CustomerMasterEntity getCustomerMasterEntity() {
		return customerMasterEntity;
	}

	public void setCustomerMasterEntity(CustomerMasterEntity customerMasterEntity) {
		this.customerMasterEntity = customerMasterEntity;
	}

	public String getCreditNoteNo() {
		return creditNoteNo;
	}

	public void setCreditNoteNo(String creditNoteNo) {
		this.creditNoteNo = creditNoteNo;
	}

	public Date getCreditNoteDate() {
		return creditNoteDate;
	}

	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public double getCreditsRemaining() {
		return creditsRemaining;
	}

	public void setCreditsRemaining(double creditsRemaining) {
		this.creditsRemaining = creditsRemaining;
	}

	public Set<CustomerInvoiceEntity> getCustomerInvoiceEntity() {
		return customerInvoiceEntity;
	}

	public void setCustomerInvoiceEntity(Set<CustomerInvoiceEntity> customerInvoiceEntity) {
		this.customerInvoiceEntity = customerInvoiceEntity;
	}

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
	
	@ManyToMany
	@JoinTable
	private Set<CustomerInvoiceEntity> customerInvoiceEntity;
	
	
	
	
	

}
