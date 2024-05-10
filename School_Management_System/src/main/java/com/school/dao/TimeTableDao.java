package com.school.dao;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.SubjectMaster;
import com.school.beans.Teacher;
import com.school.beans.TimeTable;
import com.school.repository.StandardMasterRepository;
import com.school.repository.SubjectMasterRepository;
import com.school.repository.TeacherRepository;
import com.school.repository.TimeTableRepository;
import com.school.request.TimeTableRequest;
import com.school.service.TimeTableService;

@Service
public class TimeTableDao implements TimeTableService {

	@Autowired
	TimeTableRepository timeTableRepository;

	@Autowired
	StandardMasterRepository standardMasterRepository;

	@Autowired
	SubjectMasterRepository subjectMasterRepository;

	@Autowired
	TeacherRepository teacherRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<TimeTable> getAllTimetablesById(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return timeTableRepository.ListOfAllStudentAttendance(institute_id1);
	}

	@Override
	public TimeTable createTimetable(TimeTableRequest timetableRequest, String institute_id) {
		try {
			// Fetch relevant entities (assuming these repositories exist)
			StandardMaster standard = standardMasterRepository.findById(timetableRequest.getStandard_id())
					.orElseThrow(() -> new EntityNotFoundException("Standard not found"));

			SubjectMaster subject = subjectMasterRepository.findById(timetableRequest.getSubject_id())
					.orElseThrow(() -> new EntityNotFoundException("Subject not found"));

			Teacher teacher = teacherRepository.findById(timetableRequest.getTeacher_id())
					.orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

			String institute_id1 = decrypt.Decryption(institute_id);

			int i_id = Integer.parseInt(institute_id1);

			TimeTable timetable = new TimeTable();
			timetable.setStandardmaster(standard);
			timetable.setSubjectmaster(subject);
			timetable.setTeacher(teacher);
			timetable.setDay(timetableRequest.getDay());
			timetable.setStartTime(timetableRequest.getStart_time());
			timetable.setEndTime(timetableRequest.getEnd_time());
			timetable.setStatus(1);

			LocalTime startTime = timetableRequest.getStart_time().toLocalTime();
			LocalTime endTime = timetableRequest.getEnd_time().toLocalTime();
			long hours;
			long minutes;

			if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
				endTime = endTime.plusHours(12);
			}

			if (endTime.isBefore(startTime)) {
				endTime = endTime.plusHours(24);
			}

			hours = Duration.between(startTime, endTime).toHours();
			minutes = Duration.between(startTime, endTime).toMinutes() % 60;

			Time totalDuration = Time.valueOf(String.format("%02d:%02d:00", hours, minutes));
			timetable.setTotalDuration(totalDuration);

			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			timetable.setInstitute(institute);

			return timeTableRepository.save(timetable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<TimeTable> getTimetableById(int timetable_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		Optional<TimeTable> user1 = timeTableRepository.findAllById(timetable_id, institute_id1);
		return user1;
	}

	@Override
	public TimeTable updateTimetable(int timetable_id, String institute_id, TimeTableRequest timetableRequest) {

		StandardMaster standard = standardMasterRepository.findById(timetableRequest.getStandard_id())
				.orElseThrow(() -> new EntityNotFoundException("Standard not found"));
		SubjectMaster subject = subjectMasterRepository.findById(timetableRequest.getSubject_id())
				.orElseThrow(() -> new EntityNotFoundException("Subject not found"));
		Teacher teacher = teacherRepository.findById(timetableRequest.getTeacher_id())
				.orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

		String institute_id1 = decrypt.Decryption(institute_id);

		int i_id = Integer.parseInt(institute_id1);

		Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(timetable_id);
		if (optionalTimeTable.isPresent()) {
			TimeTable timetable = optionalTimeTable.get();
			timetable.setStandardmaster(standard);
			timetable.setSubjectmaster(subject);
			timetable.setTeacher(teacher);
			timetable.setDay(timetableRequest.getDay());
			timetable.setStartTime(timetableRequest.getStart_time());
			timetable.setEndTime(timetableRequest.getEnd_time());
			timetable.setStatus(1);

			LocalTime startTime = timetableRequest.getStart_time().toLocalTime();
			LocalTime endTime = timetableRequest.getEnd_time().toLocalTime();
			Duration duration = Duration.between(startTime, endTime);
			long hours = duration.toHours();
			long minutes = duration.minusHours(hours).toMinutes();
			Time totalDuration = Time.valueOf(String.format("%02d:%02d:00", hours, minutes));
			timetable.setTotalDuration(totalDuration);

			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			timetable.setInstitute(institute);
			timetable.setTimetabe_id(timetable_id);

			return timeTableRepository.save(timetable);
		}
		return null;

	}

	@Override
	public int deleteTimetable(int timetable_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);

		int deleted = timeTableRepository.deleteAllById(timetable_id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<TimeTable> getDeletedTimetables(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);

		return timeTableRepository.deletedTimeTable(institute_id1);
	}

	@Override
	public int activateTimetableById(int timetable_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);

		int activated = timeTableRepository.activateTimetableById(timetable_id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
