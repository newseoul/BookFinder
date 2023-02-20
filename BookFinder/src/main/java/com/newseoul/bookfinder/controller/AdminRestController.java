package com.newseoul.bookfinder.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

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
import com.newseoul.bookfinder.service.AdminService;

@RestController
@RequestMapping(value="api/admin")
public class AdminRestController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/{bookId}")
	public Book read(@PathVariable Integer bookId) {
		return adminService.getBook(bookId);
	}
	
	// 도서 등록
	@RequestMapping("/insert")
	public void insert(Book book,
		 			@RequestParam(required=false) MultipartFile img,
		 			HttpServletRequest request) {
		String filename = "";

		if (img != null && !img.isEmpty()) {
			filename =img.getOriginalFilename();
			 
			try {
				String userPath = System.getProperty("user.dir");
				String path = userPath +"\\src\\main\\resources\\static\\images\\uploads\\";
				img.transferTo(new File(path + filename));
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 
		book.setFilename(filename);
		adminService.insertBook(book); 
	}
		
	// 도서 위치
	@GetMapping("/location")
	public List<Location> locationList() {
		return adminService.getLocationList();
		
	}
	
	// 도서 분류
	@GetMapping("/category")
	public List<Category> categoryList() {
		return adminService.getCategoryList();
	}
		
	// 도서 수정
	@RequestMapping("/update")
	public void update(Book book,
					@RequestParam(required=false) MultipartFile img,
					@RequestParam(required=false) String beforeImg,
					@RequestParam(required=false) Integer categoryId,
					@RequestParam(required=false) Integer locationId,
					HttpServletRequest request) {
		
		 // 이미지 변경할 때 기존 이미지 삭제
		if (beforeImg != null && !beforeImg.equals("")) {
			String userPath = System.getProperty("user.dir");
			String path = userPath +"\\src\\main\\resources\\static\\images\\uploads\\";
			File file = new File(path + beforeImg);
			
			if (file.exists()) {
				file.delete();
				
			}
		}
		
		String filename = "";
		 if (img != null && !img.isEmpty()) {
			 filename =img.getOriginalFilename();
			 
			 try {
					String userPath = System.getProperty("user.dir");
					String path = userPath +"\\src\\main\\resources\\static\\images\\uploads\\";
					img.transferTo(new File(path + filename));
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 
		 if(categoryId != null && categoryId > 0 ) {
			 Category category = new Category();
			 category.setCategoryId(categoryId);
			 book.setCategory(category);
		 }
		 
		 if(locationId != null && locationId > 0 ) {
			 Location location = new Location();
			 location.setLocationId(locationId);
			 book.setLocation(location);
		 }
		 
		 book.setFilename(filename);
		 adminService.updatetBook(book);
	 }
}
