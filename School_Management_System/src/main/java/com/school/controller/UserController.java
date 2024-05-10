package com.school.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.service.UsersService;
import com.school.beans.Users;
import com.school.commom.responses.JsonResponses;
import com.school.request.UserRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UsersService usersService;

	LocalDateTime today = LocalDateTime.now();

	@GetMapping("/{institute_id}")
	public Map<String, Object> getUsers(@PathVariable String institute_id) {

		List<Users> allUsers = usersService.getallUsers(institute_id);

		if (allUsers.isEmpty()) {
			return JsonResponses.generateResponse1(false, allUsers, "Institute Id is Invalid Or List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allUsers, "User Details Get Successfully");
		}
	}

	// User register
	// @Secured("ROLE_ADMIN")
	@PostMapping("/{institute_id}")
	public Map<String, Object> registerNewUser(@RequestBody UserRequest user, @PathVariable String institute_id) {
		String userEmail = user.getEmail();
		String username = user.getUsername();

		Users users = usersService.findByEmail(userEmail);
		Users validateUsername = usersService.findByUsername(username);

		if (users == null && validateUsername == null) {

			boolean details = usersService.saveUser(user, institute_id);

			if (details) {
				return JsonResponses.generateResponse1(true, user, "User Registered Successfully");
			} else {
				return JsonResponses.generateResponse1(false, user, "Some Data is Null or Invalid");
			}

		} else {
			return JsonResponses.generateResponse1(false, userEmail, "Email Or Username Already Exists");
		}

	}

	// Edit User
	@GetMapping("/edit/{institute_id}/{user_id}")
	public Map<String, Object> findUserById(@PathVariable String institute_id, @PathVariable int user_id) {
		Optional<Users> OneUser = usersService.findById(user_id, institute_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "User Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, user_id, "User Not Found for Id " + user_id);
		}
	}

	// Update User
	@PutMapping("/update/{institute_id}/{user_id}")
	public Map<String, Object> updateUserById(@PathVariable String institute_id, @PathVariable int user_id,
			@RequestBody UserRequest user) {

		Optional<Users> users = usersService.findById(user_id, institute_id);

		if (users != null) {

			boolean updatedUser = usersService.updateUser(user, institute_id, user_id);
			if (updatedUser) {
				return JsonResponses.generateResponse1(true, updatedUser, "UserData Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, user_id, "User Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, user_id, "User Not Found fot this ID");
		}
	}

	@Transactional
	@DeleteMapping("/{institute_id}/{user_id}")
	public Map<String, Object> deleteUserById(@PathVariable String institute_id, @PathVariable int user_id)
			throws Exception {

		String today1 = today.toString();
		int deleted = usersService.deleteUserById(today1, user_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "User Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No User Found For this ID " + user_id);
		}

	}

	@GetMapping("/deactive_users/{institute_id}")
	public Map<String, Object> getDeletedUsers(@PathVariable String institute_id) {

		List<Users> deletedUsers = usersService.getdeletedUsers(institute_id);

		if (deletedUsers.isEmpty()) {
			return JsonResponses.generateResponse1(false, deletedUsers, "List is Empty");

		} else {
			return JsonResponses.generateResponse1(true, deletedUsers, "Deleted Users Get Successfully");
		}
	}

	@Transactional
	@PutMapping("/active/{institute_id}/{user_id}")
	public Map<String, Object> activeUserById(@PathVariable String institute_id, @PathVariable int user_id)
			throws Exception {

		String today1 = today.toString();
		int activated = usersService.activeUserById(today1, user_id, institute_id);

		if (activated == 1) {
			return JsonResponses.generateResponse2(true, "User Activated Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No User Found For this ID " + user_id);
		}

	}

	@PostMapping("/add_role/{username}")
	public Map<String, Object> addRoleToUser(@PathVariable String username, @RequestParam String roleName)
			throws Exception {
		Users userRole = usersService.addRoleToUser(username, roleName);
		if (userRole != null) {
			return JsonResponses.generateResponse1(true, userRole, "Role added to the user successfully");
		} else {
			return JsonResponses.generateResponse1(false, username, "User Not Found fot this Username");
		}

	}

	@PostMapping("/remove_role/{username}")
	public Map<String, Object> removeRoleFromUser(@PathVariable String username, @RequestParam String roleName)
			throws Exception {
		Users userRole = usersService.removeRoleFromUser(username, roleName);
		if (userRole != null) {
			return JsonResponses.generateResponse1(true, userRole, "Role removed from the user successfully");
		} else {
			return JsonResponses.generateResponse1(false, username, "User Not Found fot this Username");
		}

	}

}
