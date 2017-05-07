package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.entity.User;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface UserDAOInterface {

    User save(User user, boolean regTRUEaddFALSE) throws UserAlreadyExist;

    User delete(String email) throws CantDeleteCurrentUser;

    User update(User user);
}
