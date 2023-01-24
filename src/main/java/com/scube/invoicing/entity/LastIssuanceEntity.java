package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name = "last_issuance")
public class LastIssuanceEntity {

	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "material_code")
	private String materialCode;
	
	@Column (name = "issue_date")
	private String issueDate;
	
	@Column (name = "issuance_qty")
	private double issueQuantity;
	
	@Column (name = "record_date")
	private String recordDate;
	
}
