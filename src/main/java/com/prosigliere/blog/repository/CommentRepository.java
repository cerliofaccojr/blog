package com.prosigliere.blog.repository;

import com.prosigliere.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
