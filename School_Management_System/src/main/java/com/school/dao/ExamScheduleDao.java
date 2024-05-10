package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.ExamSchedule;
import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.SubjectMaster;
import com.school.repository.ExamScheduleRepository;
import com.school.repository.InstituteRepository;
import com.school.request.ExamScheduleRequest;
import com.school.service.ExamScheduleService;

@Service
public class ExamScheduleDao implements ExamScheduleService {

	@Autowired
	ExamScheduleRepository examScheduleRepository;

	@Autowired
	InstituteRepository instituteRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public boolean saveExamSchedule(ExamScheduleRequest examScheduleRequest, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			SubjectMaster subject = new SubjectMaster();
			subject.setSubject_id(examScheduleRequest.getSubject_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(examScheduleRequest.getStudent_standard_id());

			ExamSchedule examSchedule = new ExamSchedule();
			examSchedule.setExam_name(examScheduleRequest.getExam_name());
			examSchedule.setExam_date(examScheduleRequest.getExam_date());
			examSchedule.setExam_start_time(examScheduleRequest.getExam_start_time());
			examSchedule.setExam_end_time(examScheduleRequest.getExam_end_time());
			examSchedule.setExam_center(examScheduleRequest.getExam_center());
			examSchedule.setSubjectMaster(subject);
			examSchedule.setStandardMaster(standard);
			examSchedule.setStatus(i_id);
			examSchedule.setInstitute(institute);

			examScheduleRepository.save(examSchedule);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateExamSchedule(ExamScheduleRequest examScheduleRequest, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			SubjectMaster subject = new SubjectMaster();
			subject.setSubject_id(examScheduleRequest.getSubject_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(examScheduleRequest.getStudent_standard_id());

			ExamSchedule examSchedule = new ExamSchedule();
			examSchedule.setExam_id(id);
			examSchedule.setExam_name(examScheduleRequest.getExam_name());
			examSchedule.setExam_date(examScheduleRequest.getExam_date());
			examSchedule.setExam_start_time(examScheduleRequest.getExam_start_time());
			examSchedule.setExam_end_time(examScheduleRequest.getExam_end_time());
			examSchedule.setExam_center(examScheduleRequest.getExam_center());
			examSchedule.setSubjectMaster(subject);
			examSchedule.setStandardMaster(standard);
			examSchedule.setStatus(i_id);
			examSchedule.setInstitute(institute);

			examScheduleRepository.save(examSchedule);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ExamSchedule> getallExamSchedules(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return examScheduleRepository.ListOfAllExamSchedules(institute_id1);
	}

	@Override
	public Optional<ExamSchedule> findExamScheduleById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return examScheduleRepository.findExamScheduleById(id, institute_id1);

	}

	@Override
	public int deleteExamScheduleByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = examScheduleRepository.deleteByExamId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeExamScheduleByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = examScheduleRepository.activeByExamId(id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<ExamSchedule> getdeletedExamSchedules(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return examScheduleRepository.ListOfdeletedExamSchedules(institute_id1);
	}

	@Override
	public String findExamScheduleByExamScheduleId(int exam_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		String e_id = examScheduleRepository.findExamScheduleByExamId(exam_id, institute_id1);
		return e_id;

	}

}