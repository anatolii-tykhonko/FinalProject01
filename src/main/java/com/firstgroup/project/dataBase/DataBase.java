package com.firstgroup.project.dataBase;

import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase implements Serializable{
    private List<Hotel> hotelList = new ArrayList<>();
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


}