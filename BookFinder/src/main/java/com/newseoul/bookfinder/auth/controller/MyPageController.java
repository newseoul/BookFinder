package com.newseoul.bookfinder.auth.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

	@GetMapping("/mypage")
	public String show(Model model) {
		// 미로그인시
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken ) {
			return "redirect:/login";
		}
		String username = auth.getName();
		model.addAttribute("username", username);
		return "user/pages/mypage";
	}
}
