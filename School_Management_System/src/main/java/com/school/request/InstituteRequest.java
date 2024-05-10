package com.school.request;

import java.time.LocalDateTime;

public class InstituteRequest {

	private String institute_name;

	private String institute_slogen;

	private String institute_logo;

	private String address;

	private int established_year;

	private String contact_number;

	private String email;

	private String website;

	private String accregated_status;

	private LocalDateTime created_at;

	public InstituteRequest() {
		super();
	}

	public String getInstitute_name() {
		return institute_name;
	}

	public String getInstitute_slogen() {
		return institute_slogen;
	}

	public String getInstitute_logo() {
		return institute_logo;
	}

	public String getAddress() {
		return address;
	}

	public int getEstablished_year() {
		return established_year;
	}

	public String getContact_number() {
		return contact_number;
	}

	public String getEmail() {
		return email;
	}

	public String getWebsite() {
		return website;
	}

	public String getAccregated_status() {
		return accregated_status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

}
