package com.newseoul.bookfinder.auth.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newseoul.bookfinder.auth.model.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, String> {
	long countBy();
	
	@Query("SELECT u from UserAccount u WHERE (u.username like %:keyword% or u.email like %:keyword% or u.name like %:keyword% or phoneNumber like %:keyword%) order by u.username asc")
	List<UserAccount> findByUsernameContainingOrEmailContainingOrNameContainingOrderByUsernameAsc(@Param("keyword") String keyword, Pageable pageable);
	boolean existsByEmail(String email);
}
