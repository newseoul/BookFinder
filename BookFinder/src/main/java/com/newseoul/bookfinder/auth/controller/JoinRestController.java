package com.newseoul.bookfinder.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.service.UserService;

@RestController
public class JoinRestController {
	
	@Autowired
	UserService userService;

	@PostMapping(value="api/join")
	@ResponseStatus(value = HttpStatus.OK)
	public void join(UserAccount user) {
		userService.createUser(user);
	}
}
