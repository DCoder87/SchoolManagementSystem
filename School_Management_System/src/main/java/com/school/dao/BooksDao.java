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
	public Books updateBook(BookRequest book, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
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
			book1.setBook_id(id);

			return bookRepository.save(book1);
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public List<Books> getAllBooks(String Institute_id) {

		String institute_id1 = decrypt.Decryption(Institute_id);

		return bookRepository.getAllBooks(institute_id1);
	}

	@Override
	public List<Books> getActive(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return bookRepository.getAllActiveBooks(institute_id1);
	}

	@Override
	public Books getById(int book_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return bookRepository.findByBook_id(book_id, institute_id1);
	}

	@Override
	public List<Books> getDeactive(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return bookRepository.getAllActiveBooks(institute_id1);
	}

	@Override
	public int deleteBookById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = bookRepository.deleteBookById(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	

}
