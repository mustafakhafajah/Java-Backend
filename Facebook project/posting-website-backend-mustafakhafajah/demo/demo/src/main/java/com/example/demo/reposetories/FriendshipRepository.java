package com.example.demo.reposetories;

import com.example.demo.entities.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FriendshipRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendshipRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Friendship save(Friendship friendship) {
        String sql = "INSERT INTO friendships (user_one_id, user_two_id, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, friendship.getUserOneId(), friendship.getUserTwoId(), friendship.getStatus());
        return friendship;
    }

    public List<Friendship> findIncomingRequests(Integer userId) {
        String sql = "SELECT * FROM friendships WHERE user_two_id = ? AND status = 'PENDING'";
        return jdbcTemplate.query(sql, new FriendshipRowMapper(), userId);
    }
    public int acceptRequest(Integer userOneId, Integer userTwoId) {
        String sql = "UPDATE friendships SET status = 'ACCEPTED' WHERE user_one_id = ? AND user_two_id = ? AND status = 'PENDING'";
        return jdbcTemplate.update(sql, userOneId, userTwoId);
    }
    public List<Friendship> findFriends(Integer userId) {
        String sql = "SELECT * FROM friendships WHERE (user_one_id = ? OR user_two_id = ?) AND status = 'ACCEPTED'";
        return jdbcTemplate.query(sql, new FriendshipRowMapper(), userId, userId);
    }

    public int deleteFriendship(Integer userOneId, Integer userTwoId) {
        String sql = "DELETE FROM friendships WHERE (user_one_id = ? AND user_two_id = ?) OR (user_one_id = ? AND user_two_id = ?)";
        return jdbcTemplate.update(sql, userOneId, userTwoId, userTwoId, userOneId);
    }
}