package com.school.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.beans.Guest;
import com.school.commom.responses.JsonResponses;
import com.school.request.GuestRequest;
import com.school.service.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	GuestService guestService;

	@PostMapping("/{institute_id}")
	public Map<String, Object> addGuest(@PathVariable String institute_id, @RequestBody GuestRequest guestRequest) {
		Guest savedGuest = guestService.saveGuest(guestRequest, institute_id);

		if (savedGuest != null) {
			return JsonResponses.generateResponse1(true, savedGuest, "Guest Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, guestRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{guest_id}")
	public Map<String, Object> updateGuestById(@PathVariable String institute_id, @PathVariable int guest_id,
			@RequestBody GuestRequest guestRequest) {

		Guest updatedGuest = guestService.updateGuest(guestRequest, institute_id, guest_id);

		if (updatedGuest != null) {
			return JsonResponses.generateResponse1(true, updatedGuest, "Guest Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, guest_id, "Guest Not Found for ID " + guest_id);
		}
	}

	@GetMapping("/{institute_id}")
	public Map<String, Object> getAllGuests(@PathVariable String institute_id) {
		List<Guest> allGuests = guestService.getAllGuests(institute_id);

		if (allGuests.isEmpty()) {
			return JsonResponses.generateResponse1(false, allGuests, "No Guests Found");
		} else {
			return JsonResponses.generateResponse1(true, allGuests, "Guests Found Successfully");
		}
	}
	@Transactional
	@DeleteMapping("/delete/{institute_id}/{guest_id}")
	public Map<String, Object> deleteGuestById(@PathVariable String institute_id, @PathVariable int guest_id)
			throws Exception {

		int deleted = guestService.deleteGuestById(guest_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Guest Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Guest Found for ID " + guest_id);
		}
	}

	@GetMapping("/edit/{institute_id}/{guest_id}")
	public Map<String, Object> findGuestById(@PathVariable String institute_id, @PathVariable int guest_id) {
		Optional<Guest> guest = guestService.findGuestById(guest_id, institute_id);

		if (guest.isPresent()) {
			return JsonResponses.generateResponse1(true, guest, "Guest Found Successfully");
		} else {
			return JsonResponses.generateResponse1(false, guest_id, "Guest Not Found for ID " + guest_id);
		}
	}

}
