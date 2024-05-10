package com.school.beans;

import java.sql.Time;

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
public class TimeTable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timetable_count")
    @SequenceGenerator(name = "timetable_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
    private int timetabe_id;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "standard_id")
    private StandardMaster standardmaster;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "subject_id")
    private SubjectMaster subjectmaster;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NotNull
    @Size(max = 10)
    @Column(length = 10)
    private String day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time endTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time totalDuration;
    
    @Column(length = 2)
	private int status;

	public TimeTable() {
		super();
	}

	

	public TimeTable(int timetabe_id, @NotNull Institute institute, @NotNull StandardMaster standardmaster,
			@NotNull SubjectMaster subjectmaster, @NotNull Teacher teacher, @NotNull @Size(max = 10) String day,
			Time startTime, Time endTime, @NotNull @Size(max = 10) Time totalDuration, int status) {
		super();
		this.timetabe_id = timetabe_id;
		this.institute = institute;
		this.standardmaster = standardmaster;
		this.subjectmaster = subjectmaster;
		this.teacher = teacher;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalDuration = totalDuration;
		this.status = status;
	}



	public int getTimetabe_id() {
		return timetabe_id;
	}

	public void setTimetabe_id(int timetabe_id) {
		this.timetabe_id = timetabe_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public StandardMaster getStandardmaster() {
		return standardmaster;
	}

	public void setStandardmaster(StandardMaster standardmaster) {
		this.standardmaster = standardmaster;
	}

	public SubjectMaster getSubjectmaster() {
		return subjectmaster;
	}

	public void setSubjectmaster(SubjectMaster subjectmaster) {
		this.subjectmaster = subjectmaster;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Time totalDuration) {
		this.totalDuration = totalDuration;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "TimeTable [timetabe_id=" + timetabe_id + ", institute=" + institute + ", standardmaster="
				+ standardmaster + ", subjectmaster=" + subjectmaster + ", teacher=" + teacher + ", day=" + day
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", totalDuration=" + totalDuration + ", status="
				+ status + "]";
	}



	

	
    
    
}
