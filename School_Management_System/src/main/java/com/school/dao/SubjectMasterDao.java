package com.school.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.SubjectMaster;
import com.school.repository.SubjectMasterRepository;
import com.school.request.SubjectMasterRequest;
import com.school.service.SubjectMasterService;

@Service
public class SubjectMasterDao implements SubjectMasterService {

	@Autowired
	SubjectMasterRepository subjectmasterRepository;

	LocalDateTime today = LocalDateTime.now();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public SubjectMaster saveSubjectMaster(SubjectMasterRequest subject, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		Institute institute = new Institute();
		institute.setInstitute_id(i_id);
		

			
			SubjectMaster subject1 = new SubjectMaster();
			
			subject1.setSubject_name(subject.getSubject_name());
			subject1.setStatus(1);
			subject1.setInstitute(institute);
			return subjectmasterRepository.save(subject1);

		
	}

	@Override
	public List<SubjectMaster> getAllSubject(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return subjectmasterRepository.getAllSubject(institute_id1);
	}

	@Override
	public List<SubjectMaster> getActiveSubejct(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return subjectmasterRepository.getAllActiveSubject(institute_id1);
	}

	@Override
	public List<SubjectMaster> getDeactiveSubject(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return subjectmasterRepository.getAllDeactiveSubject(institute_id1);
	}

	@Override
	public SubjectMaster getById(int subject_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return subjectmasterRepository.findBySubject_id(subject_id, institute_id1);
	}

	@Override
	public SubjectMaster updateSubjectMaster(SubjectMaster master, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		SubjectMaster subject=new SubjectMaster();
		subject.setSubject_name(master.getSubject_name());
		Institute institute=new Institute();
		institute.setInstitute_id(i_id);
		subject.setInstitute(institute);
		return subjectmasterRepository.save(subject);
	}

	

}
