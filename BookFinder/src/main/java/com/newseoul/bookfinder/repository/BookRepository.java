package com.newseoul.bookfinder.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newseoul.bookfinder.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByBookNameContainingAndDisplayStatusEqualsOrderByBookNameAsc(String bookName, Integer displayStatus, Pageable pageable);
	List<Book> findByAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(String author, Integer displayStatus, Pageable pageable);
	
	long countByAuthorContainingAndDisplayStatusEquals(String author, Integer displayStatus);
	long countByBookNameContainingAndDisplayStatusEquals(String keyword, Integer displayStatus);
	
	@Query("SELECT b from Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.displayStatus=:displayStatus order by b.bookName asc")
	List<Book> findByBookNameContainingOrAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(@Param("bookName") String bookName, @Param("author") String author, @Param("displayStatus") Integer displayStatus, Pageable pageable);
	
	@Query("SELECT COUNT(*) FROM Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.displayStatus=:displayStatus order by b.bookName asc")
	long countByBookNameContainingOrAuthorContainingAndDisplayStatusEquals(@Param("bookName") String bookName, @Param("author") String author, @Param("displayStatus") Integer displayStatus);
}
