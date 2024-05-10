package com.school.beans;

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

@Entity
public class Fees {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fees_count")
    @SequenceGenerator(name = "fees_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
    private int fee_id;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;

    @NotNull
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "standard_id")
    private StandardMaster standardmaster;

    @NotNull
    private double total_amount;

    @Column(name = "late_fee")
    private double late_fee;

    @NotNull
    private double grand_total;

    @NotNull
    @Size(max = 32)
    @Column(name = "amount_type")
    private String amount_type;

    @NotNull
    @Column(length = 2)
    private int status;

	public Fees() {
		super();
	}

	public Fees(int fee_id, @NotNull Institute institute, @NotNull Student student,
			@NotNull StandardMaster standardmaster, @NotNull double total_amount, double late_fee,
			@NotNull double grand_total, @NotNull @Size(max = 32) String amount_type, @NotNull int status) {
		super();
		this.fee_id = fee_id;
		this.institute = institute;
		this.student = student;
		this.standardmaster = standardmaster;
		this.total_amount = total_amount;
		this.late_fee = late_fee;
		this.grand_total = grand_total;
		this.amount_type = amount_type;
		this.status = status;
	}

	public int getFee_id() {
		return fee_id;
	}

	public void setFee_id(int fee_id) {
		this.fee_id = fee_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StandardMaster getStandardmaster() {
		return standardmaster;
	}

	public void setStandardmaster(StandardMaster standardmaster) {
		this.standardmaster = standardmaster;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public double getLate_fee() {
		return late_fee;
	}

	public void setLate_fee(double late_fee) {
		this.late_fee = late_fee;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getAmount_type() {
		return amount_type;
	}

	public void setAmount_type(String amount_type) {
		this.amount_type = amount_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Fees [fee_id=" + fee_id + ", institute=" + institute + ", student=" + student + ", standardmaster="
				+ standardmaster + ", total_amount=" + total_amount + ", late_fee=" + late_fee + ", grand_total="
				+ grand_total + ", amount_type=" + amount_type + ", status=" + status + "]";
	}

    
}
