package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Fees;

public interface FeesRepository extends JpaRepository<Fees, Integer> {

    @Query(value = "SELECT * FROM fees WHERE fee_id =:fee_id AND institute_id =:institute_id", nativeQuery = true)
    Optional<Fees> findFeesById(@Param("fee_id") int fee_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM fees WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
    List<Fees> ListOfAllFees(@Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM fees WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
    List<Fees> ListOfdeletedFees(@Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE fees SET status = 0 WHERE fee_id =:fee_id  AND institute_id =:institute_id", nativeQuery = true)
    int deleteByFeesId(@Param("fee_id") int fee_id, @Param("institute_id") String institue_id);

    @Modifying
    @Query(value = "UPDATE fees SET status = 1 WHERE fee_id =:fee_id  AND institute_id =:institute_id", nativeQuery = true)
    int activeByFeesId(@Param("fee_id") int fee_id, @Param("institute_id") String institue_id);

    @Query(value = "SELECT id FROM fees WHERE status=1 AND fee_id =:fee_id AND institute_id =:institute_id", nativeQuery = true)
    String findFeesByFeesId(@Param("fee_id") String fee_id, @Param("institute_id") String institue_id);
}
