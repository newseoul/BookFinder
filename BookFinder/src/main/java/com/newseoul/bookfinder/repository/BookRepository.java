package com.newseoul.bookfinder.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByBookNameContainingAndDisplayStatusEqualsOrderByBookNameAsc(String bookName, Integer displayStatus, Pageable pageable);
	List<Book> findByAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(String author, Integer displayStatus, Pageable pageable);
	List<Book> findByBookNameContainingOrAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(String bookName, String author, Integer displayStatus, Pageable pageable);
	long countByAuthorContainingAndDisplayStatusEquals(String author, Integer displayStatus);
	long countByBookNameContainingAndDisplayStatusEquals(String keyword, Integer displayStatus);
	long countByBookNameContainingOrAuthorContainingAndDisplayStatusEquals(String bookName, String author, Integer displayStatus);
}
