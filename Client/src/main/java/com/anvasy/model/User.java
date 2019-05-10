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

    public User(String username, String name, String surname, String regType) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.regType = regType;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) { this.password = password; }

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

    public void setSurname(String surname) { this.surname = surname; }

    public void setRegType(String regType) { this.regType = regType; }

    public void setUsername(String username) { this.username = username; }

    public void setRole(String role) { this.role = role; }
}
