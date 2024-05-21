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
import com.school.beans.TeacherAttendance;
import com.school.commom.responses.JsonResponses;
import com.school.request.TeacherAttendanceRequest;
import com.school.service.TeacherAttendanceService;

@RestController
@RequestMapping("/teacher/attendances")
public class TeacherAttendanceController {

	@Autowired
	private TeacherAttendanceService teacherAttendanceService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getAttendance(@PathVariable String institute_id) {

		List<TeacherAttendance> allAttendance = teacherAttendanceService.getAllAttendances(institute_id);

		if (allAttendance.isEmpty()) {
			return JsonResponses.generateResponse1(false, allAttendance, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allAttendance, "Teacher Attendance Details Get Successfully");
		}
	}

	// Add Attendance
	@PostMapping("/{institute_id}")
	public Map<String, Object> addAttendance(@RequestBody TeacherAttendanceRequest teacherAttendanceRequest,
			@PathVariable String institute_id) {

		TeacherAttendance details = teacherAttendanceService.createAttendance(teacherAttendanceRequest, institute_id);

		if (details != null) {
			return JsonResponses.generateResponse1(true, details, "Teacher attendance Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, details, "Teacher attendance Data is Null or Invalid");
		}

	}

	@GetMapping("/{institute_id}/{teacher_attendance_id}")
	public Map<String, Object> findUserById(@PathVariable String institute_id,
			@PathVariable int teacher_attendance_id) {
		Optional<TeacherAttendance> OneUser = teacherAttendanceService.findByTeacherAttendanceId(teacher_attendance_id,
				institute_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "Teacher attendance Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, teacher_attendance_id,
					"Teacher attendance Not Found for Id " + teacher_attendance_id);
		}
	}

	// Update attendance
	@PutMapping("/update/{institute_id}/{teacher_attendance_id}")
	public Map<String, Object> updateUserById(@PathVariable String institute_id,
			@PathVariable int teacher_attendance_id, @RequestBody TeacherAttendanceRequest teacherAttendanceRequest) {

		Optional<TeacherAttendance> attendance1 = teacherAttendanceService
				.findByTeacherAttendanceId(teacher_attendance_id, institute_id);

		if (attendance1 != null) {

			TeacherAttendance updatedAttendance = teacherAttendanceService
					.updateTeacherAttendance(teacherAttendanceRequest, institute_id, teacher_attendance_id);
			if (updatedAttendance != null) {
				return JsonResponses.generateResponse1(true, updatedAttendance,
						"Teacher attendance Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, teacher_attendance_id,
						"Teacher attendance Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, teacher_attendance_id,
					"Teacher attendance Not Found fot this ID");
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{teacher_attendance_id}")
	public Map<String, Object> deleteAttendance(@PathVariable String institute_id,
			@PathVariable int teacher_attendance_id) throws Exception {

		int deleted = teacherAttendanceService.deleteById(teacher_attendance_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true,
					"teacher attendance Deleted Successfully for this id" + teacher_attendance_id);
		} else {
			return JsonResponses.generateResponse2(false,
					"No teacher attendance Found For this ID " + teacher_attendance_id);
		}
	}

	@GetMapping("/deactive_teacher/{institute_id}")
	public Map<String, Object> getDeletedTeacher(@PathVariable String institute_id) {

		List<TeacherAttendance> deletedTeacher = teacherAttendanceService.getDeletedTeacher(institute_id);

		if (deletedTeacher.isEmpty()) {
			return JsonResponses.generateResponse1(false, deletedTeacher, "List is Empty");

		} else {
			return JsonResponses.generateResponse1(true, deletedTeacher, "Deleted Teacher Get Successfully");
		}
	}

	@Transactional
	@PutMapping("/active/{institute_id}/{teacher_attendance_id}")
	public Map<String, Object> activeTeacherById(@PathVariable String institute_id,
			@PathVariable int teacher_attendance_id) throws Exception {

		int activated = teacherAttendanceService.activeTeacherById(teacher_attendance_id, institute_id);

		if (activated == 1) {
			return JsonResponses.generateResponse2(true, "Teacher Activated Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Teacher Found For this ID " + teacher_attendance_id);
		}

	}
}
