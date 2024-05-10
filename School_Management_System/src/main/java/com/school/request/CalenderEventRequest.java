package com.school.request;

import java.util.Date;

public class CalenderEventRequest {

	private String event_name;

	private String event_type;

	private String event_description;

	private Date start_date;

	private Date end_date;

	public CalenderEventRequest() {
		super();
	}

	public String getEvent_name() {
		return event_name;
	}

	public String getEvent_type() {
		return event_type;
	}

	public String getEvent_description() {
		return event_description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	@Override
	public String toString() {
		return "CalenderEventRequest [event_name=" + event_name + ", event_type=" + event_type + ", event_description="
				+ event_description + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}



}
