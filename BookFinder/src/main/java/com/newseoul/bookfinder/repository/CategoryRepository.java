package com.newseoul.bookfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newseoul.bookfinder.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
