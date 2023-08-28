package com.example.learning.controllers;

import java.sql.SQLException;
import java.util.List;

import com.example.learning.dto.UserDto;
import com.example.learning.models.Users;
import com.example.learning.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getall() throws SQLException {
        List<Users> users = userService.findAll();
        if (users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") String id) {
        try {
            Users user = userService.findUser(id);
            if (user.getId() != null) {
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
            System.out.println(req.toString());
            Users user = new Users(req.name(), req.email());
            userService.saveUser(user);
            return new ResponseEntity<>(req, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto req, @PathVariable("id") String id){
        try {
            Users user = new Users(req.name(), req.email());
            user.setId(Integer.parseInt(id));
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





}
