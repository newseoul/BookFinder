package com.newseoul.bookfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/book")
public class BookAdminController {
	
	@GetMapping("")
	public String index() {
		return "admin/pages/book-list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "admin/pages/book-write";
	}

	@GetMapping("/{bookId}")
	public String detail(@PathVariable Integer bookId, Model model) {
		model.addAttribute("bookId", bookId);
		return "admin/pages/book-detail";
	}
}
