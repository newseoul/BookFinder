package com.newseoul.bookfinder.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String role);
}
