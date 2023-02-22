package com.newseoul.bookfinder.auth.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.auth.model.Role;
import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.RoleRepository;
import com.newseoul.bookfinder.auth.repository.UserRepository;
import com.newseoul.bookfinder.model.Book;
import com.newseoul.bookfinder.model.BookRental;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserAccount> getUserList(String keyword, int page) {
		List<UserAccount> list = userRepository.findByUsernameContainingOrEmailContainingOrNameContainingOrderByUsernameAsc(keyword, PageRequest.of(page - 1, 10));
		// 패스워드 마스킹 처리
		return list.stream().map(u -> {u.setPassword("*******"); return u;}).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public UserAccount createUser(UserAccount user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPhoneNumber(user.getPhoneNumber().replaceAll("[^\\d]", ""));
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.addRoles(Arrays.asList(userRole));
		UserAccount newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public long getUserCount() {
		return userRepository.countBy();
	}

	@Override
	public UserAccount getUser(String username) {
		UserAccount user = userRepository.findById(username).orElse(null);
		user.setPassword("********");
		return user;
	}

	@Override
	public List<Map<String, String>> getBookRentalList(String username) {
		return this.getUser(username).getBookRentalList().stream().map(bookRental -> {
			Map<String, String> map = new HashMap<>();
			Book book = bookRental.getBook();
			map.put("bookId", Integer.toString(book.getBookId()));
			map.put("bookName", book.getBookName());
			map.put("rentalDate", bookRental.getRentalDate());
			map.put("returnDueDate", bookRental.getReturnDueDate());
			map.put("returnDate", bookRental.getReturnDate());
			map.put("rentalStatus", bookRental.getRentalStatus());
			return map;
		}).collect(Collectors.toList());
		
	}


}
