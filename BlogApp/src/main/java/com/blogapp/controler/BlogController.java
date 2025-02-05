package com.blogapp.controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.model.Blog;
import com.blogapp.service.BlogService;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BlogController {

    @Value("${upload_dir}")
    private String UPLOAD_DIR;

    @Autowired
    private BlogService blogService;
    
    @PostMapping("/posts")
    public ResponseEntity<Blog> createBlogController(@RequestBody Blog blog){
        String sanitizedBody = blog.getBody().replace("\n", "");
        blog.setBody(sanitizedBody);
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
    
    @GetMapping("/posts/")
    public ResponseEntity<List<Blog>> getBlogsPaginatingAndSortingController(
    		@RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
    		@RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
    		@RequestParam(value = "sortBy",defaultValue = "blogId",required = false) String sortBy,
    		@RequestParam(value = "sortDirection",defaultValue = "ASC",required = false) String sortDirection
    		){
    	List<Blog> blogs = new ArrayList<>();
    	blogs = blogService.getBlogsPaginatingAndSorting(pageSize, pageNo, sortBy, sortDirection);
    	return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @PostMapping("/blog/blobUpload")
    private ResponseEntity<String> blobUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (!file.getContentType().startsWith("image/")) {
                return new ResponseEntity<>("Only images are allowed!", HttpStatus.BAD_REQUEST);
            }

            // Define the path where the file will be stored
            Path uploadPath = Paths.get("D:/Study/Blog-Backend/BlogApp/uploadDir");
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(fileName);

            // Save the file
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Return the relative URL to the uploaded image
            String fileUrl = "/uploads/" + fileName;  // Relative path for serving the file

            return ResponseEntity.status(HttpStatus.OK).body(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }


    @DeleteMapping("/blog/blobDelete")
    public ResponseEntity<String> deleteBlob(@RequestParam("fileName") String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            File file = filePath.toFile();

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found!");
            }

            if (file.delete()) {
                return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image: " + e.getMessage());
        }
    }

}
