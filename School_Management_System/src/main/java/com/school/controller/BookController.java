package com.school.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.beans.Books;
import com.school.commom.responses.JsonResponses;
import com.school.repository.BookRepository;
import com.school.repository.InstituteRepository;
import com.school.request.BookRequest;
import com.school.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	InstituteRepository instituteRepository;

	LocalDateTime today = LocalDateTime.now();
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;

	@PostMapping("{institute_id}")
	public Map<String, Object> addbook(@RequestBody BookRequest book, @PathVariable String institute_id) {

		Books book1 = bookService.saveBook(book, institute_id);

		return book1 != null ? JsonResponses.generateResponse1(true, book1, "Book Saved")
				: JsonResponses.generateResponse1(false, book1, "Some Problem is there...");

	}

	@GetMapping("{institute_id}")
	public Map<String, Object> allbooks(@PathVariable String institute_id) {
		List<Books> allbooks = bookService.getAllBooks(institute_id);

		return !allbooks.isEmpty() ? JsonResponses.generateResponse1(true, allbooks, "All Books Fetched")
				: JsonResponses.generateResponse1(false, allbooks, "Not Books Available");
	}

	@GetMapping("/edit/{institute_id}/{book_id}")
	public Map<String, Object> getBookById(@PathVariable int book_id,@PathVariable String institute_id) {

		Books book = bookService.getById(book_id, institute_id);

		return book != null ? JsonResponses.generateResponse1(true, book, "Book found")
				: JsonResponses.generateResponse1(false, null, "data found for this Id" + book_id + "");
	}

	@PutMapping("{/update/institute_id}/{book_id}")
	public Map<String, Object> updateBook(@PathVariable int book_id, @RequestBody BookRequest bookdata,
			@PathVariable String institute_id) {
		
		Books book = bookService.getById(book_id,institute_id);

		if (book != null) {
			
			book.setBook_name(bookdata.getBook_name());
			book.setBook_auther(bookdata.getBook_auther());
			book.setBook_publisher(bookdata.getBook_publisher());
			book.setQuantity(bookdata.getQuantity());

			return bookRepository.save(book) != null
					? JsonResponses.generateResponse1(true, bookRepository.save(book), "book updated")
					: JsonResponses.generateResponse1(false, bookdata, "Invalid data");

		}
		return JsonResponses.generateResponse1(false, null, "data found for this Id" + book_id + "");
	}

	@GetMapping("/active/{institute_id}")
	public Map<String, Object> getAllactive(@PathVariable String institute_id) {
		List<Books> allactive = bookService.getActive(institute_id);

		return !allactive.isEmpty() ? JsonResponses.generateResponse1(true, allactive, "All Active Books Fetched")
				: JsonResponses.generateResponse1(false, null, "No active Book in Store");
	}

	@GetMapping("/deactive/{institute_id}")
	public Map<String, Object> getAlldeactive(@PathVariable String institute_id) {
		List<Books> alldeactive = bookService.getDeactive(institute_id);

		return !alldeactive.isEmpty() ? JsonResponses.generateResponse1(true, alldeactive, "All Deactive Books Fetched")
				: JsonResponses.generateResponse1(false, null, "No Deactive Book in Store");
	}

}
