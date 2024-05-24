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

import com.school.beans.ExamSchedule;
import com.school.commom.responses.JsonResponses;
import com.school.request.ExamScheduleRequest;
import com.school.service.ExamScheduleService;

@RestController
@RequestMapping("/examschedule")
public class ExamScheduleController {

	@Autowired
	ExamScheduleService examScheduleService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getExamSchedules(@PathVariable String institute_id) {

		List<ExamSchedule> allExamSchedules = examScheduleService.getallExamSchedules(institute_id);

		if (allExamSchedules.isEmpty()) {
			return JsonResponses.generateResponse1(false, allExamSchedules,
					"Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allExamSchedules, "Exam Schedules Details Got Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addExamSchedule(@PathVariable String institute_id,
			@RequestBody ExamScheduleRequest examScheduleRequest) {

		ExamSchedule savedExamSchedule = examScheduleService.saveExamSchedule(examScheduleRequest, institute_id);

		if (savedExamSchedule != null) {
			return JsonResponses.generateResponse1(true, savedExamSchedule, "Exam Schedule Added Successfully");

		} else {
			return JsonResponses.generateResponse1(false, examScheduleRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{exam_id}")
	public Map<String, Object> updateExamScheduleById(@PathVariable String institute_id, @PathVariable int exam_id,
			@RequestBody ExamScheduleRequest examScheduleRequest) {

		ExamSchedule updatedExamSchedule = examScheduleService.updateExamSchedule(examScheduleRequest, institute_id,
				exam_id);

		if (updatedExamSchedule != null) {
			return JsonResponses.generateResponse1(true, examScheduleRequest,
					"Exam Schedule Data Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, exam_id, "Exam Schedule Not Found for this ID: " + exam_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{exam_id}")
	public Map<String, Object> deleteExamScheduleById(@PathVariable String institute_id, @PathVariable int exam_id)
			throws Exception {

		int deleted = examScheduleService.deleteExamScheduleByid(exam_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Exam Schedule Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Exam Schedule Found For this ID " + exam_id);
		}

	}

	@GetMapping("/edit/{institute_id}/{exam_id}")
	public Map<String, Object> findExamScheduleById(@PathVariable String institute_id, @PathVariable int exam_id) {

		Optional<ExamSchedule> examSchedule = examScheduleService.findExamScheduleById(exam_id, institute_id);

		if (examSchedule.isPresent()) {
			return JsonResponses.generateResponse1(true, examSchedule, "Exam Schedule Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, exam_id, "Exam Schedule Not Found for Id " + exam_id);
		}
	}
}
