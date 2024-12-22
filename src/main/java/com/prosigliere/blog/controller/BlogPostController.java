package com.prosigliere.blog.controller;

import com.prosigliere.blog.dto.BlogPostResponse;
import com.prosigliere.blog.dto.CommentResponse;
import com.prosigliere.blog.dto.CreateBlogPostRequest;
import com.prosigliere.blog.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public ResponseEntity<List<BlogPostResponse>> getAllPosts() {
        List<BlogPostResponse> posts = blogPostService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostResponse> getPostById(@PathVariable Long id) {
        BlogPostResponse post = blogPostService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addCommentToPost(@PathVariable Long id, @RequestBody CommentResponse commentRequest) {
        CommentResponse commentResponse = blogPostService.addCommentToPost(id, commentRequest);
        return ResponseEntity.status(201).body(commentResponse);
    }

    @PostMapping
    public ResponseEntity<BlogPostResponse> createPost(@RequestBody CreateBlogPostRequest request) {
        BlogPostResponse response = blogPostService.createPost(request);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
    }
}

