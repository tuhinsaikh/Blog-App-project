package com.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Blog;

@Repository
public interface BlogDao extends JpaRepository<Blog, Integer>{

}
