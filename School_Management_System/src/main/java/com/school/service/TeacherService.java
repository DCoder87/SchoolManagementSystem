package com.school.service;

import java.util.List;

import com.school.beans.Teacher;

public interface TeacherService {

	Teacher saveTeacher(Teacher teacher);

	Teacher updateTeacher(Teacher teacher);

	List<Teacher> getallTeachers(String institute_id);

	Teacher findTeacherById(int id, String institute_id);

	int deleteTeacherByid(int id, String institute_id);

	int activeTeacherByid(int id, String institute_id);

	List<Teacher> getdeletedTeachers(String institute_id);

	String findTeacherByTeacherId(String teacher_id, String institute_id);
}
