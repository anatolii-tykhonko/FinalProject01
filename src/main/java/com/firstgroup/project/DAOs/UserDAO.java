package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.IncorrectEmail;
import com.firstgroup.project.Exceptions.IncorrectPassword;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.entity.User;

public class UserDAO extends DBService implements UserDAOInterface{


    public User save(User user, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        if (getDataBase().getUserMap().keySet().contains(user.getEmail())) {
            throw new UserAlreadyExist("Юзер с таким имейлом уже существует");
        }
        getDataBase().getUserMap().put(user.getEmail(), user);
        if (regTRUEaddFALSE) getDataBase().setCurrentUser(user);

        return user;
    }

    public User delete(String email) throws CantDeleteCurrentUser {
        if (getDataBase().getCurrentUser().equals(getDataBase().getUserMap().get(email)))
            throw new CantDeleteCurrentUser("Нельзя удалить текущего юзера! Повторите попытку!");
        return getDataBase().getUserMap().remove(email);
    }

    public User update(User user) {
        getDataBase().getUserMap().get(user.getEmail()).setName(user.getName());
        getDataBase().getUserMap().get(user.getEmail()).setSurname(user.getSurname());
        return user;
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        if (getDataBase().getUserMap().keySet().contains(email)) {
            if (getDataBase().getUserMap().get(email).getPassword().equals(password)) {
                getDataBase().setCurrentUser(getDataBase().getUserMap().get(email));
                return true;
            } else throw new IncorrectPassword("Не верный пароль! Повторите ввод!\n");
        } else throw new IncorrectEmail("Юзера с таким email не существует! Повторите ввод!\n");
    }
}