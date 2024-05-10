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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Teacher {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_count")
	@SequenceGenerator(name = "teacher_count", initialValue = 1, allocationSize = 10)
	@Column(length = 16)
	private int teacher_id;
	
	
	@NotNull
	@BatchSize(size = 100)
	@Column(length = 100)
	private String teacher_name;
	
	
	@NotNull
	@BatchSize(size = 200)
	@Column(length = 200, columnDefinition = "Text")
	private String address;
	
	
	@NotNull
	@Pattern(regexp = "\\d{1,20}") // assuming the contact number length should be between 1 and 20
	@BatchSize(size = 32)
	@Column(length = 32)
	private String contact_number;
	
	
	@BatchSize(size = 32)
	@Column(length = 32)
	private String alternate_number;
	
	
	@Email
	@BatchSize(size = 32)
	@Column(length = 32)
	private String email;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date joining_date;
	
	
	@Column(length = 32)
	private String gender;
	
	
	@Column(length = 100)
	private String qualification;
	
	
	private int experience;
	
	
	@Column(length = 100)
	private String image;
	
	
	@Column(length = 50)
	private String government_id;
	
	
	@Column(length = 2)
	private int status;

	
	@NotNull
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
	private Institute institute;


	public Teacher() {
		super();
	}


	public Teacher(int teacher_id, @NotNull String teacher_name, @NotNull String address,
			@NotNull @Pattern(regexp = "\\d{1,20}") String contact_number, String alternate_number, @Email String email,
			Date joining_date, String gender, String qualification, int experience, String image, String government_id,
			int status, @NotNull Institute institute) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.address = address;
		this.contact_number = contact_number;
		this.alternate_number = alternate_number;
		this.email = email;
		this.joining_date = joining_date;
		this.gender = gender;
		this.qualification = qualification;
		this.experience = experience;
		this.image = image;
		this.government_id = government_id;
		this.status = status;
		this.institute = institute;
	}


	public int getTeacher_id() {
		return teacher_id;
	}


	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}


	public String getTeacher_name() {
		return teacher_name;
	}


	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact_number() {
		return contact_number;
	}


	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}


	public String getAlternate_number() {
		return alternate_number;
	}


	public void setAlternate_number(String alternate_number) {
		this.alternate_number = alternate_number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getJoining_date() {
		return joining_date;
	}


	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getGovernment_id() {
		return government_id;
	}


	public void setGovernment_id(String government_id) {
		this.government_id = government_id;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Institute getInstitute() {
		return institute;
	}


	public void setInstitute(Institute institute) {
		this.institute = institute;
	}


	@Override
	public String toString() {
		return "Teacher [teacher_id=" + teacher_id + ", teacher_name=" + teacher_name + ", address=" + address
				+ ", contact_number=" + contact_number + ", alternate_number=" + alternate_number + ", email=" + email
				+ ", joining_date=" + joining_date + ", gender=" + gender + ", qualification=" + qualification
				+ ", experience=" + experience + ", image=" + image + ", government_id=" + government_id + ", status="
				+ status + ", institute=" + institute + "]";
	}
	
	
}
