package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.Student;
import com.school.request.StudentRequest;

public interface StudentService {
	
	Student saveStudent(StudentRequest studentRequest, String institute_id);
	
	Student updateStudent(StudentRequest studentRequest, String institute_id, int id);

	List<Student> getallStudents(String institute_id);

	Optional<Student> findStudentById(int id, String institute_id);

	int deleteStudentByid(int id, String institute_id);

	int activeStudentByid(int id, String institute_id);

	List<Student> getdeletedStudents(String institute_id);

	String findStudentByStudentId(String student_id, String institute_id);
}
