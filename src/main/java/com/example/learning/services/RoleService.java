package com.example.learning.services;

import com.example.learning.DAO.dao.RoleDAO;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleDAO roleDAO;

    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void create(String role) {
        this.roleDAO.create(role);
    }

    public List<Role> findAll() {
        return roleDAO.findAllRoles();
    }

    public List<Role> findBuIds(List<Integer> id) {
        return roleDAO.getRolesByIds(id);
    }

    public List<User> findUsersByRoleId(int id) {
        return roleDAO.findAllUsersByRoleId(id);
    }

    public List<Role> findRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
