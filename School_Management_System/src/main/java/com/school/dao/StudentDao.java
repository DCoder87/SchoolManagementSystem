package com.school.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.Parent;
import com.school.beans.Student;
import com.school.repository.InstituteRepository;
import com.school.repository.StudentRepository;
import com.school.request.StudentRequest;
import com.school.service.StudentService;

@Service
public class StudentDao implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	InstituteRepository institutionRepository;


	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();
	
	@Override
	public List<Student> getallStudents(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return studentRepository.ListOfAllStudents(institute_id1);
	}

	@Override
	public Student saveStudent(StudentRequest studentRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);
		
		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			
			Parent parent = new Parent();
			parent.setParent_id(studentRequest.getParent_id());

			Student student = new Student();
			student.setAddress(studentRequest.getAddress());
			student.setDob(studentRequest.getDob());
			student.setEmail(studentRequest.getEmail());
			student.setGender(studentRequest.getGender());
			student.setMobile(studentRequest.getMobile());
			student.setParent(parent);
			student.setPrevious_school(studentRequest.getPrevious_school());
			student.setStudent_name(studentRequest.getStudent_name());
			student.setStatus(i_id);
			student.setInstitute(institute);

			return studentRepository.save(student);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Student updateStudent(StudentRequest studentRequest, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			
			Parent parent = new Parent();
			parent.setParent_id(studentRequest.getParent_id());

			Student student = new Student();
			student.setAddress(studentRequest.getAddress());
			student.setDob(studentRequest.getDob());
			student.setEmail(studentRequest.getEmail());
			student.setGender(studentRequest.getGender());
			student.setMobile(studentRequest.getMobile());
			student.setParent(parent);			
			student.setPrevious_school(studentRequest.getPrevious_school());
			student.setStudent_name(studentRequest.getStudent_name());
			student.setStatus(i_id);
			student.setStudent_id(id);
			student.setInstitute(institute);

			
			return studentRepository.save(student);
		} catch (Exception e) {
			return null;
		}
	}

	

	@Override
	public Optional<Student> findStudentById(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return studentRepository.findStudentById(id, institute_id1);
	}

	@Override
	public int deleteStudentByid(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int deleted = studentRepository.deleteByStudentId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeStudentByid(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int activated = studentRepository.activeByStudentId(id, institute_id1);
		if( activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Student> getdeletedStudents(String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return studentRepository.ListOfdeletedStudents(institute_id1);
	}

	@Override
	public String findStudentByStudentId(String student_id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		String std_id = studentRepository.findStudentByStudentId(student_id, institute_id1);
		return std_id;
	}

}
