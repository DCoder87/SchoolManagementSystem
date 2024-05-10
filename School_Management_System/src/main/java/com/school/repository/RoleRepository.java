package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.school.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRollname(String rollname);
	
	@Query(value = "SELECT COUNT(*) FROM role", nativeQuery = true)
	int countOfRoles();

	
}
