package com.newseoul.bookfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@GetMapping("/{bookId}")
	public String show(@PathVariable Integer bookId, Model model) {
		model.addAttribute("bookId", bookId);
		return "user/pages/show";
	}
}
