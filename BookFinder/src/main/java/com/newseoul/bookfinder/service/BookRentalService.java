package com.newseoul.bookfinder.service;

import java.text.ParseException;
import java.util.List;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;

public interface BookRentalService {
	List<BookRental> getBookRentalList();
	BookRental rentBook(Integer bookId, String username) throws ParseException;
}
