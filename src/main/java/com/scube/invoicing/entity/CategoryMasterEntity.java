package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_expense_category")
@Getter @Setter
public class CategoryMasterEntity extends BaseEntity {

	@Column(name = "expense_category_name")
	private String expenseCategoryName;
	
}
