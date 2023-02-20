package com.newseoul.bookfinder.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
	
	@GetMapping("/join")
	public String join() {
		return "user/pages/join";
	}
	
}
