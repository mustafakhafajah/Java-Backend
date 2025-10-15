package com.example.demo.reposetories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.entities.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CommentRepository {
    private final JdbcTemplate jdbcTemplate;
    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<Comment> findById(Integer id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        try {
            Comment comment = jdbcTemplate.queryForObject(sql, new CommentRowMapper(), id);
            return Optional.of(comment);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public List<Comment> findAll() {
        String sql = "SELECT * FROM comments ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new CommentRowMapper());
    }
    public Comment save(Comment comment){
        int id = findMaxId() + 1;
        comment.setId(id);

        String sql = "INSERT INTO comments (id, content, created_at, user_id, post_id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUserId(),
                comment.getPostId()
        );

        return comment;
    }
    public int findMaxId() {
        String sql = "SELECT MAX(id) FROM comments";
        try {
            Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
            return maxId != null ? maxId.intValue() : 0;
        } catch (org.springframework.dao.DataAccessException e) {
            return 0;
        }
    }
    public int updateContent(int id, String content) {
        String sql = "UPDATE comments SET content = ? WHERE id = ?";
        return jdbcTemplate.update(sql, content, id);

    }
    public int deleteById(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }


}
