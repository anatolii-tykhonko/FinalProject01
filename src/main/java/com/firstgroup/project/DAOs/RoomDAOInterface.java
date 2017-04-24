package com.firstgroup.project.DAOs;


import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;

import java.util.List;

public interface RoomDAOInterface {
    public Room save(Room obj);

    public boolean delete(Room obj);

    public Room update(Room obj);

    public List<Room> findRoomsByHotel(Hotel hotel);
}
