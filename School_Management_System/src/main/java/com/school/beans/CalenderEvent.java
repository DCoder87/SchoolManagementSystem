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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class CalenderEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calender_event_count")
	@SequenceGenerator(name = "calender_event_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int event_id;

	@NotNull
	@Size(max = 100)
	@Column(length = 100)
	private String event_name;

	@NotNull
	@Size(max = 100)
	@Column(length = 100)
	private String event_type;

	@NotNull
	@Column(name = "event_description", columnDefinition = "TEXT")
	private String event_description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date start_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date end_date;

	@Column(length = 2)
	private int status;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	public CalenderEvent() {
		super();
	}

	public CalenderEvent(int event_id, @NotNull @Size(max = 100) String event_name,
			@NotNull @Size(max = 100) String event_type, @NotNull String event_description, Date start_date,
			Date end_date, int status, @NotNull Institute institute) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_type = event_type;
		this.event_description = event_description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.institute = institute;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
		return "CalenderEvent [event_id=" + event_id + ", event_name=" + event_name + ", event_type=" + event_type
				+ ", event_description=" + event_description + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", status=" + status + ", institute=" + institute + "]";
	}

	
}