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

import com.school.beans.TeacherSubject;
import com.school.commom.responses.JsonResponses;
import com.school.request.TeacherSubjectRequest;
import com.school.service.TeacherSubjectService;

@RestController
@RequestMapping("/teachersubject")
public class TeacherSubjectController {

	@Autowired
	TeacherSubjectService teacherSubjectService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getTeacherSubjects(@PathVariable String institute_id) {

		List<TeacherSubject> allTeacherSubjects = teacherSubjectService.getAllTeacherSubjects(institute_id);

		if (allTeacherSubjects.isEmpty()) {
			return JsonResponses.generateResponse1(false, allTeacherSubjects,
					"Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allTeacherSubjects,
					"Teacher Subjects Details Get Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addTeacherSubject(@PathVariable String institute_id,
			@RequestBody TeacherSubjectRequest teacherSubjectRequest) {

		TeacherSubject savedTeacherSubject = teacherSubjectService.saveTeacherSubject(teacherSubjectRequest,
				institute_id);

		if (savedTeacherSubject != null) {
			return JsonResponses.generateResponse1(true, savedTeacherSubject, "Teacher Subject Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, teacherSubjectRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{mapped_id}")
	public Map<String, Object> updateTeacherSubjectById(@PathVariable String institute_id, @PathVariable int mapped_id,
			@RequestBody TeacherSubjectRequest teacherSubjectRequest) {

		TeacherSubject updatedTeacherSubject = teacherSubjectService.updateTeacherSubject(teacherSubjectRequest,
				institute_id, mapped_id);

		if (updatedTeacherSubject != null) {
			return JsonResponses.generateResponse1(true, teacherSubjectRequest,
					"Teacher Subject Data Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, mapped_id,
					"Teacher Subject Not Found for this ID: " + mapped_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{mapped_id}")
	public Map<String, Object> deleteTeacherSubjectById(@PathVariable String institute_id, @PathVariable int mapped_id)
			throws Exception {

		int deleted = teacherSubjectService.deleteTeacherSubjectByMappedId(mapped_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Teacher Subject Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Teacher Subject Found For this ID " + mapped_id);
		}
	}

	@GetMapping("/edit/{institute_id}/{mapped_id}")
	public Map<String, Object> findTeacherSubjectByMappedId(@PathVariable String institute_id,
			@PathVariable int mapped_id) {

		Optional<TeacherSubject> teacherSubject = teacherSubjectService.findTeacherSubjectByMappedId(mapped_id,
				institute_id);

		if (teacherSubject.isPresent()) {
			return JsonResponses.generateResponse1(true, teacherSubject, "Teacher Subject Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, mapped_id, "Teacher Subject Not Found for Id " + mapped_id);
		}
	}
}
