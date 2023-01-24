package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.RoleDto;
import com.scube.invoicing.dto.incoming.RoleIncomingDto;
import com.scube.invoicing.entity.RoleEntity;

public interface RoleService {

	
	RoleEntity	findRoleNameByCode(String code);
	RoleEntity	findRoleId(String id);

	boolean addRole(@Valid RoleIncomingDto roleIncomingDto);

	boolean editRole(@Valid RoleIncomingDto roleIncomingDto);
	boolean deleteRole(String id);
	 List<RoleDto>  findAllRoles();
	 List<RoleDto> findActiveRoles();
	 RoleDto getRoleById(String id);
	

	
}
