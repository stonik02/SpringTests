package com.example.learning.DAO.dao;

import com.example.learning.models.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Users user = new Users();

        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setIs_active(resultSet.getBoolean("is_active"));
        user.setIs_staff(resultSet.getBoolean("is_staff"));
        user.setId(resultSet.getInt("id"));

        return user;
    }
}
