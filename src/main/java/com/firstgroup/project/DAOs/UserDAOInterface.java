package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;
import java.time.LocalDate;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface UserDAOInterface {

    User save(User user, boolean regTRUEaddFALSE) throws UserAlreadyExist;

    User delete(String email) throws CantDeleteCurrentUser;

    User update(User user);

    DBServiceSingleton getDbServiceSingleton();

    Room reservRoom(Room room, LocalDate date, boolean status);

    boolean cancelReserv(int roomindex);
}
