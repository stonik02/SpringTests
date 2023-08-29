package com.example.learning.DAO.dao;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

import com.example.learning.DAO.interfaces.UserDAOIntefrace;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO implements UserDAOIntefrace<User, Long>{


    private final JdbcTemplate jdbcTemplate;
    private final RoleDAO roleDAO;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, RoleDAO roleDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleDAO = roleDAO;
    }

    @Override
    public Long getMaxId() throws SQLException {
        Long id;
        id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM users", Long.class);

        return id == null? 1 : id;
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
        for (User user : users) {
            List<Role> roles = roleDAO.findAllRolesByUserId(user.getId());
            user.setRoles(roles);
        }
       return users;
    }

    @Override
    public User findById(String id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new Object[]{Long.parseLong(id)},new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Long save(User user) throws SQLException {
        Long id = getMaxId();
        id+=1;
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?)", id, user.getEmail(), null, false, false, user.getName(), user.getPassword() ,null);
        setRoles(id, roleDAO.getRoleByName("ROLE_USER"));
        return id;
    }

    public void update(User user) {
      jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?", user.getName(), user.getEmail(), user.getId());
      System.out.println("User: " + user.getName() + " email: " + user.getEmail() + " is_active: " + user.isIs_active() + " is_staff: " + user.isIs_staff() + " UPDATED");
    }

    @Override
    public void delete(String id) throws SQLException {
        int userID = Integer.parseInt(id);
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", userID);
        System.out.println("Delete user by id = " + id + "successful");
    }

    @Override
    public void setRoles(Long userId, List<Role> roles) {
        // Для каждой роли ассоциируем её с пользователем
        for (Role role : roles) {
            Integer roleId = role.getId();
            try {
                jdbcTemplate.update(
                        "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)",
                        userId,
                        2
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
