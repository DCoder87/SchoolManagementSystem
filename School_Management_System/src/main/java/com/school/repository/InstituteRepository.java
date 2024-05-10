package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.Institute;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Integer> {

	@Query(value = "SELECT * FROM Institute WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	Institute getInstituteByEncryptedInstituteId(@Param("institute_id") String institute_id);
	
	@Query(value = "SELECT * FROM Institute WHERE status=1 AND Institute_name =:Institute_name", nativeQuery = true)
	Institute getInstituteByInstituteName(@Param("Institute_name") String Institute_name);
	
	@Query(value = "SELECT * FROM Institute WHERE status=1 AND email =:email", nativeQuery = true)
	Institute getInstituteByInstituteEmail(@Param("email") String email);
}
