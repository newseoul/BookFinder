package com.newseoul.bookfinder.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.auth.service.UserService;
import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;
import com.newseoul.bookfinder.service.BookRentalService;
import com.newseoul.bookfinder.service.BookService;
import com.newseoul.bookfinder.validator.IsRentable;

@RestController
@RequestMapping(value = "/api/rental")
public class BookRentalRestController {
	
	@Autowired
	BookRentalService bookRentalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	IsRentable isRentable;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(isRentable);
	}
	
	@GetMapping("")
	public List<BookRental> list() {
		return bookRentalService.getBookRentalList();
	}
	
	@PostMapping("/{bookId}")
	public ResponseEntity<BookRental> rent(@Validated Book book, BindingResult bindingResult){
		// 대출 가능한 도서가 아닌 경우
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		// 미로그인시
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken ) {
			return ResponseEntity.status(403).build();
		}
		
		try {
			BookRental bookRental = bookRentalService.rentBook(book.getBookId(), auth.getName());
			return ResponseEntity.ok(bookRental);
		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/{bookId}/return")
	public ResponseEntity<List<BookRental>> returnBook(Book book){
		try {
			System.out.println(book.getBookId());
			List<BookRental> bookRentalList = bookRentalService.returnBook(book);
			return ResponseEntity.ok(bookRentalList);
		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}
