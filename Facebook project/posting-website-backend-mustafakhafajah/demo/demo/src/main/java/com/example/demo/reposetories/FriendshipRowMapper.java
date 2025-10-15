package com.example.demo.reposetories;

import com.example.demo.entities.Friendship;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendshipRowMapper implements RowMapper<Friendship> {

    @Override
    public Friendship mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friendship friendship = new Friendship();
        friendship.setId(rs.getInt("id"));
        friendship.setUserOneId(rs.getInt("user_one_id"));
        friendship.setUserTwoId(rs.getInt("user_two_id"));
        friendship.setStatus(rs.getString("status"));
        return friendship;
    }
}