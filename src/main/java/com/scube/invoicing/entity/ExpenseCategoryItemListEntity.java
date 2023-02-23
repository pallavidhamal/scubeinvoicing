package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_items_list")
@Getter @Setter
public class ExpenseCategoryItemListEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "fk_expense")
	private ExpenseInfoEntity expenseInfoEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_category")
	private CategoryMasterEntity categoryMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_tax")
	private GSTMasterEntity gstMasterEntity	;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private double amount;
	
	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;
	
}
