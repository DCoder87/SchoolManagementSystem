package com.school.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.school.beans.StudentAttendance;
import com.school.request.StudentAttendanceRequest;

@Service
public interface StudentAttendanceService {

	List<StudentAttendance> getAllAttendances(String institute_id);

	StudentAttendance createAttendance(StudentAttendanceRequest studentAttendanceRequest, String institute_id);

	Optional<StudentAttendance> findById(int student_attendance_id, String institute_id);

	StudentAttendance updateStudentAttendance(StudentAttendanceRequest studentAttendanceRequest, String institute_id,
			int student_attendance_id);

	int deleteById(int student_attendance_id, String institute_id);

	List<StudentAttendance> getDeletedStudent(String institute_id);

	int activeStudentById(int student_attendance_id, String institute_id);

}
