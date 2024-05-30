package com.school.scheduler;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.beans.Institute;
import com.school.beans.Student;
import com.school.beans.StudentAttendance;
import com.school.dao.EncryptionAndDecryption;
import com.school.repository.InstituteRepository;
import com.school.repository.StudentAttendanceRepository;
import com.school.repository.StudentRepository;

@Component
@Transactional
public class AttendanceScheduler {

	@Autowired
	StudentAttendanceRepository studentAttendanceRepository;

	StudentRepository studentRepository;

	InstituteRepository instituteRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	public AttendanceScheduler(StudentAttendanceRepository studentAttendanceRepository,
			StudentRepository studentRepository, InstituteRepository instituteRepository) {
		this.studentAttendanceRepository = studentAttendanceRepository;
		this.studentRepository = studentRepository;
		this.instituteRepository = instituteRepository;
	}

	@Scheduled(fixedRate = 60000)
	public void scheduleMarkAbsent() {

		List<Institute> allInstitutes = instituteRepository.findAll();

		for (Institute institute : allInstitutes) {

			String institute_id1 = Integer.toString(institute.getInstitute_id());

			LocalDate currentDate = LocalDate.now();

			int year = currentDate.getYear();

			int month = currentDate.getMonthValue();

			List<Student> allStudents = studentRepository.ListOfEntireStudents(institute_id1);

			List<StudentAttendance> studentAttendancePresenty = studentAttendanceRepository
					.listOfPresentStudentAttendance(institute_id1, currentDate);

			for (Student student : allStudents) {
				int flag = 1;
				for (StudentAttendance sa : studentAttendancePresenty) {

					if (student.getStudent_id() == (sa.getStudent_id()).getStudent_id()) {
						flag = 0;
					}
				}
				if (flag == 1) { // for absent

					StudentAttendance sa1 = new StudentAttendance();

					sa1.setDate(currentDate);
					sa1.setLocation(null);
					sa1.setCheck_in_time(null);
					sa1.setCheck_out_time(null);
					sa1.setMonth_id(month);
					sa1.setYear_id(year);
					sa1.setStudent_id(student);
					sa1.setInstitute(institute);
					sa1.setStatus(0);
					System.out.println("StudentRequest" + sa1);
					StudentAttendance xyz = studentAttendanceRepository.save(sa1);
					System.out.println(xyz);

				}
			}
		}
	}

}
