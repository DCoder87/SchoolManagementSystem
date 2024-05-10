package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Fees;

public interface FeesRepository extends JpaRepository<Fees, Integer> {

    @Query(value = "SELECT * FROM Fees WHERE fees_id =:fees_id AND institute_id =:institute_id", nativeQuery = true)
    Optional<Fees> findFeesById(@Param("fees_id") int fees_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Fees WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
    List<Fees> ListOfAllFees(@Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Fees WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
    List<Fees> ListOfdeletedFees(@Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE Fees SET status = 0 WHERE fees_id =:fees_id  AND institute_id =:institute_id", nativeQuery = true)
    int deleteByFeesId(@Param("fees_id") int fees_id, @Param("institute_id") String institue_id);

    @Modifying
    @Query(value = "UPDATE Fees SET status = 1 WHERE fees_id =:fees_id  AND institute_id =:institute_id", nativeQuery = true)
    int activeByFeesId(@Param("fees_id") int fees_id, @Param("institute_id") String institue_id);

    @Query(value = "SELECT id FROM Fees WHERE status=1 AND fees_id =:fees_id AND institute_id =:institute_id", nativeQuery = true)
    String findFeesByFeesId(@Param("fees_id") String fees_id, @Param("institute_id") String institue_id);
}
