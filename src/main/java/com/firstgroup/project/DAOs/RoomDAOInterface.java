package com.firstgroup.project.DAOs;


import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import java.util.List;

public interface RoomDAOInterface {
    Room save(Room obj, int hotelsIndex);

    boolean delete(Room obj);

    Room update(Room obj, int hotelIndex, int roomIndex);

    List<Hotel> findRoomsByHotel(String hotelName);

    DBServiceSingleton getDbServiceSingleton();
}
