package com.school.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.Student;
import com.school.beans.StudentStandard;
import com.school.repository.InstituteRepository;
import com.school.repository.StudentStandardRepository;
import com.school.request.GradeRequest;
import com.school.request.StudentStandardRequest;
import com.school.service.StudentStandardService;

@Service
public class StudentStandardDao implements StudentStandardService {

	@Autowired
	StudentStandardRepository studentStandardRepository;

	@Autowired
	InstituteRepository institutionRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<StudentStandard> getallStudentStandards(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return studentStandardRepository.ListOfAllStudentStandards(institute_id1);
	}

	@Override
	public boolean saveStudentStandard(StudentStandardRequest studentStandardRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Student student = new Student();
			student.setStudent_id(studentStandardRequest.getStudent_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(studentStandardRequest.getStandard_id());

			StudentStandard studentStandard = new StudentStandard();
			studentStandard.setAcademic_end_year(studentStandardRequest.getAcademic_end_year());
			studentStandard.setAcademic_start_year(studentStandardRequest.getAcademic_start_year());
			studentStandard.setAdmission_date(studentStandardRequest.getAdmission_date());
			studentStandard.setGrade(studentStandardRequest.getGrade());
			studentStandard.setStudent(student);
			studentStandard.setStandardmaster(standard);
			studentStandard.setStatus(1);
			studentStandard.setInstitute(institute);
			System.out.println(studentStandardRequest);
			studentStandardRepository.save(studentStandard);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateStudentStandard(StudentStandardRequest studentStandardRequest, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Student student = new Student();
			student.setStudent_id(studentStandardRequest.getStudent_id());

			StandardMaster standard = new StandardMaster();
			standard.setStandard_id(studentStandardRequest.getStandard_id());

			StudentStandard studentStandard = new StudentStandard();
			studentStandard.setAcademic_end_year(studentStandardRequest.getAcademic_end_year());
			studentStandard.setAcademic_start_year(studentStandardRequest.getAcademic_start_year());
			studentStandard.setAdmission_date(studentStandardRequest.getAdmission_date());
			studentStandard.setGrade(studentStandardRequest.getGrade());
			studentStandard.setStudent(student);
			studentStandard.setStandardmaster(standard);
			studentStandard.setStatus(1);
			studentStandard.setStudent_standard_id(id);
			studentStandard.setInstitute(institute);

			studentStandardRepository.save(studentStandard);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<StudentStandard> findStudentStandardById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);

		return studentStandardRepository.findStudentStandardById(id, institute_id1);
	}

	@Override
	public int deleteStudentStandardByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = studentStandardRepository.deleteByStandardId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeStudentStandardByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = studentStandardRepository.activeByStandardId(id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<StudentStandard> getdeletedStudentStandards(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return studentStandardRepository.ListOfdeletedStudentStandards(institute_id1);
	}

	@Override
	public String findStudentStandardByStudentStandardId(String standard_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		String s_id = studentStandardRepository.findStudentStandardByStandardId(standard_id, institute_id1);
		return s_id;
	}

	@Override
	@Transactional
	public boolean updateBulkStudentStandard(List<GradeRequest> gradeRequests, String institute_id) {
		try {
			String institute_id1 = decrypt.Decryption(institute_id);
			boolean success = true;

			for (GradeRequest ssr : gradeRequests) {
				StudentStandard studStandard = studentStandardRepository.findStudentStandardById2(ssr.getStandard_id(),
						institute_id1, ssr.getStudent_id());
				if (studStandard != null) {

					double grade = ssr.getGrade();

					if (grade > 35.0) {

						int start_year = studStandard.getAcademic_start_year();
						int end_year = studStandard.getAcademic_end_year();
						int stud_stan_id = studStandard.getStudent_standard_id();

						StandardMaster sm = studStandard.getStandardmaster();
						int s_id = sm.getStandard_id();
						StandardMaster newStandardMaster = new StandardMaster();
						newStandardMaster.setStandard_id(s_id + 1);

						studStandard.setGrade(grade);
						studStandard.setAcademic_start_year(start_year + 1);
						studStandard.setAcademic_end_year(end_year + 1);
						studStandard.setAdmission_date(null);
						studStandard.setStudent_standard_id(stud_stan_id);
						studStandard.setStandardmaster(newStandardMaster);
						studStandard.setStatus(1);
						studentStandardRepository.save(studStandard);
					} else {
						studStandard.setGrade(grade);
						studStandard.setStatus(0);
						studentStandardRepository.save(studStandard);
					}
				} else {
					success = false;
				}
			}
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
