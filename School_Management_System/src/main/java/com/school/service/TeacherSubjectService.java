package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.TeacherSubject;
import com.school.request.TeacherSubjectRequest;

public interface TeacherSubjectService {

	TeacherSubject saveTeacherSubject(TeacherSubjectRequest teacherSubjectRequest, String institute_id);

	TeacherSubject updateTeacherSubject(TeacherSubjectRequest teacherSubjectRequest, String institute_id, int mapped_id);

	List<TeacherSubject> getAllTeacherSubjects(String institute_id);

	Optional<TeacherSubject> findTeacherSubjectByMappedId(int mapped_id, String institute_id);

	int deleteTeacherSubjectByMappedId(int mapped_id, String institute_id);

	int activateTeacherSubjectByMappedId(int mapped_id, String institute_id);

	List<TeacherSubject> getDeletedTeacherSubjects(String institute_id);

	String findTeacherSubjectByStandardId(int standard_id, String institute_id);
}
