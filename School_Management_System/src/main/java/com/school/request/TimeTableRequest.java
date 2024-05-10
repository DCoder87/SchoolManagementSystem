package com.school.request;

import java.sql.Time;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TimeTableRequest {

	private int standard_id;

	private int subject_id;

	private int teacher_id;

	@Column(length = 20)
	private String day;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time start_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time end_time;

	public int getStandard_id() {
		return standard_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public String getDay() {
		return day;
	}

	public Time getStart_time() {
		return start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

}
