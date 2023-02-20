package com.newseoul.bookfinder.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//		.authorizeHttpRequests()
//		.antMatchers("/book/**").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
