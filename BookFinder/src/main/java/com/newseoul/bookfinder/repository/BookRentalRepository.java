package com.newseoul.bookfinder.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;

public interface BookRentalRepository extends JpaRepository<BookRental, Integer>{
	List<BookRental> findAllByOrderByRentalIdDesc();
	List<BookRental> findByBookEqualsAndRentalStatusIn(Book book, Set<String> rentalStatuses);
}
