package com.example.learning.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import com.example.learning.dto.SetRoleDto;
import com.example.learning.dto.UserDto;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import com.example.learning.services.RoleService;
import com.example.learning.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService, RoleService roleService1) {
        this.userService = userService;
        this.roleService = roleService1;
    }

    @GetMapping
    public ResponseEntity<List<User>> getall() throws SQLException {
        List<User> users = userService.findAll();
        if (users.get(0).getEmail() != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try {
            User user = userService.findUser(id);
            if (user.getEmail() != null && !Objects.equals(user.getEmail(), "")) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                System.out.println("dada");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createOne(@RequestBody UserDto req) throws SQLException{
        try {
            User user = new User(req.name(), req.email(), req.password());
            System.out.println("Posle User");
            userService.saveUser(user);
            return new ResponseEntity<>(req, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto req, @PathVariable("id") String id){
        try {
            User user = new User(req.name(), req.email(), req.password());
            user.setId(Long.parseLong(id));
            userService.updateUser(user);
            return new ResponseEntity<>(req, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> detele(@PathVariable("id") String id) {
        try {
            userService.deleteUser(id);
            return  new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/set_role")
    public ResponseEntity<Boolean> setRoles(@RequestBody SetRoleDto req) {
        try {
            List<Role> roles= roleService.findRoleByName(req.role());
            Long id = Long.valueOf(req.userID());
            userService.setRoles(id, roles);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }





}
