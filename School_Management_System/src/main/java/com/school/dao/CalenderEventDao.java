package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.CalenderEvent;
import com.school.beans.Institute;
import com.school.repository.CalenderEventRepository;
import com.school.repository.InstituteRepository;
import com.school.request.CalenderEventRequest;
import com.school.service.CalenderEventService;

@Service
public class CalenderEventDao implements CalenderEventService {

	@Autowired
	CalenderEventRepository calendarEventRepository;

	@Autowired
	InstituteRepository institutionRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<CalenderEvent> getAllCalendarEvents(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return calendarEventRepository.ListOfAllEvents(institute_id1);
	}

	@Override
	public boolean saveCalendarEvent(CalenderEventRequest calendarEventRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			CalenderEvent calendarEvent = new CalenderEvent();
			calendarEvent.setEvent_name(calendarEventRequest.getEvent_name());
			calendarEvent.setEvent_description(calendarEventRequest.getEvent_description());
			calendarEvent.setEvent_type(calendarEventRequest.getEvent_type());
			calendarEvent.setStart_date(calendarEventRequest.getStart_date());
			calendarEvent.setEnd_date(calendarEventRequest.getEnd_date());
			calendarEvent.setStatus(i_id);
			calendarEvent.setInstitute(institute);

			calendarEventRepository.save(calendarEvent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateCalendarEvent(CalenderEventRequest calendarEventRequest, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			CalenderEvent calendarEvent = new CalenderEvent();
			calendarEvent.setEvent_id(id);
			calendarEvent.setEvent_name(calendarEventRequest.getEvent_name());
			calendarEvent.setEvent_description(calendarEventRequest.getEvent_description());
			calendarEvent.setEvent_type(calendarEventRequest.getEvent_type());
			calendarEvent.setStart_date(calendarEventRequest.getStart_date());
			calendarEvent.setEnd_date(calendarEventRequest.getEnd_date());
			calendarEvent.setStatus(i_id);
			calendarEvent.setInstitute(institute);

			calendarEventRepository.save(calendarEvent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<CalenderEvent> findCalendarEventById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return calendarEventRepository.findEventById(id, institute_id1);
	}

	@Override
	public int deleteCalendarEventById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = calendarEventRepository.deleteEventById(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activateCalendarEventById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = calendarEventRepository.activateEventById(id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<CalenderEvent> getDeletedCalendarEvents(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return calendarEventRepository.ListOfdeletedEvents(institute_id1);
	}

	@Override
	public String findCalendarEventByEventId(int event_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		String e_id = calendarEventRepository.findEventByEventId(event_id, institute_id1);
		return e_id;
	}

}