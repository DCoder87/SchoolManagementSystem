package com.school.service;

import java.util.List;

import com.school.beans.Role;


public interface RoleService {
	
	boolean saveRole(Role role);
	
	public Role findByRollname(String rollname);
	
	void saveAllRoles(List<Role> roles);

}
