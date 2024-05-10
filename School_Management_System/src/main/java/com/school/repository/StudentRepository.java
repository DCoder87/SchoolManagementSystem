package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "SELECT * FROM Student WHERE student_id =:student_id AND institute_id =:institute_id", nativeQuery = true)
	Optional<Student> findStudentById(@Param("student_id") int student_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Student WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<Student> ListOfAllStudents(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Student WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<Student> ListOfdeletedStudents(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE Student SET status = 0 WHERE student_id =:student_id  AND institute_id =:institute_id", nativeQuery = true)
	int deleteByStudentId(@Param("student_id") int student_id, @Param("institute_id") String institue_id);

	@Modifying
	@Query(value = "UPDATE Student SET status = 1 WHERE student_id =:student_id  AND institute_id =:institute_id", nativeQuery = true)
	int activeByStudentId(@Param("student_id") int student_id, @Param("institute_id") String institue_id);

	@Query(value = "SELECT student_id FROM Student WHERE status=1 AND student_id =:student_id AND institute_id =:institute_id", nativeQuery = true)
	String findStudentByStudentId(@Param("student_id") String student_id, @Param("institute_id") String institue_id);
}
