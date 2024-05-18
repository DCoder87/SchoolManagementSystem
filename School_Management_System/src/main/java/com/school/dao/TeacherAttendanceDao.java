package com.school.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.beans.Institute;
import com.school.beans.Teacher;
import com.school.beans.TeacherAttendance;
import com.school.repository.InstituteRepository;
import com.school.repository.TeacherAttendanceRepository;
import com.school.repository.TeacherRepository;
import com.school.request.TeacherAttendanceRequest;
import com.school.service.TeacherAttendanceService;

@Service
public class TeacherAttendanceDao implements TeacherAttendanceService {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	TeacherAttendanceRepository teacherAttendanceRepository;

	@Autowired
	InstituteRepository instituteRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<TeacherAttendance> getAllAttendances(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherAttendanceRepository.ListOfAllTeacherAttendance(institute_id1);
	}

	@Override
	public TeacherAttendance createAttendance(TeacherAttendanceRequest teacherAttendanceRequest, String institute_id) {
		Teacher teacher = teacherRepository.findById(teacherAttendanceRequest.getTeacher_id())
				.orElseThrow(() -> new EntityNotFoundException("student not found"));

		String institute_id1 = decrypt.Decryption(institute_id);

		int i_id = Integer.parseInt(institute_id1);

		TeacherAttendance existingAttendance = new TeacherAttendance();
		existingAttendance.setCheck_in_time(teacherAttendanceRequest.getCheck_in_time());
		existingAttendance.setCheck_out_time(teacherAttendanceRequest.getCheck_out_time());
		existingAttendance.setDate(teacherAttendanceRequest.getDate());
		existingAttendance.setLocation(teacherAttendanceRequest.getLocation());
		existingAttendance.setMonth_id(teacherAttendanceRequest.getMonth_id()); // Set user entity instead of userId
		existingAttendance.setYear_id(teacherAttendanceRequest.getYear_id());
		existingAttendance.setStatus(1);
		existingAttendance.setTeacher_id(teacher);
		Institute institute = new Institute();
		institute.setInstitute_id(1);
		existingAttendance.setInstitute(institute);
		return teacherAttendanceRepository.save(existingAttendance);
	} // Or throw an exception

	@Override
	public Optional<TeacherAttendance> findByTeacherAttendanceId(int teacher_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherAttendanceRepository.findAllById(teacher_attendance_id, institute_id1);
	}

	@Override
	public TeacherAttendance updateTeacherAttendance(TeacherAttendanceRequest teacherAttendanceRequest,
			String institute_id, int teacher_attendance_id) {
		Teacher teacher = teacherRepository.findById(teacherAttendanceRequest.getTeacher_id())
				.orElseThrow(() -> new EntityNotFoundException("student not found"));

		String institute_id1 = decrypt.Decryption(institute_id);

		int i_id = Integer.parseInt(institute_id1);

		Optional<TeacherAttendance> optionalAttendance = teacherAttendanceRepository.findById(teacher_attendance_id);
		if (optionalAttendance.isPresent()) {
			TeacherAttendance existingAttendance = optionalAttendance.get();
			existingAttendance.setCheck_in_time(teacherAttendanceRequest.getCheck_in_time());
			existingAttendance.setCheck_out_time(teacherAttendanceRequest.getCheck_out_time());
			existingAttendance.setDate(teacherAttendanceRequest.getDate());
			existingAttendance.setLocation(teacherAttendanceRequest.getLocation());
			existingAttendance.setMonth_id(teacherAttendanceRequest.getMonth_id()); // Set user entity instead of userId
			existingAttendance.setYear_id(teacherAttendanceRequest.getYear_id());
			existingAttendance.setStatus(1);
			existingAttendance.setTeacher_id(teacher);
			Institute institute = new Institute();
			institute.setInstitute_id(1);
			existingAttendance.setInstitute(institute);
			return teacherAttendanceRepository.save(existingAttendance);
		} else {
			return null;
		}
	}

	@Override
	public int deleteById(int teacher_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = teacherAttendanceRepository.deleteAllById(teacher_attendance_id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeTeacherById(int teacher_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = teacherAttendanceRepository.activeTeacherById(teacher_attendance_id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<TeacherAttendance> getDeletedTeacher(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return teacherAttendanceRepository.deletedTeacher(institute_id1);
	}

}
