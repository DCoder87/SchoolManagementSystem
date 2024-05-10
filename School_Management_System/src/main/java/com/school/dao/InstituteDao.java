package com.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.repository.InstituteRepository;
import com.school.service.InstituteService;

@Service
public class InstituteDao implements InstituteService {

	@Autowired
	InstituteRepository instituteRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public Institute saveInstitute(Institute institute) {

		return instituteRepository.save(institute);
	}

	@Override
	public Institute updateInstitute(Institute institute, int institute_id) {

		return instituteRepository.save(institute);
	}

	@Override
	public Institute getInstituteById(String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);

		return instituteRepository.getInstituteByEncryptedInstituteId(institute_id1);
	}

	@Override
	public Institute checkInstituteExistsOrNotByName(String institute_name) {

		return instituteRepository.getInstituteByInstituteName(institute_name);
	}

	@Override
	public Institute checkInstituteExistsOrNotByEmail(String email) {

		return instituteRepository.getInstituteByInstituteEmail(email);
	}

}
