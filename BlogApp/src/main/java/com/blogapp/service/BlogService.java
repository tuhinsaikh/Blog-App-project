package com.blogapp.service;

import java.util.List;

import com.blogapp.model.Blog;

public interface BlogService {
	
	public Blog createBlog(Blog blog);
	public List<Blog> getAllBlog();
	public Blog updateBlogById(Blog blog,Integer blogId);
	public Blog getBlogById(Integer blogId);
	public String deleteBlogById(Integer blogId);
	public List<Blog> getBlogsPaginatingAndSorting(Integer pageSize, Integer pageNo, String sortBy, String sortDirection);
}
