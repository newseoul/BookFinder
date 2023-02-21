package com.newseoul.bookfinder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.model.BookRental;
import com.newseoul.bookfinder.repository.BookRentalRepository;

@Service
public class BookRentalServiceImpl implements BookRentalService {
	
	@Autowired 
	private BookRentalRepository bookRentalRepository;

	@Override
	public List<BookRental> getBookRentalList() {
		List<BookRental> bookRentalList = bookRentalRepository.findAll();
		return bookRentalList.stream()
		.map(bookRental -> { bookRental.getUserAccount().setPassword("*******"); return bookRental; })
		.collect(Collectors.toList());
	}

}
