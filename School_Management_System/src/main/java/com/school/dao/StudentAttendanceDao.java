package com.school.dao;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.beans.Institute;
import com.school.beans.Student;
import com.school.beans.StudentAttendance;
import com.school.repository.InstituteRepository;
import com.school.repository.StudentAttendanceRepository;
import com.school.repository.StudentRepository;
import com.school.request.StudentAttendanceRequest;
import com.school.service.StudentAttendanceService;

@Service
public class StudentAttendanceDao implements StudentAttendanceService {

	@Autowired
	StudentAttendanceRepository studentAttendanceRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	InstituteRepository instituteRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<StudentAttendance> getAllAttendances(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return studentAttendanceRepository.ListOfAllStudentAttendance(institute_id1);
	}

	@Override
	public Optional<StudentAttendance> findById(int student_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		Optional<StudentAttendance> user1 = studentAttendanceRepository.findAllById(student_attendance_id,
				institute_id1);
		return user1;
	}

	@Override
	public StudentAttendance createAttendance(StudentAttendanceRequest studentAttendanceRequest, String institute_id) {
		Student student = studentRepository.findById(studentAttendanceRequest.getStudent_id())
				.orElseThrow(() -> new EntityNotFoundException("student not found"));

		String institute_id1 = decrypt.Decryption(institute_id);

		int i_id = Integer.parseInt(institute_id1);

		// Convert java.util.Date to LocalDate
		LocalDate date = studentAttendanceRequest.getDate();
		// LocalDate attendanceDate =
		// date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Check if attendance already exists for the given student ID and date
		boolean attendanceExists = studentAttendanceRepository
				.existsByStudentIdAndDate(studentAttendanceRequest.getStudent_id(), date);

		if (attendanceExists) {
			throw new IllegalArgumentException("Attendance already exists for student ID "
					+ studentAttendanceRequest.getStudent_id() + " on " + date);
		}

		try {
			StudentAttendance existingAttendance = new StudentAttendance();
			existingAttendance.setCheck_in_time(studentAttendanceRequest.getCheck_in_time());
			existingAttendance.setCheck_out_time(studentAttendanceRequest.getCheck_out_time());
			existingAttendance.setDate(studentAttendanceRequest.getDate());
			existingAttendance.setLocation(studentAttendanceRequest.getLocation());
			existingAttendance.setMonth_id(studentAttendanceRequest.getMonth_id()); // Set user entity instead of userId
			existingAttendance.setYear_id(studentAttendanceRequest.getYear_id());
			existingAttendance.setStatus(1);
			existingAttendance.setStudent_id(student);
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			existingAttendance.setInstitute(institute);

			return studentAttendanceRepository.save(existingAttendance);

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public StudentAttendance updateStudentAttendance(StudentAttendanceRequest studentAttendanceRequest,
			String institute_id, int student_attendance_id) {

		Student student = studentRepository.findById(studentAttendanceRequest.getStudent_id())
				.orElseThrow(() -> new EntityNotFoundException("student not found"));

		String institute_id1 = decrypt.Decryption(institute_id);

		int i_id = Integer.parseInt(institute_id1);

		Optional<StudentAttendance> optionalAttendance = studentAttendanceRepository.findById(student_attendance_id);
		if (optionalAttendance.isPresent()) {
			StudentAttendance existingAttendance = optionalAttendance.get();
			existingAttendance.setCheck_in_time(studentAttendanceRequest.getCheck_in_time());
			existingAttendance.setCheck_out_time(studentAttendanceRequest.getCheck_out_time());
			existingAttendance.setDate(studentAttendanceRequest.getDate());
			existingAttendance.setLocation(studentAttendanceRequest.getLocation());
			existingAttendance.setMonth_id(studentAttendanceRequest.getMonth_id()); // Set user entity instead of userId
			existingAttendance.setYear_id(studentAttendanceRequest.getYear_id());
			existingAttendance.setStatus(1);
			existingAttendance.setStudent_id(student);
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			existingAttendance.setInstitute(institute);

			return studentAttendanceRepository.save(existingAttendance);
		} else {
			return null;
		}

	}

	@Override
	public int deleteById(int student_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = studentAttendanceRepository.deleteAllById(student_attendance_id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<StudentAttendance> getDeletedStudent(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return studentAttendanceRepository.deletedStudent(institute_id1);
	}

	@Override
	public int activeStudentById(int student_attendance_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = studentAttendanceRepository.activeStudentById(student_attendance_id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}
}

/*
 * @Transactional
 * 
 * @Override public void markAbsentForMissingRecords(String instituteId,
 * List<Integer> allStudentIds) { Date currentDate = new Date();
 * 
 * Calendar calendar = Calendar.getInstance(); calendar.setTime(currentDate);
 * 
 * int year = calendar.get(Calendar.YEAR); int month =
 * calendar.get(Calendar.MONTH) + 1;
 * 
 * String institute_id1 = decrypt.Decryption(instituteId); //int i_id =
 * Integer.parseInt(institute_id1);
 * 
 * for (int StudentId : allStudentIds) { StudentAttendance b =
 * studentAttendanceRepository.findAttendancesIncludingToday(institute_id1,
 * StudentId, currentDate);
 * 
 * if (b == null) {
 * 
 * StudentAttendance saa =
 * studentAttendanceRepository.findStudentAttendanceById(StudentId,
 * instituteId); //here StudentAttendance sa = new StudentAttendance();
 * 
 * Student student = new Student(); student.setStudent_id(StudentId);
 * 
 * // Institute institute = new Institute(); // institute.setInstitute_id(i_id);
 * 
 * sa.setDate(currentDate);
 * 
 * sa.setCheck_in_time(null); sa.setCheck_out_time(null); sa.setMonth_id(month);
 * sa.setYear_id(year); sa.setLocation(saa.getLocation());
 * sa.setStudent_id(student); // sa.setInstitute(institute);
 * sa.setInstitute(saa.getInstitute()); sa.setStatus(0); StudentAttendance xyz =
 * studentAttendanceRepository.save(sa); System.out.println(xyz);
 * 
 * } else {
 * 
 * }
 * 
 * }
 * 
 * } // iske bad unexpecdly
 * 
 * }
 */
