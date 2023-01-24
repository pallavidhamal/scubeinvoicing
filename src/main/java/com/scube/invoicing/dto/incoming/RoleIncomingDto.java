package com.scube.invoicing.dto.incoming;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleIncomingDto {

	private String name;
	private String namecode;
	private String status;
	private String id;
}
