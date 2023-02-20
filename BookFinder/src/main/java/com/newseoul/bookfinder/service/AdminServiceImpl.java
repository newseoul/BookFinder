package com.newseoul.bookfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;
import com.newseoul.bookfinder.repository.BookRepository;
import com.newseoul.bookfinder.repository.CategoryRepository;
import com.newseoul.bookfinder.repository.LocationRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Book getBook(int bookId) {
		return bookRepository.findById(bookId).orElse(null);
	}
	
	// 도서 등록
	@Override
	public void insertBook(Book book) {
		bookRepository.save(book);
	}

	// 도서 위치
	@Override
	public List<Location> getLocationList() {
		return locationRepository.findAll();
	}
	
	// 도서 분류
	@Override
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}

	// 도서 수정
	@Override
	public void updatetBook(Book book) {
		bookRepository.save(book);
	}
}
