package com.newseoul.bookfinder.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.repository.BookRepository;

@Component
public class IsRentable implements Validator {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = bookRepository.findById( ((Book) target).getBookId()).orElse(null);
		if(!book.getRentalStatus().equals("rentable")) {
			errors.rejectValue("bookId", "book.isNotRentable", "대출 불가능한 도서입니다.");
		}
	}
}
