package com.newseoul.bookfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping
	public String adminIndex(Model model) {
		return "admin/pages/book-list";
	}
	
	// 도서 등록
	@RequestMapping("/book-write")
	public String write(Model model) {
		return "admin/pages/book-write";
	}
	
	// 도서 상세
	@GetMapping("/book-detail/{bookId}")
	public String detail(@PathVariable Integer bookId, Model model) {
		model.addAttribute("bookId", bookId);
		return "admin/pages/book-detail";
	}
}
