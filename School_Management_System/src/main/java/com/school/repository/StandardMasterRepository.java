package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.StandardMaster;

public interface StandardMasterRepository extends JpaRepository<StandardMaster, Integer> {

	@Query(value = "SELECT * FROM standard_master  WHERE standard_id = :standard_id AND Institute_id =:Institute_id", nativeQuery = true)
	StandardMaster findByStandard_id(@Param("standard_id") int standard_id, @Param("Institute_id") String Institute_id);

	@Query(value = "SELECT * FROM standard_masters WHERE status=1 AND Institute_id =:Institute_id", nativeQuery = true)
	List<StandardMaster> getAllActiveStandard(@Param("Institute_id") String Institute_id);

	@Query(value = "SELECT * FROM standard_master WHERE status=0 AND Institute_id =:Institute_id", nativeQuery = true)
	List<StandardMaster> getAllDeactiveStandard(@Param("Institute_id") String Institute_id);

	@Query(value = "SELECT * FROM standard_master WHERE Institute_id =:Institute_id", nativeQuery = true)
	List<StandardMaster> getAllStandard(@Param("Institute_id") String Institute_id);

}
