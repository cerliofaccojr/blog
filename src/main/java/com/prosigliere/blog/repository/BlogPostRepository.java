package com.prosigliere.blog.repository;


import com.prosigliere.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {}
