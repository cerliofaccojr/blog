-- Create BlogPost table
CREATE TABLE blog_post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);

-- Create Comment table
CREATE TABLE comment (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    blog_post_id BIGINT NOT NULL,
    CONSTRAINT fk_blog_post FOREIGN KEY (blog_post_id)
    REFERENCES blog_post (id) ON DELETE CASCADE
);
