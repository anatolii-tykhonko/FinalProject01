package com.firstgroup.project.DAOs;

import com.firstgroup.project.hotels.User;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface UserDAOInterface {
    public User save(User obj);

    public boolean delete(User obj);

    public User update(User obj);
}
