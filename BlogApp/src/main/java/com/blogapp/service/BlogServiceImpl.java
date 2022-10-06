package com.blogapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.exception.BlogException;
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
		List<Blog> blogs = new ArrayList<>();
		blogs = blogDao.findAll();
		if(blogs==null) {
			throw new BlogException("No blogs found");
		}
		return blogs;
	}

	@Override
	public Blog updateBlogById(Blog blog, Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found with the blogId "+blogId+"");
		}
		Blog databaseBlog = opt.get();
		databaseBlog.setBody(blog.getBody());
		databaseBlog.setTitle(blog.getTitle());
		
		return blogDao.save(databaseBlog);
	}

	@Override
	public Blog getBlogById(Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found with the blogId "+blogId+"");
		}
		return opt.get();
	}

	@Override
	public String deleteBlogById(Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found with the blogId "+blogId+"");
		}
		blogDao.delete(opt.get());
		return "deleted successfully";
	}

	@Override
	public List<Blog> getBlogsPaginatingAndSorting(Integer pageSize, Integer pageNo, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
