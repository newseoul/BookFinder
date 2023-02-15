package com.newseoul.bookfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {
	
	@GetMapping
	public String index(Model model) {
		return "user/pages/main";
	}
	
	// 글등록
	@RequestMapping("/book-write")
	public String write(Model model) {
		return "admin/pages/book-write";
	}
	
	
}
