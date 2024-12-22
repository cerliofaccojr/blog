package com.prosigliere.blog.dto;

import java.util.List;

public record BlogPostResponse(Long id, String title, String content, List<CommentResponse> comments) {}
