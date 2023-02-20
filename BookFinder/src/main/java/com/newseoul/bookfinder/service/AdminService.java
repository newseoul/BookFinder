package com.newseoul.bookfinder.service;

import java.util.List;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;

public interface AdminService {
	Book getBook(int bookId);
	void insertBook(Book book); // 도서 등록
	List<Location> getLocationList(); // 도서 위치
	List<Category> getCategoryList(); // 도서 분류
	void updatetBook(Book book); // 도서 수정
}
