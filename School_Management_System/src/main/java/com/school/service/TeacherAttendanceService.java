package com.school.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.school.beans.TeacherAttendance;
import com.school.request.TeacherAttendanceRequest;

@Service
public interface TeacherAttendanceService {

	List<TeacherAttendance> getAllAttendances(String institute_id);

	TeacherAttendance createAttendance(TeacherAttendanceRequest teacherAttendanceRequest, String institute_id);

	Optional<TeacherAttendance> findByTeacherAttendanceId(int teacher_attendance_id, String institute_id);

	TeacherAttendance updateTeacherAttendance(TeacherAttendanceRequest teacherAttendanceRequest, String institute_id,
			int teacher_attendance_id);

	int deleteById(int teacher_attendance_id, String institute_id);

	int activeTeacherById(int teacher_attendance_id, String institute_id);

	List<TeacherAttendance> getDeletedTeacher(String institute_id);

}
