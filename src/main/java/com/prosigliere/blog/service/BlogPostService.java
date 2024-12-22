package com.prosigliere.blog.service;

import com.prosigliere.blog.dto.BlogPostResponse;
import com.prosigliere.blog.dto.CommentResponse;
import com.prosigliere.blog.dto.CreateBlogPostRequest;

import java.util.List;

public interface BlogPostService {
    List<BlogPostResponse> getAllPosts();
    BlogPostResponse getPostById(Long id);
    BlogPostResponse createPost(CreateBlogPostRequest request);
    CommentResponse addCommentToPost(Long postId, CommentResponse commentRequest);
    void deletePost(Long id);
}
