package com.tejaswi.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejaswi.blog.entity.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
