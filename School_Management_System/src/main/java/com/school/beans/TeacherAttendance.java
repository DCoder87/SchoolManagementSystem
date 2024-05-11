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
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TeacherAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TeacherAttendance_count", initialValue = 1, allocationSize = 1)
	private int teacher_attendance_id;

	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "teacher_id")
	private Teacher teacher_id;

	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "institute_id")
	private Institute institute;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_in_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_out_time;

	@Size(max = 256)
	@Column(length = 256)
	private String location;

	@Column(length = 32)
	private int month_id;

	@Column(length = 32)
	private int year_id;

	@Column(length = 16)
	private int status;

	public TeacherAttendance() {
		super();
	}

	public TeacherAttendance(int teacher_attendance_id, Teacher teacher_id, Institute institute, Date date,
			Time check_in_time, Time check_out_time, @Size(max = 256) String location, int month_id, int year_id,
			int status) {
		super();
		this.teacher_attendance_id = teacher_attendance_id;
		this.teacher_id = teacher_id;
		this.institute = institute;
		this.date = date;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.location = location;
		this.month_id = month_id;
		this.year_id = year_id;
		this.status = status;
	}

	public int getTeacher_attendance_id() {
		return teacher_attendance_id;
	}

	public void setTeacher_attendance_id(int teacher_attendance_id) {
		this.teacher_attendance_id = teacher_attendance_id;
	}

	public Teacher getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Teacher teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(Time check_in_time) {
		this.check_in_time = check_in_time;
	}

	public Time getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(Time check_out_time) {
		this.check_out_time = check_out_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMonth_id() {
		return month_id;
	}

	public void setMonth_id(int month_id) {
		this.month_id = month_id;
	}

	public int getYear_id() {
		return year_id;
	}

	public void setYear_id(int year_id) {
		this.year_id = year_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TeacherAttendance [teacher_attendance_id=" + teacher_attendance_id + ", teacher_id=" + teacher_id
				+ ", institute=" + institute + ", date=" + date + ", check_in_time=" + check_in_time
				+ ", check_out_time=" + check_out_time + ", location=" + location + ", month_id=" + month_id
				+ ", year_id=" + year_id + ", status=" + status + "]";
	}

	
}
