package com.scube.invoicing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_vendor")
@Getter @Setter
public class VendorMasterEntity extends BaseEntity {
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@Column(name = "vendor_email_id")
	private String vendorEmailID;
	
	@Column(name = "vendor_contact_no")
	private String vendorContactNo;

}
