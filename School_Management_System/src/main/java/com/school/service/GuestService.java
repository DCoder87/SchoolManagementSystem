package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.Guest;
import com.school.request.GuestRequest;

public interface GuestService {

    Guest saveGuest(GuestRequest guestRequest, String institute_id);

    Guest updateGuest(GuestRequest guestRequest, String institute_id, int id);

    List<Guest> getAllGuests(String institute_id);

    Optional<Guest> findGuestById(int id, String institute_id);

    int deleteGuestById(int id, String institute_id);

    int activeGuestById(int id, String institute_id);

    List<Guest> getDeletedGuests(String institute_id);

    String findGuestByGuestId(String guest_id, String institute_id);
}

