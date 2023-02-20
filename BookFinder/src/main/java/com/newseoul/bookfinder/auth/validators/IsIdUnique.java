package com.newseoul.bookfinder.auth.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.UserRepository;

@Component
public class IsIdUnique implements Validator {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccount.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAccount user = (UserAccount) target;
		if (userRepository.existsById(user.getUsername())) {
			errors.rejectValue("username", "username.exists", "이미 존재하는 아이디입니다.");
		}
	}

}
