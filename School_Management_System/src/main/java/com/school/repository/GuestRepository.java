package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

    @Query(value = "SELECT * FROM Guest WHERE guest_id =:guest_id AND institute_id =:institute_id", nativeQuery = true)
    Optional<Guest> findGuestById(@Param("guest_id") int guest_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Guest WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
    List<Guest> listOfAllGuests(@Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Guest WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
    List<Guest> listOfDeletedGuests(@Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE Guest SET status = 0 WHERE guest_id =:guest_id  AND institute_id =:institute_id", nativeQuery = true)
    int deleteByGuestId(@Param("guest_id") int guest_id, @Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE Guest SET status = 1 WHERE guest_id =:guest_id  AND institute_id =:institute_id", nativeQuery = true)
    int activateByGuestId(@Param("guest_id") int guest_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT guest_id FROM Guest WHERE status=1 AND guest_id =:guest_id AND institute_id =:institute_id", nativeQuery = true)
    String findGuestByGuestId(@Param("guest_id") String guest_id, @Param("institute_id") String institute_id);
}
