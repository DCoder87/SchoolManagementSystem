package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.beans.Books;


@Repository
public interface BookRepository extends JpaRepository<Books,Integer> {

	@Query(value="SELECT * FROM books  WHERE book_id = :book_id AND institute_id =:institute_id", nativeQuery = true)
	Books findByBook_id(@Param("book_id") int book_id,@Param("institute_id") String institute_id);
	
	@Query(value = "SELECT * FROM books WHERE status=1 AND institute_id =:institute_id", nativeQuery = true)
	List<Books> getAllActiveBooks(@Param("institute_id") String institute_id);
	
	@Query(value = "SELECT * FROM books WHERE status=0 AND institute_id =:institute_id", nativeQuery = true)
	List<Books> getAllDeactiveBooks(@Param("institute_id") String institute_id);
	
	@Query(value = "SELECT * FROM books WHERE institute_id =:institute_id", nativeQuery = true)
	List<Books> getAllBooks(@Param("institute_id") String institute_id);
	
}
