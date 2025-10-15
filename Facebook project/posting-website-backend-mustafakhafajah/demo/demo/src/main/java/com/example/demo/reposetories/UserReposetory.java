package com.example.demo.reposetories;

import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserReposetory {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserReposetory(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {
        int newId = findMaxId() + 1;
        user.setId(newId);
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail());

        return user;
    }

    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    public int findMaxId() {
        String sql = "SELECT MAX(id) FROM users";
        try {
            Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
            return maxId != null ? maxId.intValue() : 0;
        } catch (DataAccessException e) {
            return 0;
        }
    }

}