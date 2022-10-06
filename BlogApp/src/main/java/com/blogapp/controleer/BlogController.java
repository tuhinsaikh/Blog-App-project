package com.blogapp.controleer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.model.Blog;
import com.blogapp.service.BlogService;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    
    @PostMapping("/posts")
    public ResponseEntity<Blog> createBlogController(@RequestBody Blog blog){
    	return new ResponseEntity<Blog>(blogService.createBlog(blog), HttpStatus.CREATED);
    }
    
    @GetMapping("/posts")
    public ResponseEntity<List<Blog>> getAllBlogController(){
    	List<Blog> blogs = new ArrayList<>();
    	blogs = blogService.getAllBlog();
    	return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }
    
    @PutMapping("/posts/{id}")
    public ResponseEntity<Blog> updateBlogByIdController(@RequestBody Blog blog, @PathVariable("id") Integer blogId){
    	return new ResponseEntity<Blog>(blogService.updateBlogById(blog,blogId), HttpStatus.OK);
    }
    
    @GetMapping("/posts/{id}")
    public ResponseEntity<Blog> getBlogByIdController(@PathVariable("id") Integer blogId){
    	return new ResponseEntity<Blog>(blogService.getBlogById(blogId), HttpStatus.OK);
    }
    
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deleteBlogByIdController(@PathVariable("id") Integer blogId){
    	return new ResponseEntity<String>(blogService.deleteBlogById(blogId), HttpStatus.OK);
    }
}
