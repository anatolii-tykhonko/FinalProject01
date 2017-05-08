package com.firstgroup.project.Controllers;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class UserController implements UserControllerInterface {

    @Override
    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return null;
    }

    @Override
    public User editUserInfo(String newName, String newSurName, String oldEmail) {
        return null;
    }

    @Override
    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return null;
    }

    @Override
    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        return null;
    }

    @Override
    public boolean cancelReservationByName(int roomIndex) {
        return false;
    }

    @Override
    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        return false;
    }
}
