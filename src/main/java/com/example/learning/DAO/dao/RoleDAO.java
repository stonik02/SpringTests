package com.example.learning.DAO.dao;

import com.example.learning.DAO.interfaces.RoleDAOInterface;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDAO implements RoleDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    public RoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(String role) {
        Integer id = getMaxId();
        System.out.println(role);
        id += 1;
        jdbcTemplate.update("INSERT INTO roles VALUES(?,?)", id, role);
    }

    @Override
    public List<Role> findAllRoles() {
        String roleQuery = "SELECT * FROM roles";
        List<Role> roles = jdbcTemplate.query(roleQuery, new BeanPropertyRowMapper<>(Role.class));

        for (Role role : roles) {
            List<User> users = findAllUsersByRoleId(role.getId());
            role.setUsers(users);
        }
        return roles;
    }

    public List<User> findAllUsersByRoleId(int id) {
        return jdbcTemplate.query("SELECT u.* FROM users u INNER JOIN users_roles ur ON ur.user_id = u.id AND ur.role_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
    }

    public List<Role> findAllRolesByUserId(Long id) {
        return jdbcTemplate.query("SELECT r.id, r.role FROM roles r INNER JOIN users_roles ur ON ur.role_id = r.id AND ur.user_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Role.class));
    }
    @Override
    public List<Role> getRoleByName(String name) {

        return jdbcTemplate.query("SELECT * FROM roles WHERE role = ?", new Object[]{name}, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public Integer getMaxId() {
        Integer id = 1;
        id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM roles", Integer.class);
        System.out.println(id);
        return id == null ? 1 : id;
    }

    public List<Role> getRolesByIds(List<Integer> roleIds) {
        String sql = "SELECT * FROM roles WHERE id IN (:roleIds)";
        Object[] params = roleIds.toArray();

        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Role.class));
    }

    public List<Role> getRolesByName(String roleName) {
        return jdbcTemplate.query("SELECT * FROM roles WHERE role = ?", new Object[]{roleName}, new BeanPropertyRowMapper<>(Role.class));
    }
}
