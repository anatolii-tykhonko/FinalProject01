package com.firstgroup.project.DAOs;


import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomDAOInterface {
    Room save(Room obj, int hotelsIndex);
    boolean delete(Room obj);
    Room update(Room obj, int hotelIndex,int roomIndex);
    List<Hotel> findRoomsByHotel(String hotelName) throws IncorrectDataInput;
}
