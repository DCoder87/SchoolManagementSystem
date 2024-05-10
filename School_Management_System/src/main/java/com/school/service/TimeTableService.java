package com.school.service;

import java.util.List;
import java.util.Optional;
import com.school.beans.TimeTable;
import com.school.request.TimeTableRequest;

public interface TimeTableService {

	public List<TimeTable> getAllTimetablesById(String institute_id);

	public TimeTable createTimetable(TimeTableRequest timetable, String institute_id);

	public Optional<TimeTable> getTimetableById(int timetable_id, String institute_id);

	public TimeTable updateTimetable(int timetable_id, String institute_id, TimeTableRequest timetable);

	public int deleteTimetable(int timetable_id, String institute_id);

	public List<TimeTable> getDeletedTimetables(String institute_id);

	public int activateTimetableById(int timetable_id, String institute_id);

}
