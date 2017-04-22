package com.firstgroup.project.dataBase;

import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class DB {
    List<Hotel> hotelList = new ArrayList<Hotel>();
    List<Room> roomList = new ArrayList<Room>();
    Map<String, User> userMap = new HashMap<String, User>();
}
