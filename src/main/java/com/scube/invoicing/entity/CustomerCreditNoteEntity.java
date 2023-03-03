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

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public String getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	
	public String getIgstAmount() {
		return igstAmount;
	}

	public String getCreditsRemaining() {
		return creditsRemaining;
	}

	public void setIgstAmount(String igstAmount) {
		this.igstAmount = igstAmount;
	}

	public String getDeclaredTds() {
		return declaredTds;
	}

	public void setDeclaredTds(String declaredTds) {
		this.declaredTds = declaredTds;
	}

	public String getActualTds() {
		return actualTds;
	}

	public void setActualTds(String actualTds) {
		this.actualTds = actualTds;
	}

	public void setCreditsRemaining(String creditsRemaining) {
		this.creditsRemaining = creditsRemaining;
	}

	public Set<CustomerInvoiceEntity> getCustomerInvoiceEntity() {
		return customerInvoiceEntity;
	}

	public void setCustomerInvoiceEntity(Set<CustomerInvoiceEntity> customerInvoiceEntity) {
		this.customerInvoiceEntity = customerInvoiceEntity;
	}

	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;
	
	@Column(name = "credit_note_no")
	private String creditNoteNo;
	
	@Column(name = "credit_note_date")
	private Date creditNoteDate;
	
	@Column(name = "sub_total")
	private String subTotal;
	
	@Column(name = "total_amount")
	private String totalAmount;
	
	@Column(name = "cgst_amount")
	private String cgstAmount;
	
	@Column(name = "sgst_amount")
	private String sgstAmount;
	
	@Column(name = "igst_amount")
	private String igstAmount;
	
	@Column(name = "declared_tds")
	private String declaredTds;
	
	@Column(name = "actual_tds")
	private String actualTds;
	
	@Column(name = "credits_remaining")
	private String creditsRemaining;
	
	@ManyToMany
	@JoinTable
	private Set<CustomerInvoiceEntity> customerInvoiceEntity;
	
	
	
	
	

}
