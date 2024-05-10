package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.Parent;
import com.school.request.ParentRequest;

public interface ParentService {
	
	Parent saveParent(ParentRequest parentRequest, String institute_id);
	
	Parent updateParent(ParentRequest parentRequest, String institute_id, int id);

	List<Parent> getallParents(String institute_id);

	Optional<Parent> findParentById(int id, String institute_id);

	int deleteParentByid(int id, String institute_id);

	int activeParentByid(int id, String institute_id);

	List<Parent> getdeletedParents(String institute_id);

	String findParentByParentId(String parent_id, String institute_id);
}
