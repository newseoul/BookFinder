package com.newseoul.bookfinder.auth.service;

import java.util.List;

import com.newseoul.bookfinder.auth.model.UserAccount;

public interface UserService {
	List<UserAccount> getUserList();
}
