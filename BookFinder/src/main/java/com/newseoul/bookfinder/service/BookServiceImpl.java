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
				return bookRepository.findByBookNameContainingOrderByBookNameAsc(
						keyword, PageRequest.of(pageNo - 1, 15)
					);
			case "author":
				return bookRepository.findByAuthorContainingOrderByBookNameAsc(
						keyword, PageRequest.of(pageNo - 1, 15)
					);
			default:
				return bookRepository.findByBookNameContainingOrAuthorContainingOrderByBookNameAsc(
						keyword, keyword, PageRequest.of(pageNo - 1, 15) 
					);
		}
		
		
	}

	@Override
	public long getBookCount(String keyword, String condition) {
		switch(condition) {
			case "book_name":
				return bookRepository.countByBookNameContaining(keyword);
			case "author":
				return bookRepository.countByAuthorContaining(keyword);
			default:
				return bookRepository.countByBookNameContainingOrAuthorContaining(keyword, keyword);
		}
	}

	@Override
	public Book getBook(int bookId) {
		return bookRepository.findById(bookId).orElse(null);
	}
	
	

}
