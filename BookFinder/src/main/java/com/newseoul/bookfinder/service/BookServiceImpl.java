package com.newseoul.bookfinder.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;
import com.newseoul.bookfinder.repository.BookRepository;
import com.newseoul.bookfinder.repository.CategoryRepository;
import com.newseoul.bookfinder.repository.LocationRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Book> getBookList(String keyword, String condition, int pageNo) {
		switch(condition) {
			case "book_name":
				return bookRepository.findByBookNameContainingAndDisplayStatusEqualsOrderByBookNameAsc(
						keyword, 1, PageRequest.of(pageNo - 1, 15)
					);
			case "author":
				return bookRepository.findByAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(
						keyword, 1, PageRequest.of(pageNo - 1, 15)
					);
			default:
				return bookRepository.findByBookNameContainingOrAuthorContainingAndDisplayStatusEqualsOrderByBookNameAsc(
						keyword, keyword, 1, PageRequest.of(pageNo - 1, 15) 
					);
		}
		
		
	}

	@Override
	public long getBookCount(String keyword, String condition) {
		switch(condition) {
			case "book_name":
				return bookRepository.countByBookNameContainingAndDisplayStatusEquals(keyword, 1);
			case "author":
				return bookRepository.countByAuthorContainingAndDisplayStatusEquals(keyword, 1);
			default:
				return bookRepository.countByBookNameContainingOrAuthorContainingAndDisplayStatusEquals(keyword, keyword, 1);
		}
	}

	@Override
	public Book getBook(int bookId) {
		return bookRepository.findById(bookId).orElse(null);
	}
	
	// 도서 등록
	@Override
	@Transactional
	public void insertBook(Book book, Integer categoryId, Integer locationId) {
		if (categoryId != null && categoryId > 0) {
			Category category = new Category();
			category.setCategoryId(categoryId);
			book.setCategory(category);
		}
		if (locationId != null && locationId > 0) {
			Location location = new Location();
			location.setLocationId(locationId);
			book.setLocation(location);
		}
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
	@Transactional
	@Override
	public void updateBook(Book book, Integer categoryId, Integer locationId) {
		Book bookToUpdate = this.getBook(book.getBookId());
		bookToUpdate.setBookName(book.getBookName());
		bookToUpdate.setAuthor(book.getAuthor());
		bookToUpdate.setPublisher(book.getPublisher());
		try {
			bookToUpdate.setPublicationDate(book.getPublicationDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (locationId != null && locationId > 0) {
			Location location = new Location();
			location.setLocationId(locationId);
			bookToUpdate.setLocation(location);
		}
		bookToUpdate.setLocationMemo(book.getLocationMemo());
		if (categoryId != null && categoryId > 0) {
			Category category = new Category();
			category.setCategoryId(categoryId);
			bookToUpdate.setCategory(category);
		}
		bookToUpdate.setDisplayStatus(book.getDisplayStatus());
		bookToUpdate.setFilename(book.getFilename());
		bookToUpdate.setBookDetail(book.getBookDetail());

		bookRepository.save(bookToUpdate);
	}
}
