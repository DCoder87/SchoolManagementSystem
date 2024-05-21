package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.school.beans.TimeTable;
import com.school.commom.responses.JsonResponses;
import com.school.request.TimeTableRequest;
import com.school.service.TimeTableService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/timetables")
public class TimeTableController {

	@Autowired
	private TimeTableService timetableService;

	// Get all timetables by institute_id
	@GetMapping("/{institute_id}")
	public Map<String, Object> getTimetablesByInstituteId(@PathVariable String institute_id) {
		List<TimeTable> allTimetables = timetableService.getAllTimetablesById(institute_id);
		if (allTimetables.isEmpty()) {
			return JsonResponses.generateResponse1(false, allTimetables, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allTimetables, "Timetables fetched successfully");
		}
	}

	// Add a new timetable
	@PostMapping("/{institute_id}")
	public Map<String, Object> addTimetable(@RequestBody TimeTableRequest timetable,
			@PathVariable String institute_id) {
		try {
			TimeTable createdTimetable = timetableService.createTimetable(timetable, institute_id);
			if (createdTimetable != null) {
				return JsonResponses.generateResponse1(true, createdTimetable, "Timetable added successfully");
			} else {
				return JsonResponses.generateResponse1(false, null, "Timetable data is invalid");
			}
		} catch (IllegalArgumentException e) {
			return JsonResponses.generateResponse1(false, null, e.getMessage());
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, null, "Internal server error");
		}
	}

	// Get a specific timetable by its ID
	@GetMapping("/{institute_id}/{timetable_id}")
	public Map<String, Object> getTimetableById(@PathVariable String institute_id, @PathVariable int timetable_id) {
		Optional<TimeTable> timetable = timetableService.getTimetableById(timetable_id, institute_id);
		if (timetable != null) {
			return JsonResponses.generateResponse1(true, timetable, "Timetable fetched successfully");
		} else {
			return JsonResponses.generateResponse1(false, timetable_id, "Timetable not found for ID " + timetable_id);
		}
	}

	// Update a specific timetable
	@PutMapping("/update/{institute_id}/{timetable_id}")
	public Map<String, Object> updateTimetable(@PathVariable String institute_id, @PathVariable int timetable_id,
			@RequestBody TimeTableRequest timetable) {
		try {
			TimeTable updatedTimetable = timetableService.updateTimetable(timetable_id, institute_id, timetable);
			if (updatedTimetable != null) {
				return JsonResponses.generateResponse1(true, updatedTimetable, "Timetable updated successfully");
			} else {
				return JsonResponses.generateResponse1(false, null, "Timetable not found for ID " + timetable_id);
			}
		} catch (IllegalArgumentException e) {
			return JsonResponses.generateResponse1(false, null, e.getMessage());
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, null, "Internal server error");
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{timetable_id}")
	public Map<String, Object> deleteTimetable(@PathVariable String institute_id, @PathVariable int timetable_id) {

		int deleted = timetableService.deleteTimetable(timetable_id, institute_id);
		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Timetable deleted successfully");
		} else {
			return JsonResponses.generateResponse2(false, "Timetable not found for ID " + timetable_id);
		}
	}

	// Get all timetables for deactive_student by institute_id
	@GetMapping("/deactive_student/{institute_id}")
	public Map<String, Object> getDeletedTimetables(@PathVariable String institute_id) {
		List<TimeTable> deletedTimetables = timetableService.getDeletedTimetables(institute_id);
		if (deletedTimetables.isEmpty()) {
			return JsonResponses.generateResponse1(false, deletedTimetables, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, deletedTimetables, "Deleted Timetables fetched successfully");
		}
	}

	// Activate a specific timetable
	@PutMapping("/active/{institute_id}/{timetable_id}")
	public Map<String, Object> activateTimetableById(@PathVariable String institute_id, @PathVariable int timetable_id)
			throws Exception {

		int activated = timetableService.activateTimetableById(timetable_id, institute_id);
		if (activated == 1) {
			return JsonResponses.generateResponse2(true, "Timetable activated successfully");
		} else {
			return JsonResponses.generateResponse2(false, "Timetable not found for ID " + timetable_id);
		}

	}
}
