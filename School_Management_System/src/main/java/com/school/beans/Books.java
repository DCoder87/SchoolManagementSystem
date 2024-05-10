package com.school.beans;

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



@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_count")
	@SequenceGenerator(name = "books_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int book_id;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	
	private String book_name;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String book_auther;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String book_publisher;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String book_description;
	
	@NotNull
	private int quantity;
	
	private int status;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;

	public Books() {
		super();
	}

	public Books(int book_id, @NotNull @Size(max = 256) String book_name, @NotNull @Size(max = 256) String book_auther,
			@NotNull @Size(max = 256) String book_publisher, @NotNull @Size(max = 256) String book_description,
			@NotNull int quantity, int status, @NotNull Institute institute) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_auther = book_auther;
		this.book_publisher = book_publisher;
		this.book_description = book_description;
		this.quantity = quantity;
		this.status = status;
		this.institute = institute;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_auther() {
		return book_auther;
	}

	public void setBook_auther(String book_auther) {
		this.book_auther = book_auther;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		return "Books [book_id=" + book_id + ", book_name=" + book_name + ", book_auther=" + book_auther
				+ ", book_publisher=" + book_publisher + ", book_description=" + book_description + ", quantity="
				+ quantity + ", status=" + status + ", institute=" + institute + "]";
	}

	

}
