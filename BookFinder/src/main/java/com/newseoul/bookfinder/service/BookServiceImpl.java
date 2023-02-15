package com.newseoul.bookfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

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
	
	

	public void insertBook(Book book) {
		bookRepository.save(book);
		
	}

}
