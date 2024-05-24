package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.StudentStandard;
import com.school.request.GradeRequest;
import com.school.request.StudentStandardRequest;

public interface StudentStandardService {

	public boolean saveStudentStandard(StudentStandardRequest studentStandardRequest, String institute_id);

	boolean updateStudentStandard(StudentStandardRequest studentStandardRequest, String institute_id, int id);

	boolean updateBulkStudentStandard(List<GradeRequest> gradeRequests, String institute_id);

	List<StudentStandard> getallStudentStandards(String institute_id);

	Optional<StudentStandard> findStudentStandardById(int id, String institute_id);

	int deleteStudentStandardByid(int id, String institute_id);

	int activeStudentStandardByid(int id, String institute_id);

	List<StudentStandard> getdeletedStudentStandards(String institute_id);

	String findStudentStandardByStudentStandardId(String standard_id, String institute_id);
}
