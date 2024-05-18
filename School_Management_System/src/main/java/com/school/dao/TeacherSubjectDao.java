package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.SubjectMaster;
import com.school.beans.Teacher;
import com.school.beans.TeacherSubject;
import com.school.repository.InstituteRepository;
import com.school.repository.TeacherSubjectRepository;
import com.school.request.TeacherSubjectRequest;
import com.school.service.TeacherSubjectService;

@Service
public class TeacherSubjectDao implements TeacherSubjectService {

	@Autowired
	TeacherSubjectRepository teacherSubjectRepository;

	@Autowired
	InstituteRepository institutionRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<TeacherSubject> getAllTeacherSubjects(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.findAllByInstituteId(institute_id1);
	}

	@Override
	public TeacherSubject saveTeacherSubject(TeacherSubjectRequest teacherSubjectRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Teacher teacher = new Teacher();
			teacher.setTeacher_id(teacherSubjectRequest.getTeacher_id());

			SubjectMaster subject = new SubjectMaster();
			subject.setSubject_id(teacherSubjectRequest.getSubject_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(teacherSubjectRequest.getStandard_id());

			TeacherSubject teacherSubject = new TeacherSubject();

			teacherSubject.setInstitute(institute);
			teacherSubject.setStandardMaster(standard);
			teacherSubject.setSubjectMaster(subject);
			teacherSubject.setTeacher(teacher);
			teacherSubject.setStatus(1);

			return teacherSubjectRepository.save(teacherSubject);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public TeacherSubject updateTeacherSubject(TeacherSubjectRequest teacherSubjectRequest, String institute_id,
			int mapped_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Teacher teacher = new Teacher();
			teacher.setTeacher_id(teacherSubjectRequest.getTeacher_id());

			SubjectMaster subject = new SubjectMaster();
			subject.setSubject_id(teacherSubjectRequest.getSubject_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(teacherSubjectRequest.getStandard_id());

			TeacherSubject teacherSubject = new TeacherSubject();

			teacherSubject.setInstitute(institute);
			teacherSubject.setStandardMaster(standard);
			teacherSubject.setSubjectMaster(subject);
			teacherSubject.setTeacher(teacher);
			teacherSubject.setStatus(1);
			teacherSubject.setMapped_id(mapped_id);

			return teacherSubjectRepository.save(teacherSubject);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Optional<TeacherSubject> findTeacherSubjectByMappedId(int mapped_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.findByMappedIdAndInstituteId(mapped_id, institute_id1);
	}

	@Override
	public int deleteTeacherSubjectByMappedId(int mapped_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.deleteByMappedIdAndInstituteId(mapped_id, institute_id1);
	}

	@Override
	public int activateTeacherSubjectByMappedId(int mapped_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.activateByMappedIdAndInstituteId(mapped_id, institute_id1);
	}

	@Override
	public List<TeacherSubject> getDeletedTeacherSubjects(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.findDeletedByInstituteId(institute_id1);
	}

	@Override
	public String findTeacherSubjectByStandardId(int standard_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherSubjectRepository.findSubjectByStandardIdAndInstituteId(standard_id, institute_id1);
	}
}