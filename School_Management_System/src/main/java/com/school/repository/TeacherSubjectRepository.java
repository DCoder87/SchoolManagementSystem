package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.TeacherSubject;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Integer> {

	@Query(value = "SELECT * FROM teacher_subject WHERE institute_id = :institute_id", nativeQuery = true)
	List<TeacherSubject> findAllByInstituteId(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM teacher_subject WHERE mapped_id = :mapped_id AND institute_id = :institute_id", nativeQuery = true)
	Optional<TeacherSubject> findByMappedIdAndInstituteId(@Param("mapped_id") int mapped_id,@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE teacher_subject SET status = 0 WHERE mapped_id = :mapped_id AND institute_id = :institute_id", nativeQuery = true)
	int deleteByMappedIdAndInstituteId(@Param("mapped_id") int mapped_id, @Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE teacher_subject SET status = 1 WHERE mapped_id = :mapped_id AND institute_id = :institute_id", nativeQuery = true)
	int activateByMappedIdAndInstituteId(@Param("mapped_id") int mapped_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM teacher_subject WHERE status = 0 AND institute_id = :institute_id", nativeQuery = true)
	List<TeacherSubject> findDeletedByInstituteId(@Param("institute_id") String institute_id);

	@Query(value = "SELECT subject_name FROM teacher_subject WHERE standard_id = :standard_id AND institute_id = :institute_id", nativeQuery = true)
	String findSubjectByStandardIdAndInstituteId(@Param("standard_id") int standard_id,@Param("institute_id") String institute_id);
}
