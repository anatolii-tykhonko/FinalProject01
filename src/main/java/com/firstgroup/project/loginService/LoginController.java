package com.firstgroup.project.loginService;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.Exceptions.IncorrectEmail;
import com.firstgroup.project.Exceptions.IncorrectPassword;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.dataBase.DBService;
import com.firstgroup.project.hotels.User;

public class LoginController {

    private HotelsAPI hotelsAPI = new HotelsAPI();

    public User registerUser (String name, String surname, String email, String password) throws UserAlreadyExist {

        return hotelsAPI.registerUser(new User(name,surname,email,password));

    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {

        return hotelsAPI.loginUser(email,password);
    }

    public DBService getDbService() {
        return hotelsAPI.getDbService();
    }
}
