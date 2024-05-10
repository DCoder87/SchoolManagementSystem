package com.school.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Student {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_count")
	 @SequenceGenerator(name = "student_count", initialValue = 1, allocationSize = 1)
	 @Column(length = 16)
	 private int student_id;
	 
	 @NotNull
	 @ManyToOne(cascade = {CascadeType.REFRESH})
     @JoinColumn(name = "institute_id")
	 private Institute institute ;
	 
	 @OneToOne(cascade = {CascadeType.REFRESH})
	 @JoinColumn(name = "parent_id")
	 private Parent parent;
	 
	 @NotNull
	 @Size(max = 32)
	 @Column(length = 32)
     private String student_name;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 private Date dob;
	 
	 @NotNull
	 @Size(max = 32)
	 @Column(length = 32)
	 private String gender;
	 
	 @Size(max = 100)
	 @Column(length = 100)
     private String address;
	 
	 @NotNull
	 @Size(max = 100)
	 @Column(length = 100)
	 private String previous_school;
	 
	 @Email
	 @Size(max = 50)
	 @Column(name = "email")
	 private String email;
	 
	 @Pattern(regexp = "\\d{1,20}") // assuming the mobile number length should be between 1 and 20
	 @NotNull
	 @Size(max = 32)
	 @Column(name = "mobile")
	 private String mobile;
	 
	 @NotNull
	 @Column(length = 2)
	 private int status;

	public Student() {
		super();
	}

	public Student(int student_id, @NotNull Institute institute, Parent parent,
			@NotNull @Size(max = 32) String student_name, Date dob, @NotNull @Size(max = 32) String gender,
			@Size(max = 100) String address, @NotNull @Size(max = 100) String previous_school,
			@Email @Size(max = 50) String email, @Pattern(regexp = "\\d{1,20}") @NotNull @Size(max = 32) String mobile,
			@NotNull int status) {
		super();
		this.student_id = student_id;
		this.institute = institute;
		this.parent = parent;
		this.student_name = student_name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.previous_school = previous_school;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrevious_school() {
		return previous_school;
	}

	public void setPrevious_school(String previous_school) {
		this.previous_school = previous_school;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", institute=" + institute + ", parent=" + parent
				+ ", student_name=" + student_name + ", dob=" + dob + ", gender=" + gender + ", address=" + address
				+ ", previous_school=" + previous_school + ", email=" + email + ", mobile=" + mobile + ", status="
				+ status + "]";
	}
	 
	 
}
