package com.firstgroup.project.hotels;

import java.io.Serializable;
/**
 * Created by Sonikb on 22.04.2017.
 */
public class User implements Serializable{
    private long userID;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userID = idGenerator();
    }

    public int idGenerator() {
        return name.hashCode() + surname.hashCode() + email.hashCode() + password.hashCode() / 5;
    }

    public long getUserID() {
        return userID;
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
}
