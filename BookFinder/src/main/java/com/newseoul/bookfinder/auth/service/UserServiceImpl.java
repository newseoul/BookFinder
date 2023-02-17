package com.newseoul.bookfinder.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserAccount> getUserList() {
		return userRepository.findAll();
	}

}
