package com.newseoul.bookfinder.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@GetMapping("")
	public String showUserList() {
		return "admin/pages/user-list";
	}
	
	@GetMapping("/{username}")
	public String show(@PathVariable String username, Model model) {
		model.addAttribute("username", username);
		return "admin/pages/user-detail";
	}
	
}
