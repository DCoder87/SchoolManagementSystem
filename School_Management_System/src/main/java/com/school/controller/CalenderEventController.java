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

import com.school.beans.CalenderEvent;
import com.school.commom.responses.JsonResponses;
import com.school.request.CalenderEventRequest;
import com.school.service.CalenderEventService;

@RestController
@RequestMapping("/calendarevent")
public class CalenderEventController {

	@Autowired
	CalenderEventService calendarEventService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getCalendarEvents(@PathVariable String institute_id) {

		List<CalenderEvent> allCalendarEvents = calendarEventService.getAllCalendarEvents(institute_id);

		if (allCalendarEvents.isEmpty()) {
			return JsonResponses.generateResponse1(true, allCalendarEvents,
					"Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(false, allCalendarEvents,
					"Calendar Events Details Got Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addCalendarEvent(@PathVariable String institute_id,
			@RequestBody CalenderEventRequest calenderEventRequest) {

		System.out.println(calenderEventRequest.getEvent_description());

		CalenderEvent savedCalendarEvent = calendarEventService.saveCalendarEvent(calenderEventRequest, institute_id);

		if (savedCalendarEvent != null) {
			return JsonResponses.generateResponse1(true, savedCalendarEvent, "Calendar Event Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, calenderEventRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{event_id}")
	public Map<String, Object> updateCalendarEventById(@PathVariable String institute_id, @PathVariable int event_id,
			@RequestBody CalenderEventRequest calenderEventRequest) {

		CalenderEvent updatedCalendarEvent = calendarEventService.updateCalendarEvent(calenderEventRequest,
				institute_id, event_id);

		if (updatedCalendarEvent != null) {
			return JsonResponses.generateResponse1(true, calenderEventRequest, "Calendar Event Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, event_id,
					"Calendar Event Not Found for this ID: " + event_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{event_id}")
	public Map<String, Object> deleteCalendarEventById(@PathVariable String institute_id, @PathVariable int event_id)
			throws Exception {

		int deleted = calendarEventService.deleteCalendarEventById(event_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Calendar Event Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Calendar Event Found For this ID " + event_id);
		}
	}

	@GetMapping("/edit/{institute_id}/{event_id}")
	public Map<String, Object> findCalendarEventById(@PathVariable String institute_id, @PathVariable int event_id) {

		Optional<CalenderEvent> calendarEvent = calendarEventService.findCalendarEventById(event_id, institute_id);

		if (calendarEvent.isPresent()) {
			return JsonResponses.generateResponse1(true, calendarEvent, "Calendar Event Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, event_id, "Calendar Event Not Found for Id " + event_id);
		}
	}
}
