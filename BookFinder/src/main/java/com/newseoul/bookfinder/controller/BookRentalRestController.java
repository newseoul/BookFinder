package com.newseoul.bookfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.model.BookRental;
import com.newseoul.bookfinder.service.BookRentalService;

@RestController
@RequestMapping(value = "/api/rental")
public class BookRentalRestController {
	
	@Autowired
	BookRentalService bookRentalService;
	
	@GetMapping("")
	public List<BookRental> list() {
		return bookRentalService.getBookRentalList();
	}
}
