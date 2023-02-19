package com.newseoul.bookfinder.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserAccount> getUserList() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public UserAccount createUser(UserAccount user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
