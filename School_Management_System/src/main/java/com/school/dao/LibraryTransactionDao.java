package com.school.dao;

import com.school.beans.Books;

import com.school.beans.Institute;
import com.school.beans.LibraryTransaction;
import com.school.beans.Student;
import com.school.repository.LibraryTransactionRepository;
import com.school.request.LibraryTransactionRequest;
import com.school.service.LibraryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryTransactionDao implements LibraryTransactionService {
	@Autowired
	LibraryTransactionRepository libraryTransactionRepository;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public LibraryTransaction saveLibraryTransaction(LibraryTransactionRequest libraryRequest, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Books book = new Books();
			book.setBook_id(libraryRequest.getBook_id());

			Student student = new Student();
			student.setStudent_id(libraryRequest.getStudent_id());

			LibraryTransaction libraryTransaction = new LibraryTransaction();
			libraryTransaction.setFine(libraryRequest.getFine());
			libraryTransaction.setBorrowed_date(libraryRequest.getBorrowed_date());
			libraryTransaction.setStatus(1);
			libraryTransaction.setDue_date(libraryRequest.getDue_date());
			libraryTransaction.setReturned_date(libraryRequest.getReturned_date());
			libraryTransaction.setBooks(book);
			libraryTransaction.setStudent(student);
			libraryTransaction.setInstitute(institute);

			return libraryTransactionRepository.save(libraryTransaction);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public LibraryTransaction updateLibraryTransaction(LibraryTransactionRequest libraryRequest, String institute_id,
			int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Books book = new Books();
			book.setBook_id(libraryRequest.getBook_id());

			Student student = new Student();
			student.setStudent_id(libraryRequest.getStudent_id());

			LibraryTransaction libraryTransaction = new LibraryTransaction();
			libraryTransaction.setFine(libraryRequest.getFine());
			libraryTransaction.setBorrowed_date(libraryRequest.getBorrowed_date());
			libraryTransaction.setStatus(1);
			libraryTransaction.setDue_date(libraryRequest.getDue_date());
			libraryTransaction.setReturned_date(libraryRequest.getReturned_date());
			libraryTransaction.setBooks(book);
			libraryTransaction.setStudent(student);
			libraryTransaction.setInstitute(institute);
			libraryTransaction.setLibrary_transaction_id(id);

			return libraryTransactionRepository.save(libraryTransaction);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<LibraryTransaction> getallLibraryTransaction(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return libraryTransactionRepository.ListOfAllLibraryTransaction(institute_id1);
	}

	@Override
	public Optional<LibraryTransaction> findLibraryTransactiontById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return libraryTransactionRepository.findLibraryTransactionById(id, institute_id1);
	}

	@Override
	public int deleteLibraryTransactionByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = libraryTransactionRepository.deleteByLibraryTransactionId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeLibraryTransactionByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = libraryTransactionRepository.activeByLibraryTransactionId(id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<LibraryTransaction> getdeletedLibraryTransaction(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return libraryTransactionRepository.ListOfdeletedLibraryTransaction(institute_id1);
	}

	@Override
	public String findLibraryTransactionByLibraryTransactionId(String library_transaction_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		String libt_id = libraryTransactionRepository
				.findLibraryTransactionByLibraryTransactionId(library_transaction_id, institute_id1);
		return libt_id;
	}
}
