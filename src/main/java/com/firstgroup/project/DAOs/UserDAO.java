package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.entity.User;

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
        dbServiceSingleton.save();
        return dbServiceSingleton.getDataBase().getUserMap().remove(email);
    }

    public User update(User user) {
        dbServiceSingleton.getDataBase().getUserMap().get(user.getEmail()).setName(user.getName());
        dbServiceSingleton.getDataBase().getUserMap().get(user.getEmail()).setSurname(user.getSurname());
        dbServiceSingleton.save();
        return user;
    }

    public DBServiceSingleton getDbServiceSingleton() {
        return dbServiceSingleton;
    }
}