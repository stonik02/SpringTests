package com.example.learning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private Integer number;

    private boolean first_kryg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isFirst_kryg() {
        return first_kryg;
    }

    public void setFirst_kryg(boolean first_kryg) {
        this.first_kryg = first_kryg;
    }

    public Queue(String userId, Integer number) {
        this.userId = userId;
        this.number = number;
    }

    public Queue() {
    }
}
