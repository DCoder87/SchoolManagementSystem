package com.school.beans;

import java.sql.Time;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_count")
	@SequenceGenerator(name = "guest_count", initialValue = 1, allocationSize = 1)
	private int guest_id;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "referred_by", referencedColumnName = "teacher_id")
	private Teacher teacher;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	@Column(length = 100)
	private String guest_name;

	@Column(length = 100)
	private String guest_address;

	@Column(length = 32)
	private String mobile;

	@Column(length = 50)
	private String email;

	@Column(length = 32)
	private String alternate_mobile;

	@Column(length = 100)
	private String qualification;

	@Column(length = 256)
	private String lecture_subject;

	@Column
	private String lecture_description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date lecture_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time lecture_time;

	@Column(length = 256)
	private String venue;

	@Column
	private int status;

	public Guest() {
		super();
	}

	public Guest(int guest_id, Teacher teacher, Institute institute, String guest_name, String guest_address,
			String mobile, String email, String alternate_mobile, String qualification, String lecture_subject,
			String lecture_description, Date lecture_date, Time lecture_time, String venue, int status) {
		super();
		this.guest_id = guest_id;
		this.teacher = teacher;
		this.institute = institute;
		this.guest_name = guest_name;
		this.guest_address = guest_address;
		this.mobile = mobile;
		this.email = email;
		this.alternate_mobile = alternate_mobile;
		this.qualification = qualification;
		this.lecture_subject = lecture_subject;
		this.lecture_description = lecture_description;
		this.lecture_date = lecture_date;
		this.lecture_time = lecture_time;
		this.venue = venue;
		this.status = status;
	}

	public int getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getGuest_name() {
		return guest_name;
	}

	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}

	public String getGuest_address() {
		return guest_address;
	}

	public void setGuest_address(String guest_address) {
		this.guest_address = guest_address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternate_mobile() {
		return alternate_mobile;
	}

	public void setAlternate_mobile(String alternate_mobile) {
		this.alternate_mobile = alternate_mobile;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getLecture_subject() {
		return lecture_subject;
	}

	public void setLecture_subject(String lecture_subject) {
		this.lecture_subject = lecture_subject;
	}

	public String getLecture_description() {
		return lecture_description;
	}

	public void setLecture_description(String lecture_description) {
		this.lecture_description = lecture_description;
	}

	public Date getLecture_date() {
		return lecture_date;
	}

	public void setLecture_date(Date lecture_date) {
		this.lecture_date = lecture_date;
	}

	public Time getLecture_time() {
		return lecture_time;
	}

	public void setLecture_time(Time lecture_time) {
		this.lecture_time = lecture_time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Guest [guest_id=" + guest_id + ", teacher=" + teacher + ", institute=" + institute + ", guest_name="
				+ guest_name + ", guest_address=" + guest_address + ", mobile=" + mobile + ", email=" + email
				+ ", alternate_mobile=" + alternate_mobile + ", qualification=" + qualification + ", lecture_subject="
				+ lecture_subject + ", lecture_description=" + lecture_description + ", lecture_date=" + lecture_date
				+ ", lecture_time=" + lecture_time + ", venue=" + venue + ", status=" + status + "]";
	}

}
