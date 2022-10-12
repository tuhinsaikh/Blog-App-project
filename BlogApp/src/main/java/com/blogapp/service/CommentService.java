package com.blogapp.service;

import java.util.List;

import com.blogapp.model.Blog;
import com.blogapp.model.Comment;

public interface CommentService {
	
	public Blog createCommentInABlog(Comment comment, Integer blogId);

	public Comment updateCommentInABlogById(Comment comment, Integer blogId, Integer commentId);
	
	public List<Comment> getAllCommentByPostId(Integer blogId);
	
	public Comment getCommentByCommentIdAndPostId(Integer blogId, Integer commentId);
	
	public String deleteCommentByCommentIdAndPostId(Integer blogId, Integer commentId);
}