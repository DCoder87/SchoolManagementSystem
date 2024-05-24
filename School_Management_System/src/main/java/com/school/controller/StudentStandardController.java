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

import com.school.beans.StudentStandard;
import com.school.commom.responses.JsonResponses;
import com.school.request.GradeRequest;
import com.school.request.StudentStandardRequest;
import com.school.service.StudentStandardService;

@RestController
@RequestMapping("/studentStandard")
public class StudentStandardController {

	@Autowired
	StudentStandardService studentStandardService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getStudentStandards(@PathVariable String institute_id) {

		List<StudentStandard> allStudentStandards = studentStandardService.getallStudentStandards(institute_id);

		if (allStudentStandards.isEmpty()) {
			return JsonResponses.generateResponse1(false, allStudentStandards,
					"Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allStudentStandards,
					"Student Standards Details Get Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addStudentStandard(@PathVariable String institute_id,
			@RequestBody StudentStandardRequest studentStandardRequest) {
		boolean savedStudentStandard = studentStandardService.saveStudentStandard(studentStandardRequest, institute_id);

		if (savedStudentStandard) {
			return JsonResponses.generateResponse1(true, savedStudentStandard, "Student Standard Added Successfully");

		} else {
			return JsonResponses.generateResponse1(false, studentStandardRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/grades/{institute_id}")
    public Map<String, Object> updateStudentStandardGrades(@PathVariable String institute_id,
            @RequestBody List<GradeRequest>  gradeRequest) {
        boolean updatedStudentStandard = studentStandardService.updateBulkStudentStandard(gradeRequest, institute_id);

        if (updatedStudentStandard) {
            return JsonResponses.generateResponse2(true, "Student Standard Grades Updated Successfully");

        } else {
            return JsonResponses.generateResponse2(false, "Some Data is Null or Invalid");
        }
    }

	@PutMapping("/update/{institute_id}/{studentStandard_id}")
	public Map<String, Object> updateStudentStandardById(@PathVariable String institute_id,
			@PathVariable int studentStandard_id, @RequestBody StudentStandardRequest studentStandardRequest) {

		boolean updatedStudentStandard = studentStandardService.updateStudentStandard(studentStandardRequest,
				institute_id, studentStandard_id);

		if (updatedStudentStandard) {
			return JsonResponses.generateResponse1(true, studentStandardRequest,
					"Student Standard Data Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, studentStandard_id,
					"Student Standard Not Found for this ID: " + studentStandard_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{studentStandard_id}")
	public Map<String, Object> deleteStudentStandardById(@PathVariable String institute_id,
			@PathVariable int studentStandard_id) throws Exception {

		int deleted = studentStandardService.deleteStudentStandardByid(studentStandard_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Student Standard Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false,
					"No Student Standard Found for this ID: " + studentStandard_id);
		}

	}

	@GetMapping("/edit/{institute_id}/{studentStandard_id}")
	public Map<String, Object> findStudentStandardById(@PathVariable String institute_id,
			@PathVariable int studentStandard_id) {
		Optional<StudentStandard> studentStandard = studentStandardService.findStudentStandardById(studentStandard_id,
				institute_id);
		if (studentStandard.isPresent()) {
			return JsonResponses.generateResponse1(true, studentStandard, "Student Standard Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, studentStandard_id,
					"Student Standard Not Found for ID: " + studentStandard_id);
		}
	}

}
