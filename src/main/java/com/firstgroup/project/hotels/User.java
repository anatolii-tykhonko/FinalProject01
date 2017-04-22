package com.firstgroup.project.hotels;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class User {
    private long userID;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(long userID, String name, String surname, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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
