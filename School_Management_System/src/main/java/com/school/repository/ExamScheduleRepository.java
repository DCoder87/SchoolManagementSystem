package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.ExamSchedule;

@Repository
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer> {

	@Query(value = "SELECT * FROM exam_schedule WHERE exam_id = :exam_id AND institute_id = :institute_id", nativeQuery = true)
	Optional<ExamSchedule> findExamScheduleById(@Param("exam_id") int exam_id,@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM exam_schedule WHERE status = 1 AND institute_id = :institute_id", nativeQuery = true)
	List<ExamSchedule> ListOfAllExamSchedules(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM exam_schedule WHERE status = 0 AND institute_id = :institute_id", nativeQuery = true)
	List<ExamSchedule> ListOfdeletedExamSchedules(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE exam_schedule SET status = 0 WHERE exam_id = :exam_id  AND institute_id = :institute_id", nativeQuery = true)
	int deleteByExamId(@Param("exam_id") int exam_id, @Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE exam_schedule SET status = 1 WHERE exam_id = :exam_id  AND institute_id = :institute_id", nativeQuery = true)
	int activeByExamId(@Param("exam_id") int exam_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT exam_id FROM exam_schedule WHERE status = 1 AND exam_id = :exam_id AND institute_id = :institute_id", nativeQuery = true)
	String findExamScheduleByExamId(@Param("exam_id") int exam_id, @Param("institute_id") String institute_id);
}