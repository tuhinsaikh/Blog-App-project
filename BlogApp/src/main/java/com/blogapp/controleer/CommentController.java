package com.blogapp.controleer;

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
import com.blogapp.model.Comment;
import com.blogapp.service.BlogService;
import com.blogapp.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{blogId}/comments")
	public ResponseEntity<Blog> createCommentInABlogController(@RequestBody Comment comment, @PathVariable("blogId") Integer blogId){
		return new ResponseEntity<Blog>(commentService.createCommentInABlog(comment, blogId),HttpStatus.CREATED);
	}

	@PutMapping("/posts/{blogId}/comments/{commentId}")
	public ResponseEntity<Comment> updateCommentInABlogByIdController(@RequestBody Comment comment, @PathVariable("blogId") Integer blogId, @PathVariable("commentId") Integer commentId){
		return new ResponseEntity<Comment>(commentService.updateCommentInABlogById(comment, blogId, commentId),HttpStatus.OK);
	}
	
	@GetMapping("/posts/{blogId}/comments")
	public ResponseEntity<List<Comment>> getAllCommentByPostIdController(@PathVariable("blogId") Integer blogId){
		List<Comment> comments = commentService.getAllCommentByPostId(blogId);
		return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{blogId}/comments/{commentId}")
	public ResponseEntity<Comment> getCommentByCommentIdAndPostIdController(@PathVariable("blogId") Integer blogId, @PathVariable("commentId") Integer commentId){
		Comment comment = commentService.getCommentByCommentIdAndPostId(blogId, commentId);
		return new ResponseEntity<Comment>(comment,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{blogId}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentByCommentIdAndPostIdController(@PathVariable("blogId") Integer blogId, @PathVariable("commentId") Integer commentId){
		String comment = commentService.deleteCommentByCommentIdAndPostId(blogId, commentId);
		return new ResponseEntity<String>(comment,HttpStatus.OK);
	}
}
