package com.school.request;

import java.util.Date;

public class StudentRequest {
	
  	 private String student_name;
	  
	 private int parent_id;

	 private Date dob;
	 
	 private String gender;

     private String address;
	 
	 private String previous_school;

	 private String email;

	 private String mobile;

	public String getStudent_name() {
		return student_name;
	}

	public Date getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getPrevious_school() {
		return previous_school;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public int getParent_id() {
		return parent_id;
	}

	@Override
	public String toString() {
		return "StudentRequest [student_name=" + student_name + ", parent_id=" + parent_id + ", dob=" + dob
				+ ", gender=" + gender + ", address=" + address + ", previous_school=" + previous_school + ", email="
				+ email + ", mobile=" + mobile + "]";
	}

	
	 
	 

}
