package com.school.request;

import java.util.Date;

public class TeacherRequest {

private String teacher_name;

	private String address;

	private String contact_number;

	private String alternate_number;

	private String email;

	private Date joining_date;

	private String gender;

	private String qualification;
	
	private int experience;

	private String government_id;

	public String getTeacher_name() {
		return teacher_name;
	}

	public String getAddress() {
		return address;
	}

	public String getContact_number() {
		return contact_number;
	}

	public String getAlternate_number() {
		return alternate_number;
	}

	public String getEmail() {
		return email;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public String getGender() {
		return gender;
	}

	public String getQualification() {
		return qualification;
	}

	public int getExperience() {
		return experience;
	}


	public String getGovernment_id() {
		return government_id;
	}

	@Override
	public String toString() {
		return "TeacherRequest [teacher_name=" + teacher_name + ", address=" + address + ", contact_number="
				+ contact_number + ", alternate_number=" + alternate_number + ", email=" + email + ", joining_date="
				+ joining_date + ", gender=" + gender + ", qualification=" + qualification + ", experience="
				+ experience + ", government_id=" + government_id + "]";
	}

	
		
	
}
