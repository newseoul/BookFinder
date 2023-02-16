package com.newseoul.bookfinder.service;

import java.util.List;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Location;

public interface BookService {
	List<Book> getBookList(String keyword, String condition, int page);
	long getBookCount(String keyword, String condition);
	Book getBook(int bookId);
	List<Book> getBookList(String name, int page);
	
	void insertBook(Book book); // 도서 등록
	
	List<Location> getLocationList(); // 도서 위치
	
}
