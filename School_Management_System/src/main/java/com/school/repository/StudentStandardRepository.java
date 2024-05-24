package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.StudentStandard;

public interface StudentStandardRepository extends JpaRepository<StudentStandard, Integer> {

	@Query(value = "SELECT * FROM student_standard WHERE student_standard_id =:student_standard_id AND institute_id =:institute_id", nativeQuery = true)
	Optional<StudentStandard> findStudentStandardById(@Param("student_standard_id") int student_standard_id,
			@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM student_standard WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<StudentStandard> ListOfAllStudentStandards(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM student_standard WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<StudentStandard> ListOfdeletedStudentStandards(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE student_standard SET status = 0 WHERE student_standard_id =:student_standard_id  AND institute_id =:institute_id", nativeQuery = true)
	int deleteByStandardId(@Param("student_standard_id") int student_standard_id,
			@Param("institute_id") String institue_id);

	@Modifying
	@Query(value = "UPDATE student_standard SET status = 1 WHERE student_standard_id =:student_standard_id  AND institute_id =:institute_id", nativeQuery = true)
	int activeByStandardId(@Param("student_standard_id") int student_standard_id,
			@Param("institute_id") String institue_id);

	@Query(value = "SELECT id FROM student_standard WHERE status=1 AND student_standard_id =:student_standard_id AND institute_id =:institute_id", nativeQuery = true)
	String findStudentStandardByStandardId(@Param("student_standard_id") String student_standard_id,
			@Param("institute_id") String institue_id);

	/*
	 * @Query(value =
	 * "SELECT * FROM student_standard WHERE student_standard_id =:student_standard_id AND institute_id =:institute_id AND student_id=:student_id"
	 * , nativeQuery = true) StudentStandard
	 * findStudentStandardById2(@Param("student_standard_id") int
	 * student_standard_id,
	 * 
	 * @Param("institute_id") String institute_id, @Param("student_id") int
	 * student_id);
	 */
	
	@Query(value = "SELECT * FROM student_standard WHERE standard_id =:standard_id AND institute_id =:institute_id AND student_id=:student_id", nativeQuery = true)
	StudentStandard findStudentStandardById2(@Param("standard_id") int standard_id,
			@Param("institute_id") String institute_id, @Param("student_id") int student_id);
}
