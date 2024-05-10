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
public class FeesTransaction {

	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fees_transaction_count")
	    @SequenceGenerator(name = "fees_transaction_count", initialValue = 1, allocationSize = 1)
	    @Column(length = 16)
	    private int transaction_id;
	 
	    @NotNull
	    @ManyToOne(cascade = {CascadeType.REFRESH})
		@JoinColumn(name = "institute_id")
	    private Institute institute ;

	    @NotNull
	    @ManyToOne(cascade = {CascadeType.REFRESH})
	    @JoinColumn(name = "fee_id")
	    private Fees fees;

	    @NotNull
	    @ManyToOne(cascade = {CascadeType.REFRESH})
	    @JoinColumn(name = "student_id")
	    private Student student;
	    
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    private Date transaction_date;	
	    
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	  	private Date next_due_date;

	  	@NotNull
	    @ManyToOne(cascade = {CascadeType.REFRESH})
	    @JoinColumn(name = "transaction_type_id", referencedColumnName = "type_id")
	    private TransactionTypeMaster typemaster;
	  	
	    @NotNull
		private double paid_amount;

	    @NotNull
	    @Size(max = 32)
	    @Column(name = "amount_type")
	    private String amount_type;

	    @NotNull
	    private boolean bill_status;

	    @NotNull
	    @Column(length = 2)
	    private int status;

		public FeesTransaction() {
			super();
		}

		public FeesTransaction(int transaction_id, @NotNull Institute institute, @NotNull Fees fees,
				@NotNull Student student, Date transaction_date, Date next_due_date,
				@NotNull TransactionTypeMaster typemaster, @NotNull double paid_amount,
				@NotNull @Size(max = 32) String amount_type, @NotNull boolean bill_status, @NotNull int status) {
			super();
			this.transaction_id = transaction_id;
			this.institute = institute;
			this.fees = fees;
			this.student = student;
			this.transaction_date = transaction_date;
			this.next_due_date = next_due_date;
			this.typemaster = typemaster;
			this.paid_amount = paid_amount;
			this.amount_type = amount_type;
			this.bill_status = bill_status;
			this.status = status;
		}

		public int getTransaction_id() {
			return transaction_id;
		}

		public void setTransaction_id(int transaction_id) {
			this.transaction_id = transaction_id;
		}

		public Institute getInstitute() {
			return institute;
		}

		public void setInstitute(Institute institute) {
			this.institute = institute;
		}

		public Fees getFees() {
			return fees;
		}

		public void setFees(Fees fees) {
			this.fees = fees;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Date getTransaction_date() {
			return transaction_date;
		}

		public void setTransaction_date(Date transaction_date) {
			this.transaction_date = transaction_date;
		}

		public Date getNext_due_date() {
			return next_due_date;
		}

		public void setNext_due_date(Date next_due_date) {
			this.next_due_date = next_due_date;
		}

		public TransactionTypeMaster getTypemaster() {
			return typemaster;
		}

		public void setTypemaster(TransactionTypeMaster typemaster) {
			this.typemaster = typemaster;
		}

		public double getPaid_amount() {
			return paid_amount;
		}

		public void setPaid_amount(double paid_amount) {
			this.paid_amount = paid_amount;
		}

		public String getAmount_type() {
			return amount_type;
		}

		public void setAmount_type(String amount_type) {
			this.amount_type = amount_type;
		}

		public boolean isBill_status() {
			return bill_status;
		}

		public void setBill_status(boolean bill_status) {
			this.bill_status = bill_status;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "FeesTransaction [transaction_id=" + transaction_id + ", institute=" + institute + ", fees=" + fees
					+ ", student=" + student + ", transaction_date=" + transaction_date + ", next_due_date="
					+ next_due_date + ", typemaster=" + typemaster + ", paid_amount=" + paid_amount + ", amount_type="
					+ amount_type + ", bill_status=" + bill_status + ", status=" + status + "]";
		}
	    
	    

}
