package com.example.learning.DAO.interfaces;

import com.example.learning.models.Role;

import java.util.List;

public interface RoleDAOInterface <T, Integer>{
    public void create(String role);
    public List<Role> findAllRoles();
    public Integer getMaxId();
    public List<Role> getRoleByName(String name);
}
