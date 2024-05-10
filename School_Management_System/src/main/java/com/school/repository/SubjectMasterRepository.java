package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.school.beans.SubjectMaster;

public interface SubjectMasterRepository extends JpaRepository<SubjectMaster,Integer> {

	
	@Query(value="SELECT * FROM subject_master  WHERE subject_id = :subject_id AND Institute_id =:Institute_id", nativeQuery = true)
	SubjectMaster findBySubject_id(@Param("subject_id") int subject_id,@Param("Institute_id") int Institute_id);
	
	@Query(value = "SELECT * FROM subject_master WHERE status=1 AND Institute_id =:Institute_id", nativeQuery = true)
	List<SubjectMaster> getAllActiveSubject(@Param("Institute_id") int Institute_id);
	
	@Query(value = "SELECT * FROM subject_master WHERE status=0 AND Institute_id =:Institute_id", nativeQuery = true)
	List<SubjectMaster> getAllDeactiveSubject(@Param("Institute_id") int Institute_id);
	
	@Query(value = "SELECT * FROM subject_master WHERE Institute_id =:Institute_id", nativeQuery = true)
	List<SubjectMaster> getAllSubject(@Param("Institute_id") int Institute_id);
	
	 
    
    
}
