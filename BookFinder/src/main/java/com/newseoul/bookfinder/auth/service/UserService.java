package com.newseoul.bookfinder.auth.service;

import java.util.List;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.model.BookRental;

public interface UserService {
	List<UserAccount> getUserList(String keyword, int page);
	UserAccount createUser(UserAccount user);
	long getUserCount();
	UserAccount getUser(String username);
	List<BookRental> getBookRentalList(String username);
	
}
