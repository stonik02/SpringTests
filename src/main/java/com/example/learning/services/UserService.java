package com.example.learning.services;

import com.example.learning.DAO.dao.UserDAO;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {


    private final UserDAO usersDao;



    public UserService(UserDAO usersDao){
        this.usersDao = usersDao;
    }

    public User findUser(String id) throws SQLException {
        return usersDao.findById(id);
    }

    public void saveUser(User user) throws SQLException {
        usersDao.save(user);
    }

    public void deleteUser(String id) throws SQLException {
        usersDao.delete(id);
    }

    public void updateUser(User user) throws SQLException {
        usersDao.update(user);
    }

    public List<User> findAll() throws SQLException {
        return usersDao.findAll();
    }

    public void setRoles(Long userId, List<Role> roles) {
        usersDao.setRoles(userId, roles);
    }






}
