package com.example.demo.reposetories;

import com.example.demo.entities.Post;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setContent(rs.getString("content"));

        Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
        if (createdAtTimestamp != null) {
            post.setCreatedAt(createdAtTimestamp.toLocalDateTime());
        }

        post.setUserId(rs.getInt("user_id"));
        return post;
    }
}