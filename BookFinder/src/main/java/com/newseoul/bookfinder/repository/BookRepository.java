package com.newseoul.bookfinder.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newseoul.bookfinder.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	// 도서 대출 여부 - 공백/ 저자명으로 검색
	long countByAuthorContainingAndDisplayStatusEquals(String author, Integer displayStatus);
	
	// 도서 대출 여부 - 공백/ 도서명으로 검색
	long countByBookNameContainingAndDisplayStatusEquals(String keyword, Integer displayStatus);
	
	// 도서 대출 여부 - 공백/ 전체 검색
	@Query("SELECT COUNT(*) FROM Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.displayStatus=:displayStatus order by b.bookName asc")
	long countByBookNameContainingOrAuthorContainingAndDisplayStatusEquals(@Param("bookName") String bookName, @Param("author") String author, @Param("displayStatus") Integer displayStatus);
	
	// 도서 대출 여부가 있고 / 검색조건 도서명
	long countByBookNameContainingAndRentalStatusEqualsAndDisplayStatusEquals(String keyword, String rentalStatus, Integer displayStatus);
	
	// 도서 대출 여부가 있고 / 검색조건 저자
	long countByAuthorContainingAndRentalStatusEqualsAndDisplayStatusEquals(String author, String rentalStatus, Integer displayStatus);
	
	// 도서 대출 여부가 있고 / 검색조건 전체
	@Query("SELECT COUNT(*) FROM Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.rentalStatus=:rentalStatus AND b.displayStatus=:displayStatus order by b.bookName asc")
	long countByBookNameContainingOrAuthorContainingAndRentalStatusEqualsAndDisplayStatusEqualsOrderByBookNameAsc(@Param("bookName") String bookName, @Param("author") String author, @Param("rentalStatus") String rentalStatus, @Param("displayStatus") Integer displayStatus);
	
	
	
	
	
	
	// 도서 대출 여부 - 공백 / 검색 조건 - 전체
	@Query("SELECT b from Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.displayStatus=:displayStatus order by b.bookName asc")
	List<Book> findByBookNameContainingOrAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(@Param("bookName") String bookName, @Param("author") String author, @Param("displayStatus") Integer displayStatus, Pageable pageable);
	
	// 도서 대출 여부 - 공백 / 검색 조건 - 도서명
	List<Book> findByBookNameContainingAndDisplayStatusEqualsOrderByBookNameAsc(String bookName, Integer displayStatus, Pageable pageable);
	
	// 도서 대출 여부 - 공백 / 검색 조건 - 저자
	List<Book> findByAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(String author, Integer displayStatus, Pageable pageable);


	// 도서 대출 여부가 있고 / 검색 조건 - 전체
	@Query("SELECT b from Book b WHERE (b.bookName like %:bookName% or b.author like %:author%) AND b.rentalStatus=:rentalStatus AND b.displayStatus=:displayStatus order by b.bookName asc")
	List<Book> findByBookNameContainingOrAuthorContainingAndRentalStatusEqualsAndDisplayStatusEqualsOrderByBookNameAsc(@Param("bookName") String bookName, @Param("author") String author, @Param("rentalStatus") String rentalStatus, @Param("displayStatus") Integer displayStatus, Pageable pageable);
	
	// 도서 대출 여부가 있고 / 검색 조건 - 도서명
	List<Book> findByBookNameContainingAndRentalStatusEqualsAndDisplayStatusEqualsOrderByBookNameAsc(String bookName, String rentalStatus, Integer displayStatus, Pageable pageable);
	
	// 도서 대출 여부가 있고 / 검색 조건 - 저자
	List<Book> findByAuthorContainingAndRentalStatusEqualsAndDisplayStatusEqualsOrderByBookNameAsc(String author, String rentalStatus, Integer displayStatus, Pageable pageable);
}
