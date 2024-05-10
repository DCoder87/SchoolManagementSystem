package com.school.service;

import java.util.List;


import com.school.beans.SubjectMaster;
import com.school.request.SubjectMasterRequest;

public interface SubjectMasterService {

	public SubjectMaster saveSubjectMaster(SubjectMasterRequest subject, String Instittute_id);

	public List<SubjectMaster> getAllSubject(String Institute_id);

	public List<SubjectMaster> getActiveSubejct(String Institute_id);

	public List<SubjectMaster> getDeactiveSubject(String Institute_id);

	public SubjectMaster getById(int subject_id, String Institute_id);
	
	public SubjectMaster updateSubjectMaster(SubjectMaster master,String Institute_id);

}
