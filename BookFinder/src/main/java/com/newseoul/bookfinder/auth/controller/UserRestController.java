package com.newseoul.bookfinder.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.service.UserService;
import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;

@RestController
@RequestMapping(value="api/user")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public List<UserAccount> list(
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") Integer page
			) {
		return userService.getUserList(keyword, page);
	} 
	
	@GetMapping("/count")
	public long count() {
		return userService.getUserCount();
	}
	
	@GetMapping("{username}")
	public UserAccount read(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@GetMapping("{username}/rental")
	public List<Map<String, String>> rentalList(@PathVariable String username) {
		return userService.getBookRentalList(username);
		
	}
}
