package com.newseoul.bookfinder.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.service.UserService;

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
}
