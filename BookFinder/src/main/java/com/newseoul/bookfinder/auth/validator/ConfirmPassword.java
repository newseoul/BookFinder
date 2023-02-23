package com.newseoul.bookfinder.auth.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.newseoul.bookfinder.auth.model.UserAccount;

@Component
public class ConfirmPassword implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccount.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAccount user = (UserAccount) target;
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.fail", "비밀번호를 다시 입력해주세요.");
		}
	}

}
