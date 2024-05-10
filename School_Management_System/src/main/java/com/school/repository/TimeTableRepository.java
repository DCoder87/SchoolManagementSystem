package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable , Integer>{

	@Query(value = "SELECT * FROM time_table WHERE status=1 AND  institute_id =:institute_id", nativeQuery = true)
	List<TimeTable> ListOfAllStudentAttendance(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM time_table WHERE time_table_id = :time_table_id AND institute_id = :institute_id", nativeQuery = true)
	Optional<TimeTable> findAllById(@Param("time_table_id") int time_table_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM time_table WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<TimeTable> deletedTimeTable(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE time_table SET status = 1 WHERE time_table_id =:time_table_id AND institute_id =:institute_id", nativeQuery = true)
	int activateTimetableById(@Param("time_table_id") int time_table_id, @Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE time_table SET status = 0 WHERE time_table_id =:time_table_id AND institute_id =:institute_id", nativeQuery = true)
	int deleteAllById(@Param("time_table_id") int time_table_id, @Param("institute_id") String institute_id);


}
