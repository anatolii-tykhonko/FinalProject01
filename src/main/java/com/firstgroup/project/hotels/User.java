package com.firstgroup.project.hotels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Room> roomList = new ArrayList<>();

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    @Override
    public String toString() {
        return  "Имя: " + name +
                ", фамилия: " + surname +
                ", email: " + email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
