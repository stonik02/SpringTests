package com.example.learning.DAO.dao;

import java.sql.*;
import java.util.List;

import com.example.learning.DAO.interfaces.UserDAOIntefrace;
import com.example.learning.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO implements UserDAOIntefrace<Users, Long>{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public int getMaxId() throws SQLException {
        int id = 1;
        id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM Users", Integer.class);

        return id;
    }

    @Override
    public List<Users> findAll() {
       return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(Users.class));
    }

    @Override
    public Users findById(String id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE id = ?", new Object[]{id},new BeanPropertyRowMapper<>(Users.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Users user) throws SQLException {
        int id = getMaxId();
        id+=1;
        jdbcTemplate.update("INSERT INTO Users VALUES(?, ?, ?, ?, ?)", id, user.getEmail(), false, false, user.getName());

    }

    public void update(Users user) {

      jdbcTemplate.update("UPDATE Users SET name = ?, email = ? WHERE id = ?", user.getName(), user.getEmail(), user.getId());

      System.out.println("User: " + user.getName() + " email: " + user.getEmail() + " is_active: " + user.isIs_active() + " is_staff: " + user.isIs_staff() + " UPDATED");
    }

    @Override
    public void delete(String id) throws SQLException {
        int userID = Integer.parseInt(id);
        jdbcTemplate.update("DELETE FROM Users WHERE id = ?", userID);
        System.out.println("Delete user by id = " + id + "successful");
    }

}
