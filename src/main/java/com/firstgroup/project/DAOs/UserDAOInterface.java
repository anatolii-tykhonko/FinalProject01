package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.hotels.User;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface UserDAOInterface {

    User save(User obj) throws UserAlreadyExist;

    boolean delete(User obj);

    User update(User obj);
}
