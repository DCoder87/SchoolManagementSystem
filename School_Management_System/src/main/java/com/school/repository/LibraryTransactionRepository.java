package com.school.repository;

import com.school.beans.LibraryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibraryTransactionRepository extends JpaRepository<LibraryTransaction, Integer> {
    @Query(value = "SELECT * FROM library_transaction WHERE library_transaction_id =:library_transaction_id AND institute_id =:institute_id", nativeQuery = true)
    Optional<LibraryTransaction> findLibraryTransactionById(@Param("library_transaction_id") int library_transaction_id, @Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM library_transaction WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
    List<LibraryTransaction> ListOfAllLibraryTransaction(@Param("institute_id") String institute_id);

    @Query(value = "SELECT * FROM library_transaction WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
    List<LibraryTransaction> ListOfdeletedLibraryTransaction(@Param("institute_id") String institute_id);

    @Modifying
    @Query(value = "UPDATE library_transaction SET status = 0 WHERE library_transaction_id =:library_transaction_id  AND institute_id =:institute_id", nativeQuery = true)
    int deleteByLibraryTransactionId(@Param("library_transaction_id") int library_transaction_id, @Param("institute_id") String institue_id);

    @Modifying
    @Query(value = "UPDATE library_transaction SET status = 1 WHERE library_transaction_id =:library_transaction_id  AND institute_id =:institute_id", nativeQuery = true)
    int activeByLibraryTransactionId(@Param("library_transaction_id") int library_transaction_id, @Param("institute_id") String institue_id);

    @Query(value = "SELECT library_transaction_id FROM library_transaction WHERE status=1 AND library_transaction_id =:library_transaction_id AND institute_id =:institute_id", nativeQuery = true)
    String findLibraryTransactionByLibraryTransactionId(@Param("library_transaction_id") String library_transaction_id, @Param("institute_id") String institue_id);
}
