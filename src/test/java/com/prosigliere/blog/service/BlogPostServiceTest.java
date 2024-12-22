package com.prosigliere.blog.service;

import com.prosigliere.blog.dto.BlogPostResponse;
import com.prosigliere.blog.dto.CommentResponse;
import com.prosigliere.blog.dto.CreateBlogPostRequest;
import com.prosigliere.blog.exception.ResourceNotFoundException;
import com.prosigliere.blog.model.BlogPost;
import com.prosigliere.blog.model.Comment;
import com.prosigliere.blog.repository.BlogPostRepository;
import com.prosigliere.blog.repository.CommentRepository;
import com.prosigliere.blog.service.impl.BlogPostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class BlogPostServiceTest {

    private final BlogPostRepository blogPostRepository = Mockito.mock(BlogPostRepository.class);
    private final CommentRepository commentRepository = Mockito.mock(CommentRepository.class);
    private final BlogPostService blogPostService = new BlogPostServiceImpl(blogPostRepository, commentRepository);

    @Test
    void getPostById_shouldReturnBlogPostResponse() {
        Long postId = 1L;
        BlogPost mockPost = new BlogPost();
        mockPost.setId(postId);
        mockPost.setTitle("Test Title");
        mockPost.setContent("Test Content");
        when(blogPostRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        BlogPostResponse response = blogPostService.getPostById(postId);

        assertEquals("Test Title", response.title());
        assertEquals("Test Content", response.content());
    }

    @Test
    void getPostById_shouldThrowExceptionIfNotFound() {
        Long postId = 1L;
        when(blogPostRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> blogPostService.getPostById(postId));
    }

    @Test
    void createPost_shouldSaveAndReturnBlogPostResponse() {
        CreateBlogPostRequest request = new CreateBlogPostRequest("New Title", "New Content");
        BlogPost mockPost = new BlogPost();
        mockPost.setId(1L);
        mockPost.setTitle("New Title");
        mockPost.setContent("New Content");
        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(mockPost);

        BlogPostResponse response = blogPostService.createPost(request);

        assertEquals("New Title", response.title());
        assertEquals("New Content", response.content());
    }

    @Test
    void addCommentToPost_shouldSaveAndReturnCommentResponse() {
        Long postId = 1L;
        BlogPost mockPost = new BlogPost();
        mockPost.setId(postId);
        when(blogPostRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        Comment mockComment = new Comment();
        mockComment.setId(1L);
        mockComment.setText("New Comment");
        when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);

        CommentResponse response = blogPostService.addCommentToPost(postId, new CommentResponse(null, "New Comment"));

        assertEquals("New Comment", response.text());
    }
}
