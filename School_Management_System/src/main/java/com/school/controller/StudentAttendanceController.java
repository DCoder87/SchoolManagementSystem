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
import org.springframework.web.bind.annotation.RestController;
import com.school.beans.StudentAttendance;
import com.school.commom.responses.JsonResponses;
import com.school.request.StudentAttendanceRequest;
import com.school.service.StudentAttendanceService;

@RestController
@RequestMapping("/student/attendances")
public class StudentAttendanceController {

	@Autowired
	private StudentAttendanceService studentAttendanceService;

	LocalDateTime today = LocalDateTime.now();

	@GetMapping("/{institute_id}")
	public Map<String, Object> getAttendance(@PathVariable String institute_id) {

		List<StudentAttendance> allAttendance = studentAttendanceService.getAllAttendances(institute_id);

		if (allAttendance.isEmpty()) {
			return JsonResponses.generateResponse1(false, allAttendance, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allAttendance, "Student Attendance Details Get Successfully");
		}
	}

	// Add Attendance
	@PostMapping("/{institute_id}")
	public Map<String, Object> addAttendance(@RequestBody StudentAttendanceRequest studentAttendanceRequest,
			@PathVariable String institute_id) {
		try {
			StudentAttendance details = studentAttendanceService.createAttendance(studentAttendanceRequest,
					institute_id);
			// If details is not null, attendance was created successfully
			if (details != null) {
				return JsonResponses.generateResponse1(true, details, "Student attendance added successfully");
			} else {
				// This block will not be reached if an exception is thrown in the
				// createAttendance method
				return JsonResponses.generateResponse1(false, null, "Student attendance data is invalid");
			}
		} catch (IllegalArgumentException e) {
			// If attendance already exists or data is invalid
			return JsonResponses.generateResponse1(false, null, e.getMessage());
		} catch (Exception e) {
			// Internal server error
			return JsonResponses.generateResponse1(false, null, "Internal server error");
		}
	}

	// get attendance
	@GetMapping("/{institute_id}/{student_attendance_id}")
	public Map<String, Object> findUserById(@PathVariable String institute_id,
			@PathVariable int student_attendance_id) {
		Optional<StudentAttendance> OneUser = studentAttendanceService.findById(student_attendance_id, institute_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "Student attendance Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, student_attendance_id,
					"Student attendance Not Found for Id " + student_attendance_id);
		}
	}

	// Update attendance
	@PutMapping("/update/{institute_id}/{student_attendance_id}")
	public Map<String, Object> updateUserById(@PathVariable String institute_id,
			@PathVariable int student_attendance_id, @RequestBody StudentAttendanceRequest studentAttendanceRequest) {

		Optional<StudentAttendance> attendance1 = studentAttendanceService.findById(student_attendance_id,
				institute_id);

		if (attendance1 != null) {

			StudentAttendance updatedAttendance = studentAttendanceService
					.updateStudentAttendance(studentAttendanceRequest, institute_id, student_attendance_id);
			if (updatedAttendance != null) {
				return JsonResponses.generateResponse1(true, updatedAttendance,
						"Student attendance Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, student_attendance_id,
						"Student attendance Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, student_attendance_id,
					"Student attendance Not Found fot this ID");
		}
	}

	@Transactional
	@DeleteMapping("/{institute_id}/{student_attendance_id}")
	public Map<String, Object> deleteAttendance(@PathVariable String institute_id,
			@PathVariable int student_attendance_id) throws Exception {

		int deleted = studentAttendanceService.deleteById(student_attendance_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Student attendance Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false,
					"No Student attendance Found For this ID " + student_attendance_id);
		}
	}

	@GetMapping("/deactive_student/{institute_id}")
	public Map<String, Object> getDeletedStudent(@PathVariable String institute_id) {

		List<StudentAttendance> deletedStudent = studentAttendanceService.getDeletedStudent(institute_id);

		if (deletedStudent.isEmpty()) {
			return JsonResponses.generateResponse1(false, deletedStudent, "List is Empty");

		} else {
			return JsonResponses.generateResponse1(true, deletedStudent, "Deleted Student Get Successfully");
		}
	}

	@Transactional
	@PutMapping("/active/{institute_id}/{student_attendance_id}")
	public Map<String, Object> activeStudentById(@PathVariable String institute_id,
			@PathVariable int student_attendance_id) throws Exception {

		int activated = studentAttendanceService.activeStudentById(student_attendance_id, institute_id);

		if (activated == 1) {
			return JsonResponses.generateResponse2(true, "Student Activated Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Student Found For this ID " + student_attendance_id);
		}

	}

}
