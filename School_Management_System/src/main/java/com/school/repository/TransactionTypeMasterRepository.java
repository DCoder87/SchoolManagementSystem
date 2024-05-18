package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.TransactionTypeMaster;
@Repository
public interface TransactionTypeMasterRepository extends JpaRepository<TransactionTypeMaster, Integer> {

	@Query(value = "SELECT * FROM transaction_type_master  WHERE  type_id = :type_id AND Institute_id =:Institute_id", nativeQuery = true)
	TransactionTypeMaster findByType_id(@Param("type_id") int type_id, @Param("Institute_id") String Institute_id);
    
	@Query(value = "SELECT * FROM transaction_type_master WHERE status=1 AND Institute_id =:Institute_id", nativeQuery = true)
	List<TransactionTypeMaster> getAllActiveTransactions(@Param("Institute_id") String Institute_id);

	@Query(value = "SELECT * FROM transaction_type_master WHERE status=0 AND Institute_id =:Institute_id", nativeQuery = true)
	List<TransactionTypeMaster> getAllDeactiveTransactions(@Param("Institute_id") String Institute_id);

	@Query(value = "SELECT * FROM transaction_type_master WHERE Institute_id =:Institute_id", nativeQuery = true)
	List<TransactionTypeMaster> getAllTransactions(@Param("Institute_id") String Institute_id);

}
