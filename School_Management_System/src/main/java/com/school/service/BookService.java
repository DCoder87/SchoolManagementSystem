package com.school.service;

import java.util.List;

import com.school.beans.Books;
import com.school.request.BookRequest;

public interface BookService {
	
	public Books saveBook(BookRequest book,String Institute_Id);
	
	public List<Books> getAllBooks(String Institute_id);
	
	public List<Books> getActive(String Institute_id);
	
	public List<Books> getDeactive(String Institute_id);
	
	public Books getById(int book_id,String Institute_id);
	
	
	

}
