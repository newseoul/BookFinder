package com.newseoul.bookfinder.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/admin/user")
	public String showUserList() {
		return "admin/pages/user-list";
	}
}
