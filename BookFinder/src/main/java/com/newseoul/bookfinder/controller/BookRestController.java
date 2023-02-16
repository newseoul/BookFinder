package com.newseoul.bookfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.service.BookService;

@RestController
@RequestMapping(value="api/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("")
	public List<Book> list(
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "") String condition,
				@RequestParam(defaultValue = "1") Integer page
			) {
		return bookService.getBookList(keyword, condition, page);
	}
	
	@GetMapping("/count")
	public long count(@RequestParam(defaultValue = "") String keyword,
					  @RequestParam(defaultValue = "") String condition) {
		return bookService.getBookCount(keyword, condition);
	}
	
}
