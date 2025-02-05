package com.blogapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapp.exception.BlogException;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.repository.BlogDao;
import com.blogapp.repository.CommentDao;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public Blog createBlog(Blog blog) {
		return blogDao.save(blog);
	}

	@Override
	public List<Blog> getAllBlog() {
		List<Blog> blogs = new ArrayList<>();
		blogs = blogDao.findAll();
		if(blogs.size()<=0) {
			throw new BlogException("No blogs found");
		}
		return blogs;
	}

	@Override
	public Blog updateBlogById(Blog blog, Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found with the blogId "+blogId);
		}
		Blog databaseBlog = opt.get();
		if(blog.getBody()!=null && !blog.getBody().isEmpty()) {
			String sanitizedBody = blog.getBody().replace("\n", "");
			databaseBlog.setBody(sanitizedBody);
		}
		if(blog.getTitle()!=null && !blog.getTitle().isEmpty()){
			databaseBlog.setTitle(blog.getTitle());
		}
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
		Blog blog = opt.get();
		List<Comment> blogComments = blog.getComments();
		for(Comment comments: blogComments) {
			commentDao.delete(comments);
		}
		blogDao.delete(opt.get());
		return "deleted successfully";
	}

	@Override
	public List<Blog> getBlogsPaginatingAndSorting(Integer pageSize, Integer pageNo, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable =   PageRequest.of(pageNo, pageSize,sort);
		Page<Blog> blogs = blogDao.findAll(pageable);
		List<Blog> blogList = blogs.getContent();
		return blogList;
	}

}
