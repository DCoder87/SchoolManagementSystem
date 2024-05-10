package com.school.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Teacher;
import com.school.repository.InstituteRepository;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherService;

@Service
public class TeacherDao implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    InstituteRepository institutionRepository;

    EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

    @Override
    public List<Teacher> getallTeachers(String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return teacherRepository.ListOfAllTeachers(institute_id1);
    }

    
	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
    
	@Override
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
   

    @Override
    public Teacher findTeacherById(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return teacherRepository.findTeacherById(id, institute_id1);
    }

    @Override
    public int deleteTeacherByid(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int deleted = teacherRepository.deleteByTeacherId(id, institute_id1);
        if (deleted == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int activeTeacherByid(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int activated = teacherRepository.activeByTeacherId(id, institute_id1);
        if (activated == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<Teacher> getdeletedTeachers(String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return teacherRepository.ListOfdeletedTeachers(institute_id1);
    }

    @Override
    public String findTeacherByTeacherId(String teacher_id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        String tchr_id = teacherRepository.findTeacherByTeacherId(teacher_id, institute_id1);
        return tchr_id;
    }


}
