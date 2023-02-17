package com.newseoul.bookfinder.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.auth.model.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, String> {

}
