package com.example.demo.reposetories;

import com.example.demo.entities.Comment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setContent(rs.getString("content"));

        if (rs.getTimestamp("created_at") != null) {
            comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        comment.setUserId(rs.getInt("user_id"));
        comment.setPostId(rs.getInt("post_id"));

        return comment;
    }
}