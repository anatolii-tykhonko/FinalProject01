package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.Exceptions.UserNotCreated;
import com.firstgroup.project.hotels.User;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface UserDAOInterface {

    User save(User user) throws UserAlreadyExist;

    User add(User user) throws UserAlreadyExist;

    User delete(String email) throws UserNotCreated, CantDeleteCurrentUser;

    User update(User user);
}
