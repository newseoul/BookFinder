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
	public List<Book> getBookList(String bookName, int pageNo) {
		return bookRepository.findByBookNameContainingOrderByBookNameAsc(
					bookName, PageRequest.of(pageNo - 1, 15)
				);
	}

	@Override
	public long getBookCount(String bookName) {
		return bookRepository.countByBookNameContaining(bookName);
	}

}
