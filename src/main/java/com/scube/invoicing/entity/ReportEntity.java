package com.scube.invoicing.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_view")
@Getter @Setter
public class ReportEntity extends BaseEntity{

}
