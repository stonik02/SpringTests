package com.example.learning.DAO.interfaces;

import com.example.learning.dto.UserDto;
import com.example.learning.models.Users;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UserDAOIntefrace <T, String extends Serializable>{


    List<Users> findAll() throws SQLException;

    Users findById(java.lang.String id) throws SQLException;

    public void save(Users user) throws SQLException;

    public void update(T entity) throws SQLException;

    public void delete(java.lang.String id) throws SQLException;

    public int getMaxId() throws SQLException;

}
