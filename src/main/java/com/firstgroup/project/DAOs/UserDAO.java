package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.Map;

public class UserDAO implements UserDAOInterface {

    private DBServiceSingleton dbServiceSingleton = DBServiceSingleton.getDBServiceInstance();

    public User save(User user, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        if (dbServiceSingleton.getDataBase().getUserMap().keySet().contains(user.getEmail())) {
            throw new UserAlreadyExist("Юзер с таким имейлом уже существует");
        }
        dbServiceSingleton.getDataBase().getUserMap().put(user.getEmail(), user);
        if (regTRUEaddFALSE) dbServiceSingleton.getDataBase().setCurrentUser(user);
        dbServiceSingleton.save();
        return user;
    }

    public User delete(String email) throws CantDeleteCurrentUser {
        if (dbServiceSingleton.getDataBase().getCurrentUser().equals(dbServiceSingleton.getDataBase().getUserMap().get(email)))
            throw new CantDeleteCurrentUser("Нельзя удалить текущего юзера! Повторите попытку!");
        User removeUser = dbServiceSingleton.getDataBase().getUserMap().remove(email);
        dbServiceSingleton.save();
        return removeUser;
    }

    public User update(User user) {
        dbServiceSingleton.getDataBase().getUserMap().get(user.getEmail()).setName(user.getName());
        dbServiceSingleton.getDataBase().getUserMap().get(user.getEmail()).setSurname(user.getSurname());
        dbServiceSingleton.save();
        return user;
    }

    public Room reservRoom(Room room, LocalDate date, boolean status) {
        room.setStatus(true);
        room.setReservBefore(date);
        dbServiceSingleton.getDataBase().getCurrentUser().getRoomList().add(room);
        dbServiceSingleton.save();
        return room;
    }

    public boolean cancelReserv(int roomindex) {
        dbServiceSingleton.getDataBase().getCurrentUser().getRoomList().get(roomindex).setStatus(false);
        dbServiceSingleton.getDataBase().getCurrentUser().getRoomList().remove(roomindex);
        dbServiceSingleton.save();
        return true;
    }

    public DBServiceSingleton getDbServiceSingleton() {
        return dbServiceSingleton;
    }
}