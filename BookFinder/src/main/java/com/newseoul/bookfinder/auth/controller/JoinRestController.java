package com.newseoul.bookfinder.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.service.UserService;
import com.newseoul.bookfinder.auth.validators.ConfirmPassword;
import com.newseoul.bookfinder.auth.validators.IsEmailUnique;
import com.newseoul.bookfinder.auth.validators.IsIdUnique;
import com.newseoul.bookfinder.auth.validators.IsPhoneNumberUnique;

@RestController
public class JoinRestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private IsIdUnique isIdUniqueValidator;
	@Autowired
	private ConfirmPassword confirmPasswordValidator;
	@Autowired
	private IsEmailUnique isEmailUniqueValidator;
	@Autowired
	private IsPhoneNumberUnique isPhoneNumberUnique;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(isIdUniqueValidator);
		binder.addValidators(confirmPasswordValidator);
		binder.addValidators(isEmailUniqueValidator);
		binder.addValidators(isPhoneNumberUnique);
	}

	@PostMapping(value="api/join")
	public ResponseEntity<Map<String, Object>> join(@Validated UserAccount user, BindingResult bindingResult) {
		Map<String, Object> body = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = bindingResult.getFieldErrors()
					.stream()
		            .collect(Collectors.toMap(fe -> fe.getField(), fe -> fe.getDefaultMessage()));
			body.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
		} else {
			userService.createUser(user);
		}
		return ResponseEntity.status(status).body(body);
	}
}
