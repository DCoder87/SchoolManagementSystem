package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.Student;
import com.school.beans.StudentStandard;
import com.school.repository.InstituteRepository;
import com.school.repository.StudentStandardRepository;
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

            studentStandardRepository.save(studentStandard);
            return true;
        } catch (Exception e) {
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

}
