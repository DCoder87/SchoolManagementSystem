package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.CalenderEvent;

@Repository
public interface CalenderEventRepository extends JpaRepository<CalenderEvent, Integer> {

	@Query(value = "SELECT * FROM Calender_Event WHERE event_id = :event_id AND institute_id = :institute_id", nativeQuery = true)
	Optional<CalenderEvent> findEventById(@Param("event_id") int event_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Calender_Event WHERE institute_id = :institute_id AND status = 1", nativeQuery = true)
	List<CalenderEvent> ListOfAllEvents(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Calender_Event WHERE institute_id = :institute_id AND status = 0", nativeQuery = true)
	List<CalenderEvent> ListOfdeletedEvents(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE Calender_Event SET status = 0 WHERE event_id = :event_id AND institute_id = :institute_id", nativeQuery = true)
	int deleteEventById(@Param("event_id") int event_id, @Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE Calender_Event SET status = 1 WHERE event_id = :event_id AND institute_id = :institute_id", nativeQuery = true)
	int activateEventById(@Param("event_id") int event_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT id FROM Calender_Event WHERE status = 1 AND event_id = :event_id AND institute_id = :institute_id", nativeQuery = true)
	String findEventByEventId(@Param("event_id") int event_id, @Param("institute_id") String institute_id);
}
