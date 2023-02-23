package com.newseoul.bookfinder.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.Category;
import com.newseoul.bookfinder.model.Location;
import com.newseoul.bookfinder.service.BookService;

@RestController
@RequestMapping(value = "api/book")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@GetMapping("")
	public List<Book> list(@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "") String condition, 
			// 전체보기:"", 대여가능:"rentable", 대여중:"on_rental", 연체중:"overdue")
			@RequestParam(defaultValue = "") String rentalStatus,
			@RequestParam(defaultValue = "1") Integer page) {
		return bookService.getBookList(keyword, rentalStatus, condition, page);
	}

	@GetMapping("/count")
	public long count(@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "") String condition,
			// 전체보기:"", 대여가능:"rentable", 대여중:"on_rental", 연체중:"overdue")
			@RequestParam(defaultValue = "") String rentalStatus) {
		return bookService.getBookCount(keyword, rentalStatus, condition);
	}

	@GetMapping("/{bookId}")
	public Book read(@PathVariable Integer bookId) {
		return bookService.getBook(bookId);
	}

	// 도서 등록
	@PostMapping("")
	public void insert(Book book, 
			@RequestParam(required = false) MultipartFile img, 
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer locationId,
			HttpServletRequest request) {
		String filename = "";

		if (img != null && !img.isEmpty()) {
			filename = img.getOriginalFilename();

			try {
				String userPath = System.getProperty("user.dir");
				String path = userPath + "\\src\\main\\resources\\static\\images\\uploads\\";
				img.transferTo(new File(path + filename));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		book.setFilename(filename);
		bookService.insertBook(book, categoryId, locationId);
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
	@PutMapping("/{bookId}")
	public void update(@PathVariable Integer bookId,
			Book book, 
			@RequestParam(required = false) MultipartFile img,
			@RequestParam(required = false) String beforeImg, 
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer locationId, 
			HttpServletRequest request) {

		

		if (img != null && !img.isEmpty()) {
			
			// 이미지 변경할 때 기존 이미지 삭제
			if (beforeImg != null && !beforeImg.equals("")) {
				String userPath = System.getProperty("user.dir");
				String path = userPath + "\\src\\main\\resources\\static\\images\\uploads\\";
				File file = new File(path + beforeImg);

				if (file.exists()) {
					file.delete();

				}
			}
			
			String filename = "";
			filename = img.getOriginalFilename();

			try {
				String userPath = System.getProperty("user.dir");
				String path = userPath + "\\src\\main\\resources\\static\\images\\uploads\\";
				img.transferTo(new File(path + filename));

			} catch (Exception e) {
				e.printStackTrace();
			}
			book.setFilename(filename);
		}

		bookService.updateBook(book, categoryId, locationId);
	}
	
	@GetMapping("/{bookId}/rental")
	public List<Map<String, String>> rentalList(@PathVariable Integer bookId) {
		return bookService.getBookRentalList(bookId);
	}
}
