package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_ledger")
@Getter @Setter
public class ExpenseLedgerEntity extends BaseEntity {
	
	@Column(name = "amount")
	private String amount;
	
	@OneToOne
	@JoinColumn(name = "fk_expense")
	private ExpenseInfoEntity expenseInfoEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_category")
	private CategoryMasterEntity categoryMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_vendor")
	private VendorMasterEntity vendorMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_ledger_mst")
	private LedgerMasterEntity ledgerMasterEntity;

}
