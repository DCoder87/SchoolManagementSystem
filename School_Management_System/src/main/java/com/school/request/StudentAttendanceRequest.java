package com.school.request;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class StudentAttendanceRequest {
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "student_id")
	private int student_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "institute_id")
	private int institute_id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_in_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_out_time;

	@Column(length = 256)
	private String location;

	@Column(length = 32)
	private int month_id;

	@Column(length = 32)
	private int year_id;

	public int getStudent_id() {
		return student_id;
	}

	public Date getDate() {
		return date;
	}

	public Time getCheck_in_time() {
		return check_in_time;
	}

	public Time getCheck_out_time() {
		return check_out_time;
	}

	public String getLocation() {
		return location;
	}

	public int getMonth_id() {
		return month_id;
	}

	public int getYear_id() {
		return year_id;
	}

	public int getInstitute_id() {
		return institute_id;
	}

}
