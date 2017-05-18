package com.firstgroup.project.controllers;

import com.firstgroup.project.exceptions.*;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.util.Map;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public interface UserControllerInterface {

    User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist;

    User editUserInfo(String newName, String newSurName, String oldEmail);

    User deleteUser(String email) throws CantDeleteCurrentUser;

    Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat;

    boolean cancelReservationByName(int roomIndex);

    boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword;

    Map<String, User> getUsers();

    User getCurrentUser();
}
