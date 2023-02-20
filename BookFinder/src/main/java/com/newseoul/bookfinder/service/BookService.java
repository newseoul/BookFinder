package com.newseoul.bookfinder.service;

import java.util.List;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;

public interface BookService {
	List<Book> getBookList(String keyword, String condition, int page);
	long getBookCount(String keyword, String condition);
	Book getBook(int bookId);
}
