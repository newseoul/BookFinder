package com.newseoul.bookfinder.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;
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
	
	@GetMapping("/{bookId}")
	public Book read(@PathVariable Integer bookId) {
		return bookService.getBook(bookId);
	}
	
	// 도서 등록 - img
	/*
	 * @RequestMapping("/insert") public void insert(Book book,
	 * 
	 * @RequestParam(required=false) MultipartFile img, HttpServletRequest request)
	 * {
	 * 
	 * String filename = ""; if (img != null && !img.isEmpty()) { filename =
	 * img.getOriginalFilename(); try { ServletContext application =
	 * request.getSession().getServletContext(); String path =
	 * application.getRealPath("/images/"); img.transferTo(new File(path +
	 * filename));
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } book.setFilename(filename);
	 * bookService.insertBook(book); }
	 */
	 
	
	// 도서등록
	@RequestMapping("/insert") 
	public void insert(Book book) {
		bookService.insertBook(book);
	}
		
	// 도서 위치
	@GetMapping("/location")
	public List<Location> locationList() {
		return bookService.getLocationList();
		
	}
	
	// 도서 분류
	@GetMapping("/category")
	public List<Category> categoryList() {
		return bookService.getCategoryList();
	}
		
	// 도서 수정
	@RequestMapping("/update")
	public void update(Book book, @RequestParam(required=false) MultipartFile img, HttpServletRequest request) {
		
	String filename = "";
	if (img != null && !img.isEmpty()) {
		filename = img.getOriginalFilename();
		try {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/upload/images/");
			img.transferTo(new File(path + filename));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else {
		filename = bookService.getBook(book.getBookId()).getFilename();
	}

	book.setFilename(filename);
		bookService.upDatetBook(book);
	}
	
}
