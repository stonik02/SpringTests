package com.example.learning.controllers;


import com.example.learning.dto.RoleDto;
import com.example.learning.models.Role;
import com.example.learning.models.User;
import com.example.learning.services.RoleService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody RoleDto req) {
        try {
            roleService.create(req.role());
            return new ResponseEntity<>(true, HttpStatusCode.valueOf(201));
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(228));
        }
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        try {
            return new ResponseEntity<>(roleService.findAll(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(228));
        }
    }
}
