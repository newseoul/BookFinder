package com.newseoul.bookfinder.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByBookNameContainingOrAuthorContainingOrderByBookNameAsc(String bookName, String author, Pageable pageable);
	List<Book> findByBookNameContainingOrderByBookNameAsc(String bookName, Pageable pageable);
	List<Book> findByAuthorContainingOrderByBookNameAsc(String author, Pageable pageable);
	long countByBookNameContainingOrAuthorContaining(String bookName, String author);
	long countByAuthorContaining(String author);
	long countByBookNameContaining(String keyword);
}
