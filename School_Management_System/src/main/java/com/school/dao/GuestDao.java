package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Guest;
import com.school.beans.Institute;
import com.school.beans.Teacher;
import com.school.repository.GuestRepository;
import com.school.repository.InstituteRepository;
import com.school.request.GuestRequest;
import com.school.service.GuestService;

@Service
public class GuestDao implements GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    InstituteRepository instituteRepository;

    EncryptionAndDecryption decrypt = new EncryptionAndDecryption();
    
	@Override
	public List<Guest> getAllGuests(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return guestRepository.listOfAllGuests(institute_id1);
	}
    

    @Override
    public Guest saveGuest(GuestRequest guestRequest, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int i_id = Integer.parseInt(institute_id1);

        try {
            Institute institute = new Institute();
            institute.setInstitute_id(i_id);
            
            Teacher teacher = new Teacher();
            teacher.setTeacher_id(guestRequest.getReferred_by());

            Guest guest = new Guest();
            guest.setAlternate_mobile(guestRequest.getGuest_address());
            guest.setAlternate_mobile(guestRequest.getAlternate_mobile());
            guest.setEmail(guestRequest.getEmail());
            guest.setGuest_name(guestRequest.getGuest_name());
            guest.setGuest_address(guestRequest.getGuest_address());
            guest.setLecture_date(guestRequest.getLecture_date());
            guest.setLecture_description(guestRequest.getLecture_description());
            guest.setLecture_subject(guestRequest.getLecture_subject());
            guest.setLecture_time(guestRequest.getLecture_time());
            guest.setMobile(guestRequest.getMobile());
            guest.setQualification(guestRequest.getQualification());
            guest.setTeacher(teacher);
            guest.setStatus(1);
            guest.setVenue(guestRequest.getVenue());
            guest.setInstitute(institute);

            return guestRepository.save(guest);
        } catch (Exception e) {
            return null;
        }
    }

	@Override
	public Guest updateGuest(GuestRequest guestRequest, String institute_id, int id) {
		 String institute_id1 = decrypt.Decryption(institute_id);
	        int i_id = Integer.parseInt(institute_id1);

	        try {
	            Institute institute = new Institute();
	            institute.setInstitute_id(i_id);
	            
	            Teacher teacher = new Teacher();
	            teacher.setTeacher_id(guestRequest.getReferred_by());

	            Guest guest = new Guest();
	            guest.setAlternate_mobile(guestRequest.getGuest_address());
	            guest.setAlternate_mobile(guestRequest.getAlternate_mobile());
	            guest.setEmail(guestRequest.getEmail());
	            guest.setGuest_name(guestRequest.getGuest_name());
	            guest.setGuest_address(guestRequest.getGuest_address());
	            guest.setLecture_date(guestRequest.getLecture_date());
	            guest.setLecture_description(guestRequest.getLecture_description());
	            guest.setLecture_subject(guestRequest.getLecture_subject());
	            guest.setLecture_time(guestRequest.getLecture_time());
	            guest.setMobile(guestRequest.getMobile());
	            guest.setQualification(guestRequest.getQualification());
	            guest.setTeacher(teacher);
	            guest.setStatus(1);
	            guest.setVenue(guestRequest.getVenue());
	            guest.setInstitute(institute);
	            guest.setGuest_id(id);

	            
	            return guestRepository.save(guest);
	        } catch (Exception e) {
	            return null;
	        }
	}



	@Override
	public Optional<Guest> findGuestById(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return guestRepository.findGuestById(id, institute_id1);
	}

	@Override
	public int deleteGuestById(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int deleted = guestRepository.deleteByGuestId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeGuestById(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int activated = guestRepository.activateByGuestId(id, institute_id1);
		if( activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Guest> getDeletedGuests(String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return guestRepository.listOfDeletedGuests(institute_id1);
	}

	@Override
	public String findGuestByGuestId(String guest_id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		String prnt_id = guestRepository.findGuestByGuestId(guest_id, institute_id1);
		return prnt_id;
	}


}
