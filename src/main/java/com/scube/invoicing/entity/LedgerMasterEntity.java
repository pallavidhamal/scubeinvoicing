package com.scube.invoicing.entity;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "mst_ledger")
@Getter @Setter
public class LedgerMasterEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_ledger_type")
	private LedgerTypeEntity ledgerTypeEntity;
	
	@Column(name = "ledger_name")
	private String ledgerName;
	
	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_service")
	private ServiceMasterEntity serviceMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_tax")
	private GSTMasterEntity gstMasterEntity;

	@OneToOne
	@JoinColumn(name = "fk_expense")
	private ExpenseInfoEntity expenseInfoEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_category")
	private CategoryMasterEntity categoryMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_vendor")
	private VendorMasterEntity vendorMasterEntity;
	
}
