package com.newseoul.bookfinder.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
				return bookRepository.countByAuthorContainingAndDisplayStatusEquals(keyword, 1);
			case "author":
				return bookRepository.countByBookNameContainingAndDisplayStatusEquals(keyword, 1);
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
	public void upDatetBook(Book book) {
		
		Book s_book = getBook(book.getBookId());
		
		s_book.setBookName(book.getBookName());
		s_book.setAuthor(book.getAuthor());
		s_book.setPublisher(book.getPublisher());
		
		try {
			s_book.setPublicationDate(book.getPublicationDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		s_book.setLocation(book.getLocation());
		s_book.setLocationMemo(book.getLocationMemo());
		s_book.setCategory(book.getCategory());
		s_book.setDisplayStatus(book.getDisplayStatus());
		s_book.setFilename(book.getFilename());
		s_book.setBookDetail(book.getBookDetail());
		
		bookRepository.save(s_book);
		
	}

}
