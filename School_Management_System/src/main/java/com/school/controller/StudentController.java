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

import com.school.beans.Student;
import com.school.commom.responses.JsonResponses;
import com.school.request.StudentRequest;
import com.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/{institution_id}")
	public Map<String, Object> getStudents(@PathVariable String institution_id) {

		List<Student> allStudents = studentService.getallStudents(institution_id);

		if (allStudents.isEmpty()) {
			return JsonResponses.generateResponse1(false, allStudents, "Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allStudents, "Students Details Get Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addStudent(@PathVariable String institute_id,
			@RequestBody StudentRequest studentRequest) {
		Student savedStudent = studentService.saveStudent(studentRequest, institute_id);

		if (savedStudent != null) {
			return JsonResponses.generateResponse1(true, savedStudent, "Student Added Successfully");

		} else {
			return JsonResponses.generateResponse1(false, studentRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{student_id}")
	public Map<String, Object> updateStudentById(@PathVariable String institute_id, @PathVariable int student_id,
			@RequestBody StudentRequest studentRequest) {

		Student updatedStudent = studentService.updateStudent(studentRequest, institute_id, student_id);

		if (updatedStudent != null) {
			return JsonResponses.generateResponse1(true, studentRequest, "Student Data Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, student_id, "Student Not Found for this ID: " + student_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{student_id}")
	public Map<String, Object> deleteStudentById(@PathVariable String institute_id, @PathVariable int student_id)
			throws Exception {

		int deleted = studentService.deleteStudentByid(student_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Student Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Student Found for this ID: " + student_id);
		}

	}

	@GetMapping("/edit/{institute_id}/{student_id}")
	public Map<String, Object> findStudentById(@PathVariable String institute_id, @PathVariable int student_id) {
		Optional<Student> student = studentService.findStudentById(student_id, institute_id);
		if (student.isPresent()) {
			return JsonResponses.generateResponse1(true, student, "Student Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, student_id, "Student Not Found for ID: " + student_id);
		}
	}

}
