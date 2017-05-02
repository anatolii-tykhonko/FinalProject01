package com.firstgroup.project.DAOs;


import com.firstgroup.project.hotels.Room;

import java.util.List;

public interface RoomDAOInterface {
    Room save(Room obj, int hotelsIndex);
    boolean delete(Room obj);
    Room update(Room obj);
    List<Room> findRoomsByHotel(String hotelName);
}
