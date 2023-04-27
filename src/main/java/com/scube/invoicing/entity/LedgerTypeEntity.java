package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ledger_type")
@Getter @Setter
public class LedgerTypeEntity extends BaseEntity {

	@Column(name = "ledger_type_name")
	private String legderType;
	
}
