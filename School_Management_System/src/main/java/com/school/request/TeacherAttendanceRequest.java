package com.school.request;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TeacherAttendanceRequest {

	private int institute_id;

	private int teacher_id;

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

	public int getTeacher_id() {
		return teacher_id;
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
