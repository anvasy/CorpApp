package com.anvasy.model;

import javax.persistence.Entity;

@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String role;
    private String regType;

    public User() { }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getRegType() {
        return regType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
}
