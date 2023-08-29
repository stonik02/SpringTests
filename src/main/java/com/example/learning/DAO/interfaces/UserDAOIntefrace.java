package com.example.learning.DAO.interfaces;

import com.example.learning.models.Role;
import com.example.learning.models.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UserDAOIntefrace <T, String extends Serializable>{


    public List<User> findAll() throws SQLException;

    public User findById(java.lang.String id) throws SQLException;

    public Long save(User user) throws SQLException;

    public void update(T entity) throws SQLException;

    public void delete(java.lang.String id) throws SQLException;

    public Long getMaxId() throws SQLException;
    public void setRoles(Long userId, List<Role> roles);


}
