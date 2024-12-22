package com.prosigliere.blog.service.impl;

import com.prosigliere.blog.dto.BlogPostResponse;
import com.prosigliere.blog.dto.CommentResponse;
import com.prosigliere.blog.dto.CreateBlogPostRequest;
import com.prosigliere.blog.exception.ResourceNotFoundException;
import com.prosigliere.blog.model.BlogPost;
import com.prosigliere.blog.model.Comment;
import com.prosigliere.blog.repository.BlogPostRepository;
import com.prosigliere.blog.repository.CommentRepository;
import com.prosigliere.blog.service.BlogPostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private static final String POST_NOT_FOUND_MESSAGE = "Post with ID %d not found";

    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, CommentRepository commentRepository) {
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<BlogPostResponse> getAllPosts() {
        return blogPostRepository.findAll().stream()
                .map(this::convertToBlogPostResponse)
                .toList();
    }

    @Override
    public BlogPostResponse getPostById(Long id) {
        BlogPost post = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, id)));
        return convertToBlogPostResponse(post);
    }

    @Override
    public BlogPostResponse createPost(CreateBlogPostRequest request) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(request.title());
        blogPost.setContent(request.content());

        BlogPost savedPost = blogPostRepository.save(blogPost);
        return convertToBlogPostResponse(savedPost);
    }

    @Override
    public CommentResponse addCommentToPost(Long postId, CommentResponse commentRequest) {
        BlogPost post = blogPostRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, postId)));

        Comment comment = new Comment();
        comment.setText(commentRequest.text());
        comment.setBlogPost(post);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(savedComment.getId(), savedComment.getText());
    }

    private BlogPostResponse convertToBlogPostResponse(BlogPost blogPost) {
        List<CommentResponse> commentResponses = blogPost.getComments().stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getText()))
                .toList();
        return new BlogPostResponse(blogPost.getId(), blogPost.getTitle(), blogPost.getContent(), commentResponses);
    }

    @Override
    public void deletePost(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, id)));
        blogPostRepository.delete(blogPost);
    }
}
