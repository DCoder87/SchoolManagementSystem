package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.beans.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

	@Query(value = "SELECT * FROM Parent WHERE parent_id =:parent_id AND institute_id =:institute_id", nativeQuery = true)
	Optional<Parent> findParentById(@Param("parent_id") int parent_id, @Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Parent WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<Parent> ListOfAllParents(@Param("institute_id") String institute_id);

	@Query(value = "SELECT * FROM Parent WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<Parent> ListOfdeletedParents(@Param("institute_id") String institute_id);

	@Modifying
	@Query(value = "UPDATE Parent SET status = 0 WHERE parent_id =:parent_id  AND institute_id =:institute_id", nativeQuery = true)
	int deleteByParentId(@Param("parent_id") int parent_id, @Param("institute_id") String institue_id);

	@Modifying
	@Query(value = "UPDATE Parent SET status = 1 WHERE parent_id =:parent_id  AND institute_id =:institute_id", nativeQuery = true)
	int activeByParentId(@Param("parent_id") int parent_id, @Param("institute_id") String institue_id);

	@Query(value = "SELECT parent_id FROM Parent WHERE status=1 AND parent_id =:parent_id AND institute_id =:institute_id", nativeQuery = true)
	String findParentByParentId(@Param("parent_id") String parent_id, @Param("institute_id") String institue_id);
}
