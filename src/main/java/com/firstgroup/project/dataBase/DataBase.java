package com.firstgroup.project.dataBase;

import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class DataBase implements Serializable{
    private List<Hotel> hotelList = new ArrayList<>();
    private Map<String, User> userMap = new HashMap<>();

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }
}