package com.newseoul.bookfinder.auth.service;

import java.util.List;
import java.util.Map;

import com.newseoul.bookfinder.auth.model.UserAccount;

public interface UserService {
	List<UserAccount> getUserList(String keyword, int page);
	UserAccount createUser(UserAccount user);
	long getUserCount();
	UserAccount getUser(String username);
	List<Map<String, String>> getBookRentalList(String username);
	
}
