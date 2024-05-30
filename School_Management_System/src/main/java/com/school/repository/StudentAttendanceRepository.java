package com.school.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.school.beans.StudentAttendance;
import com.school.request.StudentAttendanceRequest;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {

	StudentAttendance save(StudentAttendanceRequest studentAttendanceRequest);

	@Query(value = "SELECT * FROM student_attendance  WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<StudentAttendance> ListOfAllStudentAttendance(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM student_attendance WHERE student_attendance_id =:student_attendance_id AND institute_id =:institute_id", nativeQuery = true)
	Optional<StudentAttendance> findAllById(@Param("student_attendance_id") int student_attendance_id,
			String institute_id);

	@Modifying
	@Query(value = "UPDATE student_attendance SET status = 0 WHERE student_attendance_id =:student_attendance_id AND institute_id =:institute_id", nativeQuery = true)
	int deleteAllById(@Param("student_attendance_id") int student_attendance_id,
			@Param("institute_id") String institute_id);

	@Query("SELECT CASE WHEN COUNT(sa) > 0 THEN true ELSE false END FROM StudentAttendance sa WHERE sa.student_id.id = ?1 AND sa.date = ?2")
	boolean existsByStudentIdAndDate(int student_id, LocalDate attendanceDate);

	@Query(value = "SELECT * FROM student_attendance WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<StudentAttendance> deletedStudent(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE student_attendance SET status = 1 WHERE student_attendance_id =:student_attendance_id AND institute_id =:institute_id", nativeQuery = true)
	int activeStudentById(@Param("student_attendance_id") int student_attendance_id,
			@Param("institute_id") String institute_id);

	// @Query(value = "SELECT * FROM student_attendance WHERE institute_id
	// =:institute_id AND student_id =:student_id AND DATE_FORMAT(date, '%Y-%m-%d')
	// =:date", nativeQuery = true)
	// Boolean findAttendancesIncludingToday(@Param("institute_id") String
	// institute_id,
	// @Param("student_id") int student_id, @Param("date") Date date);

//	@Query(value = "SELECT * FROM student_attendance WHERE institute_id =:institute_id AND student_id =:student_id AND DATE_FORMAT(date, '%Y-%m-%d') =:date", nativeQuery = true)
	// Boolean findAttendancesIncludingToday(@Param("institute_id") String
	// institute_id,
	// @Param("student_id") int student_id, @Param("date") Date date);

//	@Query(value = "SELECT * FROM student_attendance WHERE institute_id =:institute_id AND student_id =:student_id AND DATE_FORMAT(date, '%Y-%m-%d') =:date", nativeQuery = true)
//	StudentAttendance findAttendancesIncludingToday(@Param("institute_id") String institute_id,
//			@Param("student_id") int student_id, @Param("date") Date date);


	@Query(value = "SELECT * FROM student_attendance WHERE student_id =:student_id AND institute_id =:institute_id", nativeQuery = true)
	StudentAttendance findStudentAttendanceById(@Param("student_id") int student_id,
			@Param("institute_id") String institute_id);
	
	LocalDate currentDate = LocalDate.now();
	
	@Query(value = "SELECT * FROM student_attendance WHERE institute_id =:institute_id AND date=:currentDate", nativeQuery = true)
	List<StudentAttendance> listOfPresentStudentAttendance(@Param("institute_id") String institute_id,
			@Param("currentDate") LocalDate currentDate);

}