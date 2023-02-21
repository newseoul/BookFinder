package com.newseoul.bookfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.model.BookRental;

public interface BookRentalRepository extends JpaRepository<BookRental, Integer>{

}
