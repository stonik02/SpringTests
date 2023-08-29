package com.example.learning.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    private Date start_active;

    private Date end_active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",  // Измененное имя таблицы связи
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles =new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    private boolean is_staff;

    @Column(columnDefinition = "boolean default false")
    private boolean is_active;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStart_active() {
        return start_active;
    }

    public void setStart_active(Date start_active) {
        this.start_active = start_active;
    }

    public Date getEnd_active() {
        return end_active;
    }

    public void setEnd_active(Date end_active) {
        this.end_active = end_active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
