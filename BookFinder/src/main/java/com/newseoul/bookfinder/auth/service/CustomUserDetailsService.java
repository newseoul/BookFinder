package com.newseoul.bookfinder.auth.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newseoul.bookfinder.auth.model.UserAccount;
import com.newseoul.bookfinder.auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userRepository.findById(username).orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException("no user found:" + username);
		}
		
		// 권한 값을 사용자 데이터에 추가
		List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
													.map(role -> new SimpleGrantedAuthority(role.getName()))
													.collect(Collectors.toList());
		
		return new User(username, user.getPassword(), grantedAuthorities);
	}

}
