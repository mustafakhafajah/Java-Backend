package com.example.demo.reposetories;

import com.example.demo.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Post save(Post post) {
        int newId = findMaxId() + 1;
        post.setId(newId);

        String sql = "INSERT INTO posts (id, content, user_id, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getId(), post.getContent(), post.getUserId(), post.getCreatedAt());

        return post;
    }

    public List<Post> findAll() {
        String sql = "SELECT * FROM posts ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new PostRowMapper());
    }

    public Optional<Post> findById(int id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            Post post = jdbcTemplate.queryForObject(sql, new PostRowMapper(), id);
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateContent(int id, String content) {
        String sql = "UPDATE posts SET content = ? WHERE id = ?";
        return jdbcTemplate.update(sql, content, id);
    }
    public int findMaxId() {
        String sql = "SELECT MAX(id) FROM posts";
        try {
            Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
            return maxId != null ? maxId.intValue() : 0;
        } catch (org.springframework.dao.DataAccessException e) {
            return 0;
        }
    }

}