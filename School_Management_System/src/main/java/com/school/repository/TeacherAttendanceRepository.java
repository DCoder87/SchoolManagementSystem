package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.TeacherAttendance;
import com.school.request.TeacherAttendanceRequest;


public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance , Integer>{

	TeacherAttendance save(TeacherAttendanceRequest teacherAttendanceRequest);

	@Query(value = "SELECT * FROM teacher_attendance WHERE status=1 AND  institute_id =:institute_id", nativeQuery = true)
	List<TeacherAttendance> ListOfAllTeacherAttendance(@Param("institute_id")String institute_id);

	@Query(value = "SELECT * FROM teacher_attendance WHERE teacher_attendance_id = :teacher_attendance_id AND institute_id = :institute_id", nativeQuery = true)
	Optional<TeacherAttendance> findAllById(@Param("teacher_attendance_id") int teacher_attendance_id, @Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE teacher_attendance SET status = 0 WHERE teacher_attendance_id =:teacher_attendance_id AND institute_id =:institute_id", nativeQuery = true)
	int deleteAllById(@Param("teacher_attendance_id")int teacher_attendance_id, @Param("institute_id")  String institute_id);

	@Modifying
	@Query(value = "UPDATE teacher_attendance SET status = 1 WHERE teacher_attendance_id =:teacher_attendance_id AND institute_id =:institute_id", nativeQuery = true)
	int activeTeacherById(@Param("teacher_attendance_id") int teacher_attendance_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM teacher_attendance WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<TeacherAttendance> deletedTeacher(@Param("institute_id") String institute_id);



}
