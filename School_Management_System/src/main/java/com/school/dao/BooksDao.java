package com.school.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Books;
import com.school.beans.Institute;
import com.school.repository.BookRepository;
import com.school.request.BookRequest;
import com.school.service.BookService;

@Service
public class BooksDao implements BookService {

	@Autowired
	BookRepository bookRepository;
	LocalDateTime today = LocalDateTime.now();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public Books saveBook(BookRequest book, String Institute_Id) {
		String institute_id1 = decrypt.Decryption(Institute_Id);
		int i_id = Integer.parseInt(institute_id1);
		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);
			Books book1 = new Books();
			book1.setBook_name(book.getBook_name());
			book1.setBook_auther(book.getBook_auther());
			book1.setBook_description(book.getBook_description());
			book1.setBook_publisher(book.getBook_publisher());
			book1.setQuantity(book.getQuantity());
			book1.setStatus(1);
			book1.setInstitute(institute);

			return bookRepository.save(book1);

		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public List<Books> getAllBooks(String Institute_id) {
	
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		return bookRepository.getAllBooks(i_id);
	}

	@Override
	public List<Books> getActive(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		return bookRepository.getAllActiveBooks(i_id);
	}

	@Override
	public Books getById(int book_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		return bookRepository.findByBook_id(book_id, i_id);
	}

	@Override
	public List<Books> getDeactive(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		int i_id = Integer.parseInt(institute_id1);
		return bookRepository.getAllActiveBooks(i_id); 
	}
	

	



	

}
