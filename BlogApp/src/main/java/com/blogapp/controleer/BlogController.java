package com.blogapp.controleer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
