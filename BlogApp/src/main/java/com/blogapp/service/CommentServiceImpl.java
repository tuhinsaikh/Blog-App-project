package com.blogapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.exception.BlogException;
import com.blogapp.exception.CommentException;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.repository.BlogDao;
import com.blogapp.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private BlogDao blogDao;;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public Blog createCommentInABlog(Comment comment, Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		
		if(opt.isEmpty()) {
			throw new BlogException("blog not found");
		}
		Blog blog = opt.get();
		List<Comment> blogComments = blog.getComments();
		blogComments.add(comment);
		Blog blg =  blogDao.save(blog);
		return blg;
	}

	@Override
	public Comment updateCommentInABlogById(Comment comment, Integer blogId, Integer commentId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found");
		}
		
		Optional<Comment> optCmnt = commentDao.findById(commentId);
		if(optCmnt.isEmpty()) {
			throw new CommentException("comment not found");
		}
		Comment dataComment = optCmnt.get();
		if(comment.getCommentBody() == null || comment.getCommentBody().isEmpty()){
			throw new CommentException("comment body empty or null");
		}else{
			dataComment.setCommentBody(comment.getCommentBody());
		}
		//dataComment.setName(comment.getName());
		return commentDao.save(dataComment);
	}

	@Override
	public List<Comment> getAllCommentByPostId(Integer blogId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found");
		}
		Blog blog = opt.get();
		List<Comment> comments = blog.getComments();
		return comments;
	}

	@Override
	public Comment getCommentByCommentIdAndPostId(Integer blogId, Integer commentId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found");
		}
		Optional<Comment> optCmnt = commentDao.findById(commentId);
		if(optCmnt.isEmpty()) {
			throw new CommentException("comment not found");
		}
		return optCmnt.get();
	}

	@Override
	public String deleteCommentByCommentIdAndPostId(Integer blogId, Integer commentId) {
		Optional<Blog> opt = blogDao.findById(blogId);
		if(opt.isEmpty()) {
			throw new BlogException("blog not found");
		}
		Optional<Comment> optCmnt = commentDao.findById(commentId);
		if(optCmnt.isEmpty()) {
			throw new CommentException("comment not found");
		}
		Blog blog = opt.get();
		List<Comment> comments = blog.getComments();
		Comment dataComment = optCmnt.get();
		comments.remove(dataComment);
		blog.setComments(comments);
		blogDao.save(blog);
		commentDao.delete(dataComment);
		return "deleted successfully";
	}

}
