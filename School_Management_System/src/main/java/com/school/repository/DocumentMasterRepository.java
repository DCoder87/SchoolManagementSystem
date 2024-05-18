package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.school.beans.DocumentMaster;

public interface DocumentMasterRepository extends JpaRepository<DocumentMaster,Integer> {

	
	 @Query(value="SELECT * FROM document_master  WHERE documents_master_id = :documents_master_id AND Institute_id =:Institute_id", nativeQuery = true)
	DocumentMaster findByDocuments_master_id(@Param("documents_master_id") int documents_master_id,@Param("Institute_id") String Institute_id);
	
	@Query(value = "SELECT * FROM document_master WHERE status=1 AND Institute_id =:Institute_id", nativeQuery = true)
	List<DocumentMaster> getAllActiveDocuments(@Param("Institute_id") String Institute_id);
	
	@Query(value = "SELECT * FROM document_master WHERE status=0 AND Institute_id =:Institute_id", nativeQuery = true)
	List<DocumentMaster> getAllDeactiveDocument(@Param("Institute_id") String Institute_id);
	
	@Query(value = "SELECT * FROM document_master WHERE Institute_id =:Institute_id", nativeQuery = true)
	List<DocumentMaster> getAllDocuments(@Param("Institute_id") String Institute_id); 
}
