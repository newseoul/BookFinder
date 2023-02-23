package com.newseoul.bookfinder.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.UserRepository;
import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;
import com.newseoul.bookfinder.repository.BookRentalRepository;
import com.newseoul.bookfinder.repository.BookRepository;

@Service
public class BookRentalServiceImpl implements BookRentalService {
	
	@Autowired 
	private BookRentalRepository bookRentalRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BookRental> getBookRentalList() {
		List<BookRental> bookRentalList = bookRentalRepository.findAllByOrderByRentalIdDesc();
		return bookRentalList.stream()
		.map(bookRental -> { bookRental.getUserAccount().setPassword("*******"); return bookRental; })
		.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public BookRental rentBook(Integer bookId, String username) throws ParseException {
		// 도서 대출 레코드 설정
		BookRental bookRental = new BookRental();
		bookRental.setRentalStatus("on_rental");
		LocalDateTime now = LocalDateTime.now();
		bookRental.setRentalDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(now));
		bookRental.setReturnDueDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(now.plusDays(7L)));

		// 도서 대출 상태 변경
		Book book = bookRepository.findById(bookId).orElse(null);
		book.setRentalStatus("on_rental");
		bookRepository.save(book);
		
		// 도서, 사용자 관계 설정
		book.addBookRental(bookRental);
		UserAccount user = userRepository.findById(username).orElse(null);
		user.addBookRental(bookRental);
		
		return bookRentalRepository.save(bookRental);
	}

}
