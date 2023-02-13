package com.newseoul.bookfinder.service;

import java.util.List;

import com.newseoul.bookfinder.model.Book;

public interface BookService {
	List<Book> getBookList(String name, int pageNo);
}
