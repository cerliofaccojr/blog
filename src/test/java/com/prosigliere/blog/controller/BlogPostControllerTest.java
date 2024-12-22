package com.prosigliere.blog.controller;

import com.prosigliere.blog.dto.BlogPostResponse;
import com.prosigliere.blog.dto.CommentResponse;
import com.prosigliere.blog.dto.CreateBlogPostRequest;
import com.prosigliere.blog.service.BlogPostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BlogPostControllerTest {

    private final BlogPostService blogPostService = Mockito.mock(BlogPostService.class);
    private final BlogPostController blogPostController = new BlogPostController(blogPostService);

    @Test
    void getAllPosts_shouldReturnListOfBlogPosts() {
        List<BlogPostResponse> mockPosts = List.of(
                new BlogPostResponse(1L, "Title 1", "Content 1", List.of()),
                new BlogPostResponse(2L, "Title 2", "Content 2", List.of())
        );
        when(blogPostService.getAllPosts()).thenReturn(mockPosts);

        ResponseEntity<List<BlogPostResponse>> response = blogPostController.getAllPosts();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void createPost_shouldReturnCreatedBlogPost() {
        CreateBlogPostRequest request = new CreateBlogPostRequest("Title", "Content");
        BlogPostResponse mockResponse = new BlogPostResponse(1L, "Title", "Content", List.of());
        when(blogPostService.createPost(request)).thenReturn(mockResponse);

        ResponseEntity<BlogPostResponse> response = blogPostController.createPost(request);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Title", response.getBody().title());
    }

    @Test
    void addCommentToPost_shouldReturnCreatedComment() {
        Long postId = 1L;
        CommentResponse commentRequest = new CommentResponse(null, "New Comment");
        CommentResponse mockResponse = new CommentResponse(1L, "New Comment");
        when(blogPostService.addCommentToPost(postId, commentRequest)).thenReturn(mockResponse);

        ResponseEntity<CommentResponse> response = blogPostController.addCommentToPost(postId, commentRequest);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("New Comment", Objects.requireNonNull(response.getBody()).text());
    }
}
