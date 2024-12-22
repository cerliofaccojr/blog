package com.prosigliere.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Comment text is required")
    private String text;

    @ManyToOne
    @JoinColumn(name = "blog_post_id", nullable = false)
    private BlogPost blogPost;
}
