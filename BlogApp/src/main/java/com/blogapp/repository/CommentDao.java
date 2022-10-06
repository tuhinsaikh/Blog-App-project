package com.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer>{

}
