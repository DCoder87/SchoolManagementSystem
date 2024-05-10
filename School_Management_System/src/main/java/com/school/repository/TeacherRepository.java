package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query(value = "SELECT * FROM Teacher WHERE teacher_id =:teacher_id AND institute_id =:institute_id", nativeQuery = true)
	Teacher findTeacherById(@Param("teacher_id") int teacher_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Teacher WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<Teacher> ListOfAllTeachers(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Teacher WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<Teacher> ListOfdeletedTeachers(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE Teacher SET status = 0 WHERE teacher_id =:teacher_id  AND institute_id =:institute_id", nativeQuery = true)
	int deleteByTeacherId(@Param("teacher_id") int teacher_id, @Param("institute_id") String institue_id);

	@Modifying
	@Query(value = "UPDATE Teacher SET status = 1 WHERE teacher_id =:teacher_id  AND institute_id =:institute_id", nativeQuery = true)
	int activeByTeacherId(@Param("teacher_id") int teacher_id, @Param("institute_id") String institue_id);

	@Query(value = "SELECT teacher_id FROM Teacher WHERE status=1 AND teacher_id =:teacher_id AND institute_id =:institute_id", nativeQuery = true)
	String findTeacherByTeacherId(@Param("teacher_id") String teacher_id, @Param("institute_id") String institue_id);
}
