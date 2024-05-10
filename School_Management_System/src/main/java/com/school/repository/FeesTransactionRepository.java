package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.FeesTransaction;

public interface FeesTransactionRepository extends JpaRepository<FeesTransaction, Integer> {

    @Query(value = "SELECT * FROM Fees_Transaction WHERE fee_id =:fee_id AND institute_id =:institute_id", nativeQuery = true)
    Optional<FeesTransaction> findFeesTransactionById(@Param("fee_id") int fee_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Fees_Transaction WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
    List<FeesTransaction> ListOfAllFeesTransactions(@Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM Fees_Transaction WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
    List<FeesTransaction> ListOfdeletedFeesTransactions(@Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE Fees_Transaction SET status = 0 WHERE fee_id =:fee_id  AND institute_id =:institute_id", nativeQuery = true)
    int deleteByFeeId(@Param("fee_id") int fee_id, @Param("institute_id") String institue_id);

    @Modifying
    @Query(value = "UPDATE Fees_Transaction SET status = 1 WHERE fee_id =:fee_id  AND institute_id =:institute_id", nativeQuery = true)
    int activeByFeeId(@Param("fee_id") int fee_id, @Param("institute_id") String institue_id);

    @Query(value = "UPDATE Fees_Transaction FROM Fees_Transaction WHERE status=:1 AND transaction_id=:transaction_id  AND institute_id =:institute_id", nativeQuery = true)
    String getFeesTransactionsByFeesTransactionId(@Param("transaction_id") String transaction_id, @Param("institute_id") String institue_id);
}
