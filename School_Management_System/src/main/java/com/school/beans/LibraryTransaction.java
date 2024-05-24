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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class LibraryTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_transaction_count")
	@SequenceGenerator(name = "library_transaction_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int library_transaction_id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private Books books;

	@OneToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "borrowed_by", referencedColumnName = "student_id")
	private Student student;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date borrowed_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date returned_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date due_date;

	//@Column(length = 256)
	private int fine;

	@Column(length = 2)
	private int status;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	public LibraryTransaction() {
	}

	public LibraryTransaction(int library_transaction_id, Books books, Student student, Date borrowed_date,
			Date returned_date, Date due_date, int fine, int status, Institute institute) {
		this.library_transaction_id = library_transaction_id;
		this.books = books;
		this.student = student;
		this.borrowed_date = borrowed_date;
		this.returned_date = returned_date;
		this.due_date = due_date;
		this.fine = fine;
		this.status = status;
		this.institute = institute;
	}

	public int getLibrary_transaction_id() {
		return library_transaction_id;
	}

	public void setLibrary_transaction_id(int library_transaction_id) {
		this.library_transaction_id = library_transaction_id;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getBorrowed_date() {
		return borrowed_date;
	}

	public void setBorrowed_date(Date borrowed_date) {
		this.borrowed_date = borrowed_date;
	}

	public Date getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(Date returned_date) {
		this.returned_date = returned_date;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
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
		return "LibraryTransaction{" + "library_transaction_id=" + library_transaction_id + ", books=" + books
				+ ", student=" + student + ", borrowed_date=" + borrowed_date + ", returned_date=" + returned_date
				+ ", due_date=" + due_date + ", fine=" + fine + ", status=" + status + ", institute=" + institute + '}';
	}
}
