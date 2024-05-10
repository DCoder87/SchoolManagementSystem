package com.school.service;

import java.util.List;


import com.school.beans.StandardMaster;
import com.school.request.StandardMasterRequest;

public interface StandardMasterService {

	public StandardMaster savestandardMaster(StandardMasterRequest standard, String Institute_id);

	public List<StandardMaster> getAllStandard(String Institute_id);

	public List<StandardMaster> getActiveStandard(String Institute_id);

	public List<StandardMaster> getDeactiveStandard(String Institute_id);

	public StandardMaster getById(int standard_id, String Institute_id);

}
