package com.blogapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.model.Blog;
import com.blogapp.repository.BlogDao;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogDao blogDao;

	@Override
	public Blog createBlog(Blog blog) {
		return blogDao.save(blog);
	}

	@Override
	public List<Blog> getAllBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog updateBlogById(Blog blog, Integer blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog getBlogById(Integer blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBlogById(Integer blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> getBlogsPaginatingAndSorting(Integer pageSize, Integer pageNo, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
