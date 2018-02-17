package com.example.rathana.datastoragedemo.entity;

import java.io.Serializable;

/**
 * Created by RATHANA on 2/17/2018.
 */

public class User implements Serializable{
    private String name;
    private String gender;
    private String email;
    private String address;
    public User(){}
    public User(String name, String gender, String email, String address) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
