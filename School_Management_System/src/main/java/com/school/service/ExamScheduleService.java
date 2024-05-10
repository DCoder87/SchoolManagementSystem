package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.ExamSchedule;
import com.school.request.ExamScheduleRequest;

public interface ExamScheduleService {

	public boolean saveExamSchedule(ExamScheduleRequest examRequest, String institute_id);

	boolean updateExamSchedule(ExamScheduleRequest examRequest, String institute_id, int id);

	List<ExamSchedule> getallExamSchedules(String institute_id);

	Optional<ExamSchedule> findExamScheduleById(int id, String institute_id);

	int deleteExamScheduleByid(int id, String institute_id);

	int activeExamScheduleByid(int id, String institute_id);

	List<ExamSchedule> getdeletedExamSchedules(String institute_id);

	String findExamScheduleByExamScheduleId(int exam_id, String institute_id);

}
