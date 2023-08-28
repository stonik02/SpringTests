package com.example.learning.services;

import com.example.learning.DAO.dao.UserDAO;
import com.example.learning.models.Users;

import java.sql.SQLException;
import java.util.List;

public class UserService {


    private final UserDAO usersDao;



    public UserService(UserDAO usersDao){
        this.usersDao = usersDao;
    }

    public Users findUser(String id) throws SQLException {
        return usersDao.findById(id);
    }

    public void saveUser(Users user) throws SQLException {
        usersDao.save(user);
    }

    public void deleteUser(String id) throws SQLException {
        usersDao.delete(id);
    }

    public void updateUser(Users user) throws SQLException {
        usersDao.update(user);
    }

    public List<Users> findAll() throws SQLException {
        return usersDao.findAll();
    }




}
