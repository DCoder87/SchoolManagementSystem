package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.CalenderEvent;
import com.school.request.CalenderEventRequest;

public interface CalenderEventService {

	List<CalenderEvent> getAllCalendarEvents(String institute_id);

	boolean saveCalendarEvent(CalenderEventRequest calendarEventRequest, String institute_id);

	boolean updateCalendarEvent(CalenderEventRequest calendarEventRequest, String institute_id, int id);

	Optional<CalenderEvent> findCalendarEventById(int id, String institute_id);

	int deleteCalendarEventById(int id, String institute_id);

	int activateCalendarEventById(int id, String institute_id);

	List<CalenderEvent> getDeletedCalendarEvents(String institute_id);

	String findCalendarEventByEventId(int event_id, String institute_id);
}
