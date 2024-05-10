package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.Users;
import com.school.request.UserRequest;

public interface UsersService {
	
	boolean saveUser(UserRequest user,String company_id);
	
	boolean saveUser1(Users user);

	List<Users> getallUsers(String company_id);

	Optional<Users> findById(int id, String company_id);

	boolean updateUser(UserRequest user,String company_id,int id);

	List<Users> getdeletedUsers(String company_id);

	int deleteUserById(String updated, int id,String company_id);

	int activeUserById(String updated, int id,String company_id);
	
	Users addRoleToUser(String username, String roleName);
	
	Users removeRoleFromUser(String username, String roleName);
	
	Users findByEmail(String email);
	
	Users findByUsername(String username);
	
	int updatePasswordByEmailAndId(String password, String email, int user_id,String company_id2);

	Optional<Users> getByUsername(String username);

}
