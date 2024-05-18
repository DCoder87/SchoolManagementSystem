package com.school.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.Teacher;
import com.school.repository.StandardMasterRepository;
import com.school.repository.TeacherRepository;
import com.school.request.StandardMasterRequest;
import com.school.service.StandardMasterService;

@Service
public class StandardMasterDao implements StandardMasterService {

	@Autowired
	StandardMasterRepository standardmasterRepository;

	@Autowired

	TeacherRepository teacherRepository;

	LocalDateTime today = LocalDateTime.now();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public StandardMaster savestandardMaster(StandardMasterRequest standard, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);

		Institute institute = new Institute();
		institute.setInstitute_id(i_id);

		Teacher t1 = new Teacher();
		t1.setTeacher_id(standard.getTeacher_id());
		StandardMaster standard1 = new StandardMaster();
		standard1.setStandard_name(standard.getStandard_name());
		standard1.setTeacher(t1);
		standard1.setInstitute(institute);
		System.out.println(standard1);
		return standardmasterRepository.save(standard1);

	}

	@Override
	public List<StandardMaster> getAllStandard(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return standardmasterRepository.getAllStandard(institute_id1);
	}

	@Override
	public List<StandardMaster> getActiveStandard(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return standardmasterRepository.getAllActiveStandard(institute_id1);
	}

	@Override
	public List<StandardMaster> getDeactiveStandard(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return standardmasterRepository.getAllDeactiveStandard(institute_id1);
	}

	@Override
	public StandardMaster getById(int standard_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return standardmasterRepository.findByStandard_id(standard_id, institute_id1);
	}

}
