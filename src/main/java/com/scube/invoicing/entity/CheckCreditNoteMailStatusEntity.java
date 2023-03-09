package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "check_credit_note_mail_status")
@Getter @Setter
public class CheckCreditNoteMailStatusEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "fk_customer")
	private CustomerMasterEntity customerMasterEntity;
	
	@OneToOne
	@JoinColumn(name = "fk_customer_credit_note")
	private CustomerCreditNoteEntity customerCreditNoteEntity;
	
	@Column(name = "mail_status")
	private String mailStatus;
	
	@Column(name = "to_mail_id")
	private String toMailID;
	
	@Column(name = "from_mail_id")
	private String fromMailID;
	
	@Column(name = "bcc_mail_id")
	private String bccMailID;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "mail_body")
	private String mailBody;

}
