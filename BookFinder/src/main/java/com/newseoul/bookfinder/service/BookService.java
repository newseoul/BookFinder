package com.newseoul.bookfinder.service;

import java.util.List;

import com.newseoul.bookfinder.model.Book;

public interface BookService {
	List<Book> getBookList(String keyword, String condition, int page);
	long getBookCount(String keyword, String condition);
}
