package com.school.request;

public class BookRequest {

	private String book_name;

	private String book_auther;

	private String book_publisher;

	private int quantity;

	private String book_description;

	public String getBook_description() {
		return book_description;
	}

	public String getBook_name() {
		return book_name;
	}

	public String getBook_auther() {
		return book_auther;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "BookRequest [book_name=" + book_name + ", book_auther=" + book_auther + ", book_publisher="
				+ book_publisher + ", quantity=" + quantity + ", book_description=" + book_description + "]";
	}

    

}
