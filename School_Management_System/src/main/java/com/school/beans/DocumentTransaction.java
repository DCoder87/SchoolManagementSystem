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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DocumentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_tranasaction_count")
	@SequenceGenerator(name = "document_tranasaction_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int document_transaction_id;
	
	@OneToOne
	@JoinColumn(name="document_master_id")
	private DocumentMaster documentmaster;
	
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date document_transaction_date;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date documentvalidity;
	
	private double amount_taken;
	
	 @NotNull
	 @ManyToOne(cascade = {CascadeType.REFRESH})
     @JoinColumn(name = "institute_id")
	 private Institute institute ;

	public DocumentTransaction() {
		super();
	}

	public DocumentTransaction(int document_transaction_id, DocumentMaster documentmaster, Student student,
			Date document_transaction_date, Date documentvalidity, double amount_taken, @NotNull Institute institute) {
		super();
		this.document_transaction_id = document_transaction_id;
		this.documentmaster = documentmaster;
		this.student = student;
		this.document_transaction_date = document_transaction_date;
		this.documentvalidity = documentvalidity;
		this.amount_taken = amount_taken;
		this.institute = institute;
	}

	public int getDocument_transaction_id() {
		return document_transaction_id;
	}

	public void setDocument_transaction_id(int document_transaction_id) {
		this.document_transaction_id = document_transaction_id;
	}

	public DocumentMaster getDocumentmaster() {
		return documentmaster;
	}

	public void setDocumentmaster(DocumentMaster documentmaster) {
		this.documentmaster = documentmaster;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDocument_transaction_date() {
		return document_transaction_date;
	}

	public void setDocument_transaction_date(Date document_transaction_date) {
		this.document_transaction_date = document_transaction_date;
	}

	public Date getDocumentvalidity() {
		return documentvalidity;
	}

	public void setDocumentvalidity(Date documentvalidity) {
		this.documentvalidity = documentvalidity;
	}

	public double getAmount_taken() {
		return amount_taken;
	}

	public void setAmount_taken(double amount_taken) {
		this.amount_taken = amount_taken;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	@Override
	public String toString() {
		return "DocumentTransaction [document_transaction_id=" + document_transaction_id + ", documentmaster="
				+ documentmaster + ", student=" + student + ", document_transaction_date=" + document_transaction_date
				+ ", documentvalidity=" + documentvalidity + ", amount_taken=" + amount_taken + ", institute="
				+ institute + "]";
	}

	 
}
