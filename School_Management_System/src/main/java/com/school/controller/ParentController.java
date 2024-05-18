package com.school.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.school.beans.Parent;
import com.school.commom.responses.JsonResponses;
import com.school.request.ParentRequest;
import com.school.service.ParentService;

@RestController
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	ParentService parentService;
	
	@GetMapping("/{institution_id}")
	public Map<String, Object> getParents(@PathVariable String institution_id) {

		List<Parent> allparents = parentService.getallParents(institution_id);

		if (allparents.isEmpty()) {
			return JsonResponses.generateResponse1(false, allparents, "Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allparents, "Parents Details Get Successfully");
		}
  }
	
	@PostMapping("/{institute_id}")
	public Map<String, Object> addParent(@PathVariable String institute_id,
			@RequestBody ParentRequest parentRequest) {
		Parent savedParent = parentService.saveParent(parentRequest, institute_id);

		if (savedParent != null) {
			return JsonResponses.generateResponse1(true, savedParent, "Parent Added Successfully");

		} else {
			return JsonResponses.generateResponse1(false, parentRequest, "Some Data is Null or Invalid");
		}
	}
	
	
	@PutMapping("/update/{institute_id}/{parent_id}")
	public Map<String, Object> updateParentById(@PathVariable String institute_id, @PathVariable int parent_id,
			@RequestBody ParentRequest parentRequest) {

		Parent UpdatedParent = parentService.updateParent(parentRequest, institute_id, parent_id);

		if (UpdatedParent != null) {
			return JsonResponses.generateResponse1(true, parentRequest, "ParentDate Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, parent_id, "Employee Not Found fot this ID" + parent_id);
		}
	}
	
	@Transactional
	@DeleteMapping("/delete/{institute_id}/{parent_id}")
	public Map<String, Object> deleteParentById(@PathVariable String institute_id, @PathVariable int parent_id)
			throws Exception {

		int deleted = parentService.deleteParentByid(parent_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Parent Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Parent Found For this ID " + parent_id);
		}

	}
	
	@GetMapping("/edit/{institute_id}/{parent_id}")
	public Map<String, Object> findEmployeeById(@PathVariable String institute_id, @PathVariable int parent_id) {
		Optional<Parent> OneParent = parentService.findParentById(parent_id, institute_id);
		if (OneParent.isPresent()) {
			return JsonResponses.generateResponse1(true, OneParent, "Parent Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, parent_id, "Parent Not Found for Id " + parent_id);
		}
	}
	
}


