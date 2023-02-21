package com.newseoul.bookfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping
	public String index(Model model) {
		return "admin/pages/book-list";
	}
	
	@GetMapping("/rental")
	public String rentalList() {
		return "admin/pages/rental-list"; 
	}
}
