package com.school.request;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;


public class GuestRequest {

	private int referred_by;

	private String guest_name;

	private String guest_address;

	private String mobile;

	private String email;

	private String alternate_mobile;

	private String qualification;

	private String lecture_subject;

	private String lecture_description;

	private Date lecture_date;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time lecture_time;

	private String venue;

	public int getReferred_by() {
		return referred_by;
	}

	public String getGuest_name() {
		return guest_name;
	}

	public String getGuest_address() {
		return guest_address;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getAlternate_mobile() {
		return alternate_mobile;
	}

	public String getQualification() {
		return qualification;
	}

	public String getLecture_subject() {
		return lecture_subject;
	}

	public String getLecture_description() {
		return lecture_description;
	}

	public Date getLecture_date() {
		return lecture_date;
	}

	public Time getLecture_time() {
		return lecture_time;
	}

	public String getVenue() {
		return venue;
	}

	@Override
	public String toString() {
		return "GuestRequest [referred_by=" + referred_by + ", guest_name=" + guest_name + ", guest_address="
				+ guest_address + ", mobile=" + mobile + ", email=" + email + ", alternate_mobile=" + alternate_mobile
				+ ", qualification=" + qualification + ", lecture_subject=" + lecture_subject + ", lecture_description="
				+ lecture_description + ", lecture_date=" + lecture_date + ", lecture_time=" + lecture_time + ", venue="
				+ venue + "]";
	}



}
