package com.school.service;

import com.school.beans.Institute;

public interface InstituteService {

	Institute saveInstitute(Institute institute);

	Institute updateInstitute(Institute institute, int institute_id);

	Institute getInstituteById(String institute_id);

	Institute checkInstituteExistsOrNotByName(String institute_name);

	Institute checkInstituteExistsOrNotByEmail(String email);
}
